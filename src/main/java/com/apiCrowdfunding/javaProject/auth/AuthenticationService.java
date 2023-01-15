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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	@Autowired
	private RoleService roleService;
	
	public AuthenticationResponse register(RegisterRequest request) {
		Role role = roleService.getRoleByName(request.getRole());
		var user = User.builder()
        .username(request.getUsername())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
		.isProjectOwner(request.getIsProjectOwner())
		.role(role);
        .build()
	    repository.save(user);
	    var jwtToken = jwtService.generateToken(user);
	    return AuthenticationResponse.builder()
	        .token(jwtToken)
	        .build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()));
		var user = repository.findByEmail(request.getEmail())
				.orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

}
