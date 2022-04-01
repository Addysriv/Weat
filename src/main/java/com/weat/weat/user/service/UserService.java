package com.weat.weat.user.service;

import java.util.List;

import com.weat.weat.data.model.Role;
import com.weat.weat.data.model.User;

public interface UserService {

	User saveUser(User user);

	User getUser(Long userId);

	List<Role> getRoles(User user);
	
	
	
	
}
