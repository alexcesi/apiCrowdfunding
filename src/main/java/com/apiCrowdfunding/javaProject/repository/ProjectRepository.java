package com.apiCrowdfunding.javaProject.repository;


import java.util.List;


import org.springframework.stereotype.Repository;

import com.apiCrowdfunding.javaProject.models.Project;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProjectRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<Project> getAll() {
		List<Project> res = null;
		try {
			Query query = em.createQuery("SELECT b FROM Project AS b");
			res = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Project get(int id) {
		Project res = null;
		try {
			res = em.find(Project.class, id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Project add(Project project) {
		try {
			em.persist(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}
	
	public Project update(Project project) {
		try {
			em.merge(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}
	
	public void delete(int id) {
		try {
			Query query = em.createQuery(
					"DELETE  FROM Project WHERE id = :id");
			query.setParameter("id", id);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}