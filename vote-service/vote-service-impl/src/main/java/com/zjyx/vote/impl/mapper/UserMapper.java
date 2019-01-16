package com.zjyx.vote.impl.mapper;

import java.util.List;

import com.zjyx.vote.api.model.condition.UserCdtn;
import com.zjyx.vote.api.model.persistence.User;


public interface UserMapper {

	public int save(User user);
	
	public User selectById(Long id);
	
	public int update(User user);
	
	public int count(UserCdtn userCdtn);
	
	public List<User> list(UserCdtn userCdtn);
}
