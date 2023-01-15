package com.apiCrowdfunding.javaProject.repository;

import org.springframework.stereotype.Repository;

import com.apiCrowdfunding.javaProject.models.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RoleRepository {
    @PersistenceContext
    private EntityManager em;
    
    public Role findById(Integer id) {
        return em.find(Role.class, id);
    }

    public Role findByName(String name) {
        return em.find(Role.class, name);
    }
}