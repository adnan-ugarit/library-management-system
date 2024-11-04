package com.maids.LibraryManagementSystem.security;

import com.maids.LibraryManagementSystem.config.WebSecurityConfig;
import com.maids.LibraryManagementSystem.service.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
//import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain
			) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		boolean isInvalidToken = false;
		
		if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
			isInvalidToken = true;
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7);
		userEmail = jwtService.extractUsername(jwt);
		if (userEmail != null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
			if (jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
						);
				authToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request)
						);
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		else
		{
			isInvalidToken = true;
		}
		
		if (isInvalidToken)
		{
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
		}		
		
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request)
			throws ServletException {

		String path = ((HttpServletRequest) request).getRequestURI();
		String[] allowedPaths = WebSecurityConfig.PUBLIC_REQUEST_MATCHERS;
		for (var allowedPath : allowedPaths) {
			allowedPath = allowedPath.replace("/*", "");
			allowedPath = allowedPath.replace("/**", "");
			if (path.contains(allowedPath)) {
				return true;
			}
		}

		return false;
	}
}
