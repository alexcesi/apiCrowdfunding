package com.apiCrowdfunding.javaProject.repository;

import com.apiCrowdfunding.javaProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}