package com.weat.weat.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.weat.weat.data.dto.UserDto;
import com.weat.weat.data.model.Role;
import com.weat.weat.data.model.User;

public interface CoreUserService {

	UserDto create(UserDto user);

	List<Role> getRoles(User user);

	User getCurrentUser();

	void logUserLogin(String username, HttpServletRequest httpServletRequest);

	boolean enableDisableUser(Long userId, boolean enabled);

	List<UserDto> getUserByRole(String roleName);


}