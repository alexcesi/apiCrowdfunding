package com.apiCrowdfunding.javaProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiCrowdfunding.javaProject.models.Contribution;
import com.apiCrowdfunding.javaProject.repository.ContributionRepository;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContributionService {

    @Autowired
    private ContributionRepository contributionRepository;

  
    public Contribution add(Contribution contribution) {
        return contributionRepository.save(contribution);
    }

    public Contribution getById(Integer id) {
        return contributionRepository.findById(id).orElse(null);
    }
}