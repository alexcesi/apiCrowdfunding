package com.apiCrowdfunding.javaProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiCrowdfunding.javaProject.models.User;
import com.apiCrowdfunding.javaProject.repository.UserRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	SecureRandom random = new SecureRandom();
	
	public List<User> getAllUsers() throws Exception{
		return userRepository.getAllUsers();		
	}
	
	public User getById(int id) {
		return userRepository.get(id);		
	}
	
	public User getByEmail(String email) {
		return userRepository.getByEmail(email);		
	}

	public User add(User user) throws Exception{
		String password = user.getPassword();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(salt);
		byte[] passwordEncrypted = md.digest(password.getBytes(StandardCharsets.UTF_8));
		String securePass = passwordEncrypted.toString();
		user.setPassword(securePass);
		return userRepository.add(user);		
	}
	
	public User update(User user) throws Exception{
		return userRepository.update(user);		
	}
	
	public boolean deleteById(int id){
		try {
			userRepository.delete(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
