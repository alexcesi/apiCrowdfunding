package com.apiCrowdfunding.javaProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiCrowdfunding.javaProject.models.Compensation;

import com.apiCrowdfunding.javaProject.repository.CompensationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CompensationService {

    @Autowired
    private CompensationRepository compensationRepository;

    public Compensation add(Compensation compensation) {
        return compensationRepository.save(compensation);
    }

    public Compensation getById(Integer id) {
        return compensationRepository.findById(id).orElse(null);
    }
}