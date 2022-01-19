package com.buri.basic.repository;

import static org.assertj.core.api.Assertions.*;

import com.buri.basic.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();

	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}

	@Test
	void save() {
		// given
		Member member = new Member();
		member.setNickname("buhee");

		// when
		repository.save(member);

		// then
		Member result = repository.findById(member.getId())
								  .get();
		assertThat(member).isEqualTo(result);
	}

	@Test
	void findByNickname() {
		// given
		Member member = new Member();
		member.setNickname("buhee");
		repository.save(member);

		// when
		Member result = repository.findByNickname("buhee")
								  .get();

		// then
		assertThat(result).isEqualTo(member);
	}

	@Test
	void findAll() {
		// given
		Member member1 = new Member();
		member1.setNickname("buhee");
		repository.save(member1);

		Member member2 = new Member();
		member2.setNickname("buri");
		repository.save(member2);

		// when
		List<Member> result = repository.findAll();

		// then
		assertThat(result.size()).isEqualTo(2);
	}
}