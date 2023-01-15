package com.apiCrowdfunding.javaProject.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.apiCrowdfunding.javaProject.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserRepository{
	
	@PersistenceContext
	private EntityManager em;

	public List<User> getAllUsers() {
		List<User> res = null;
		try {
			Query query = em.createQuery("SELECT u FROM User AS u");
			res = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public User get(int id) {
		User res = null;
		try {
			res = em.find(User.class, id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public User getByEmail(String email) {
		User res = null;
		try {
			res = em.find(User.class, email);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public User add(User user) {
		try {
			em.persist(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User update(User user) {
		try {
			em.merge(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void delete(int id) {
		try {
			Query query = em.createQuery(
					"DELETE  FROM User WHERE id = :id");
			query.setParameter("id", id);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User save(User user) {
		try {
			em.persist(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
