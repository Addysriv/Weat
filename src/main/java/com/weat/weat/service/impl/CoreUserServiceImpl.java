package com.weat.weat.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weat.weat.data.dto.UserDto;
import com.weat.weat.data.model.Role;
import com.weat.weat.data.model.User;
import com.weat.weat.repository.UserRepository;
import com.weat.weat.service.CoreUserService;


@Service("coreUserServiceImpl")
@DependsOn("encoder")
@Transactional
public class CoreUserServiceImpl implements CoreUserService {

	private static final String[] IP_HEADER_CANDIDATES = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto create(UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getRoles(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logUserLogin(String username, HttpServletRequest httpServletRequest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean enableDisableUser(Long userId, boolean enabled) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserDto> getUserByRole(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
