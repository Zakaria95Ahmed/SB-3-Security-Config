package com.example.library.jwt;


import com.example.library.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/authenticate")
public class JWTController {


	private final JWTService jwtService;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/users")
	public String getTokenForAuthenticatedUser(@RequestBody JWTAuthenticationRequest authRequest) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserEmail(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUserEmail());
		} else {
			throw new UserNotFoundException("Invalid user credentials");
		}
	}


}
