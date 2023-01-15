package com.apiCrowdfunding.javaProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiCrowdfunding.javaProject.models.Contribution;

public interface ContributionRepository extends  JpaRepository<Contribution, Integer>{

}
