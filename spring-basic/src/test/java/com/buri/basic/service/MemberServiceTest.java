package com.buri.basic.service;

import static org.assertj.core.api.Assertions.*;

import com.buri.basic.domain.Member;
import com.buri.basic.repository.MemoryMemberRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;

	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}


	@DisplayName("회원가입 테스트")
	@Test
	void join() {
		// given
		Member member = new Member();
		member.setNickname("buhee");

		// when
		memberService.join(member);

		// then
		Member result = memberService.findMember(member.getId());
		assertThat(member).isEqualTo(result);

	}

	@DisplayName("모든 회원 조회 테스트")
	@Test
	void findMembers() {
		// given
		Member member1 = new Member();
		member1.setNickname("buhee");
		memberService.join(member1);

		Member member2 = new Member();
		member2.setNickname("buri");
		memberService.join(member2);

		// when
		List<Member> result = memberService.findMembers();

		// then
		assertThat(result.size()).isEqualTo(2);
	}

	@DisplayName("식별자 회원 조회 테스트")
	@Test
	void findMember() {
		// given
		Member member = new Member();
		member.setNickname("buhee");
		memberService.join(member);

		// when
		Member result = memberService.findMember(1L);

		// then
		assertThat(result).isEqualTo(member);
	}

	@DisplayName("중복 닉네임 회원가입 테스트")
	@Test
	void joinByDuplicateNickname() {
		// given
		Member member1 = new Member();
		member1.setNickname("buhee");
		memberService.join(member1);

		// when then
		Member member2 = new Member();
		member2.setNickname("buhee");

		assertThatThrownBy(() -> memberService.join(member2)).isInstanceOf(
			IllegalStateException.class);
	}
}