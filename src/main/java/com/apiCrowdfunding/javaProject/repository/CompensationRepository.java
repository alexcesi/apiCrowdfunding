package com.apiCrowdfunding.javaProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiCrowdfunding.javaProject.models.Compensation;

@Repository
public interface CompensationRepository extends JpaRepository<Compensation, Integer> {

}