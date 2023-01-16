package com.apiCrowdfunding.javaProject.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apiCrowdfunding.javaProject.config.JwtService;
import com.apiCrowdfunding.javaProject.models.Role;
import com.apiCrowdfunding.javaProject.models.User;
import com.apiCrowdfunding.javaProject.repository.UserRepository;
import com.apiCrowdfunding.javaProject.services.RoleService;
import com.apiCrowdfunding.javaProject.services.UserService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class AuthenticationService {
	
	@Autowired
	private  UserService service;

	@Autowired
	private  UserRepository repository;

	@Autowired
	private  PasswordEncoder passwordEncoder;

	@Autowired
	private  JwtService jwtService;
	
	@Autowired
	private  AuthenticationManager authenticationManager;

	@Autowired
	private RoleService roleService;
	
	public AuthenticationResponse register(RegisterRequest request) {
		Role role = roleService.getRoleByName(request.getRole());
		var user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()),
				request.getIsProjectOwner(), role);
		try {
			service.add(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
		.token(jwtToken)
		.build();
	} 
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword())
				);	
		var user = repository.findByEmail(request.getEmail());
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

}
