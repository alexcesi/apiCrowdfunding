package com.apiCrowdfunding.javaProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiCrowdfunding.javaProject.models.Project;
import com.apiCrowdfunding.javaProject.repository.ProjectRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public List<Project> getAllProjects() {
		return projectRepository.getAll();		
	}
	
	public Project getById(int id){
		return projectRepository.get(id);		
	}
	
	public Project add(Project project) {
	    System.out.println(project.getUser().getId());
		return projectRepository.add(project);		
	}
	
	public Project update(Project project) {
		return projectRepository.update(project);		
	}
	
	public boolean deleteById(int id){
		try {
			projectRepository.delete(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
}
