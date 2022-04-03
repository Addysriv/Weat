package com.weat.weat.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weat.weat.common.exception.CoreException;
import com.weat.weat.data.model.Role;
import com.weat.weat.data.model.User;
import com.weat.weat.repository.UserRepository;
import com.weat.weat.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service("userServiceImpl")
@Transactional
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

	@Override
	public List<Role> getRoles(User user) {
		return user.getRoles().stream().collect(Collectors.toList());
	}
}
