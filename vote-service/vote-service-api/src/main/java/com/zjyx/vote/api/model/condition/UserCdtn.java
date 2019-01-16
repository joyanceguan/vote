package com.zjyx.vote.api.model.condition;

import com.zjyx.vote.common.model.BasePageCondition;

public class UserCdtn extends BasePageCondition{

	private String username;
	
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
