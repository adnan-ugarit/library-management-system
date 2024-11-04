package com.maids.LibraryManagementSystem.api;

import com.maids.LibraryManagementSystem.filter.AuthenticationRequest;
import com.maids.LibraryManagementSystem.filter.AuthenticationResponse;
import com.maids.LibraryManagementSystem.service.jwt.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationService service;

  @Operation(summary = "Authenticate to get access JWT token")
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

}