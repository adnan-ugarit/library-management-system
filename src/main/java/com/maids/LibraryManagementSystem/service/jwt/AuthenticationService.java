package com.maids.LibraryManagementSystem.service.jwt;

import com.maids.LibraryManagementSystem.filter.AuthenticationRequest;
import com.maids.LibraryManagementSystem.filter.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
						)
				);

		var user = userDetailsService.loadUserByUsername(request.getEmail());
		var jwtToken = jwtService.generateToken(user);
		var response =  new AuthenticationResponse();
		response.setToken(jwtToken);
		return response;
	}
}
