package com.zjyx.vote.api.service;

import com.zjyx.vote.api.model.dto.UserLoginDto;
import com.zjyx.vote.api.model.enums.User_Status;
import com.zjyx.vote.common.model.ReturnData;

public interface IUserLoginService {

	public ReturnData<Integer> updatePassword(Long id,String password);
	
	public ReturnData<Integer> updateStatus(Long id,User_Status status);

	public ReturnData<UserLoginDto> login(String loginName,String password);
}
