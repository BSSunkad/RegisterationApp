package com.registerApp.demo.repository;

import java.lang.annotation.Native;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registerApp.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(name = "SELECT u FROM USER u WHERE u.email=:email")
	Optional<User> findByEmail(@Param("email") String email);

}
