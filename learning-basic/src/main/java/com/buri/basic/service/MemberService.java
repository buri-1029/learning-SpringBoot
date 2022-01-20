package com.buri.basic.service;

import com.buri.basic.domain.Member;
import com.buri.basic.repository.MemberRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Long join(Member member) {
		validateDuplicateMember(member);

		return memberRepository.save(member)
							   .getId();
	}

	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId)
							   .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByNickname(member.getNickname())
						.ifPresent(m -> {
							throw new IllegalStateException("이미 존재하는 회원입니다.");
						});
	}

}
