package com.zjyx.vote.impl.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.zjyx.vote.api.model.condition.UserCdtn;
import com.zjyx.vote.api.model.persistence.User;
import com.zjyx.vote.api.service.IUserService;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.PageInfo;
import com.zjyx.vote.common.model.ReturnData;
import com.zjyx.vote.impl.mapper.UserMapper;

@Service
public class UserServiceImpl implements IUserService{

	@Resource
	UserMapper userMapper;
	
	@Override
	public ReturnData<User> selectById(Long id) {
		ReturnData<User> returnData = new ReturnData<User>();
		if(id == null){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
		}else{
			User user = userMapper.selectById(id);
			returnData.setResultData(user);
		}
		return returnData;
	}

	@Override
	public ReturnData<Integer> update(User user) {
		ReturnData<Integer> returnData = new ReturnData<Integer>();
		if(user == null || user.getId() < 1){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
			returnData.setErrorMessage("无效的用户信息");
		}else if(user.getAge() != 0 && (user.getAge() < 7 || user.getAge() > 120)){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
			returnData.setErrorMessage("无效的年龄");
		}else{
			user.setEmail(null);
			int flag = userMapper.update(user);
			returnData.setResultData(flag);
		}
		return returnData;
	}

	@Override
	public PageInfo<User> list(UserCdtn userCdtn) {
		PageInfo<User> pageInfo = new PageInfo<User>();
		if(userCdtn == null || userCdtn.getCurrentPage() < 1 || userCdtn.getOnePageSize() < 1){
			pageInfo.setErrorType(Error_Type.PARAM_ERROR);
		}else{
			int counts = userMapper.count(userCdtn);
			if(counts > 0){
				List<User> list = userMapper.list(userCdtn);
				pageInfo.setPageInfo(userCdtn ,counts, list, null);
			}
		}
		return pageInfo;
	}

}
