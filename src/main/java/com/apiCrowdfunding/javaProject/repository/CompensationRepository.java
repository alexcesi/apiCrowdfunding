package com.apiCrowdfunding.javaProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiCrowdfunding.javaProject.models.Compensation;

public interface CompensationRepository extends JpaRepository<Compensation, Integer> {

}