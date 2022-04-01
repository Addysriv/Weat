package com.weat.weat.data.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import com.weat.weat.common.exception.CoreException;
import com.weat.weat.common.utils.CoreUtil;
import com.weat.weat.config.Messages;
import com.weat.weat.data.controller.PasswordV5Controller;
import com.weat.weat.data.dto.LoginRequest;
import com.weat.weat.response.ApiResponse;
import com.weat.weat.service.impl.CustomUserDetailsService;
import com.weat.weat.util.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PasswordV5ControllerImpl implements PasswordV5Controller {
	
	private final AuthenticationManager authenticationManager;
	private final  JwtTokenUtil jwtTokenUtil;
	private final CustomUserDetailsService userDetailsService;
	private final Messages messages;
	//private final CoreUserService coreUserServiceImpl;
	
	@Override
	public ResponseEntity<ApiResponse> passwordLogin(@Valid LoginRequest loginRequest, HttpServletRequest request) {
		authenticate(loginRequest.getPhoneNumber(), loginRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getPhoneNumber());
		final String token = jwtTokenUtil.generateToken(userDetails);
		//coreUserServiceImpl.logUserLogin(loginRequest.getPhoneNumber(), request);
		return CoreUtil.buildApiResponse(token, request, messages.get("user.error"),
				HttpStatus.OK.value(), HttpStatus.NOT_FOUND.value(), HttpStatus.OK, HttpStatus.NOT_FOUND);
	}
	
	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new CoreException(HttpStatus.UNAUTHORIZED.value(), "USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new CoreException(HttpStatus.UNAUTHORIZED.value(), "INVALID_CREDENTIALS");
		}
	}

}
