package com.weat.weat.data.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weat.weat.data.dto.LoginRequest;
import com.weat.weat.response.ApiResponse;

@RequestMapping("/password")
public interface PasswordV5Controller {

	@PostMapping("/login")
	ResponseEntity<ApiResponse> passwordLogin(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest req);

}
