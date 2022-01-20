package com.buri.basic.service;

import static org.assertj.core.api.Assertions.*;

import com.buri.basic.domain.Member;
import com.buri.basic.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

	@Autowired
	MemberService memberService;

	@Autowired
	MemberRepository memberRepository;


	@DisplayName("회원가입 테스트")
	@Test
	void join() {
		// given
		Member member = new Member();
		member.setNickname("test");

		// when
		memberService.join(member);

		// then
		Member result = memberRepository.findById(member.getId())
										.get();
		assertThat(member.getNickname()).isEqualTo(result.getNickname());

	}

	@DisplayName("중복 닉네임 회원가입 테스트")
	@Test
	void joinByDuplicateNickname() {
		// given
		Member member1 = new Member();
		member1.setNickname("test");
		memberService.join(member1);

		// when then
		Member member2 = new Member();
		member2.setNickname("test");

		assertThatThrownBy(() -> memberService.join(member2)).isInstanceOf(
			IllegalStateException.class);
	}
}