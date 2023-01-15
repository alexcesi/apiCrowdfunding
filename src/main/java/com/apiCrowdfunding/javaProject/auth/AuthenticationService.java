package com.apiCrowdfunding.javaProject.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apiCrowdfunding.javaProject.config.JwtService;
import com.apiCrowdfunding.javaProject.models.RoleAuth;
import com.apiCrowdfunding.javaProject.models.User;
import com.apiCrowdfunding.javaProject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	

	private final UserRepository repository = new UserRepository();
	  private final PasswordEncoder passwordEncoder = null;
	  private final JwtService jwtService = new JwtService();
	  private final AuthenticationManager authenticationManager = null;

	public AuthenticationResponse register(RegisterRequest request) {
	  var user = User.builder()
        .username(request.getUsername())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(RoleAuth.USER)
        .build();
	    repository.save(user);
	    var jwtToken = jwtService.generateToken(user);
	    return AuthenticationResponse.builder()
	        .token(jwtToken)
	        .build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		return null;
	}

}
