package com.buri.basic.repository;

import com.buri.basic.domain.Member;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

public class JpaMemberRepository implements MemberRepository {

	private final EntityManager em;

	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(em.find(Member.class, id));
	}

	@Override
	public Optional<Member> findByNickname(String nickname) {
		return em.createQuery(
					 "select m from Member m where m.nickname = :nickname",
					 Member.class
				 )
				 .setParameter("nickname", nickname)
				 .getResultList()
				 .stream()
				 .findAny();
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				 .getResultList();
	}
}
