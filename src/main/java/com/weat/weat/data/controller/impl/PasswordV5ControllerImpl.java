package com.weat.weat.data.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.weat.weat.data.controller.PasswordV5Controller;
import com.weat.weat.data.dto.LoginRequest;
import com.weat.weat.data.dto.LoginResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PasswordV5ControllerImpl implements PasswordV5Controller {
	
	
	@Override
	public ResponseEntity<LoginResponse> passwordLogin(@Valid LoginRequest loginRequest, HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}
