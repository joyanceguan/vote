package com.zjyx.vote.api.transaction;

import com.zjyx.vote.api.model.persistence.User;
import com.zjyx.vote.common.model.ReturnData;

public interface IUserTransSerivce {

	public ReturnData<Integer> saveUser(String passwd,User user);
}
