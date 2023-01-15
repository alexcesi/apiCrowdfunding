package com.apiCrowdfunding.javaProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiCrowdfunding.javaProject.models.Role;
import com.apiCrowdfunding.javaProject.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	 public Role findById(Integer id) {
	        return roleRepository.findById(id);
	    }

}