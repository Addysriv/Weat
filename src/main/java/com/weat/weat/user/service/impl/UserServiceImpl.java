package com.weat.weat.user.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.weat.weat.common.exception.CoreException;
import com.weat.weat.data.model.User;
import com.weat.weat.repository.UserRepository;
import com.weat.weat.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(Long userId) {
		return this.userRepository.findById(userId).orElseThrow(() -> new CoreException(HttpStatus.BAD_REQUEST.value(), "User not found with id :"+userId));
	}

}
