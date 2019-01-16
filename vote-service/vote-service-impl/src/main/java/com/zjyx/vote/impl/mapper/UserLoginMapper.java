package com.zjyx.vote.impl.mapper;

import com.zjyx.vote.api.model.persistence.UserLogin;

public interface UserLoginMapper {

	public int save(UserLogin userLogin);
	
	public int update(UserLogin userLogin);
	
	public UserLogin selectByUsername(String username);
	
	public UserLogin selectByEmail(String email);
}
