package com.buri.basic.repository;

import com.buri.basic.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>,
	MemberRepository {

	Optional<Member> findByNickname(String name);
}
