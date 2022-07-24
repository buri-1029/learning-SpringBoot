package com.buri.basic.config;

import com.buri.basic.repository.MemberRepository;
import com.buri.basic.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	private final MemberRepository memberRepository;

	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}

	// private final EntityManager em;

	//	@Bean
	//	public MemberRepository memberRepository() {
	//		 return new JpaMemberRepository(em);
	//		 return new JdbcMemberRepository(dataSource);
	//		 return new MemoryMemberRepository();
	//	}

}
