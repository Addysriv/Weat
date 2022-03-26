package com.weat.weat.user.service;

import com.weat.weat.data.model.User;

public interface UserService {

	User saveUser(User user);

	User getUser(Long userId);
	
	
	
	
}
