package com.zjyx.vote.api.model.dto;

import com.zjyx.vote.api.model.persistence.User;
import com.zjyx.vote.api.model.persistence.UserLogin;

public class UserLoginDto {

	private UserLogin userLogin;
	
	private User user;

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
