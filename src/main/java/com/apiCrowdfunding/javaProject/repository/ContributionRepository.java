package com.apiCrowdfunding.javaProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiCrowdfunding.javaProject.models.Contribution;

@Repository
public interface ContributionRepository extends  JpaRepository<Contribution, Integer>{

}
