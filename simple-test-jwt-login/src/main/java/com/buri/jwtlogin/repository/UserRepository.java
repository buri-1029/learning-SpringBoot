package com.buri.jwtlogin.repository;

import com.buri.jwtlogin.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    // @EntityGraph은 쿼리가 수행될 때 Lazy조회가 아니고 Eager조회로 authorities정보를 같이 가져오게 됨.
    // name을 기준으로 User 정보를 가져올 때 권한 정보도 같이 가져오게 됨.
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);
}
