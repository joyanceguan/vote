package com.zjyx.vote.impl.transaction;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjyx.vote.api.model.condition.UserCdtn;
import com.zjyx.vote.api.model.enums.User_Source;
import com.zjyx.vote.api.model.enums.User_Status;
import com.zjyx.vote.api.model.persistence.User;
import com.zjyx.vote.api.model.persistence.UserLogin;
import com.zjyx.vote.api.transaction.IUserTransSerivce;
import com.zjyx.vote.common.constants.Regex;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.exceptions.TransactionException;
import com.zjyx.vote.common.model.ReturnData;
import com.zjyx.vote.common.utils.RegexUtils;
import com.zjyx.vote.impl.mapper.UserLoginMapper;
import com.zjyx.vote.impl.mapper.UserMapper;

@Service
public class UserTransServiceImpl implements IUserTransSerivce{

	@Resource
	UserMapper userMapper;
	
	@Resource
	UserLoginMapper userLoginMapper;
	
	@Transactional("votetm")
	@Override
	public ReturnData<Integer> saveUser(String passwd, User user) {
		ReturnData<Integer> returnData = new ReturnData<Integer>();
	    if(user == null){
	    	throw new TransactionException(Error_Type.PARAM_ERROR,null,"用户信息为空");
	    }
	    if(StringUtils.isBlank(passwd)){
			throw new TransactionException(Error_Type.PARAM_ERROR,null,"必传信息为空");
		}
		if(StringUtils.isBlank(user.getEmail()) || !RegexUtils.isMatch(Regex.REGEX_EMAIL, user.getEmail())){
			throw new TransactionException(Error_Type.PARAM_ERROR,null,"不合法的邮件");
		}
		if(user.getSex() == null){
			throw new TransactionException(Error_Type.PARAM_ERROR,null,"性别不能为空");
		}
		if(user.getAge() < 7 || user.getAge() > 120){
			throw new TransactionException(Error_Type.PARAM_ERROR,null,"请输入正确的年龄");
		}
		//判断邮件是否存在
		UserCdtn userCdtn = new UserCdtn();
		userCdtn.setEmail(user.getEmail());
		List<User>  users = userMapper.list(userCdtn);
		if(users!=null && users.size()>0){
			throw new TransactionException(Error_Type.PARAM_ERROR,null,"该邮件已经注册");
		}
		user.setSource(User_Source.self);
		int flag = userMapper.save(user);
		if(flag < 1){
			throw new TransactionException(Error_Type.SYSTEM_ERROR,null,null);
		}
		UserLogin userLogin = new UserLogin();
		userLogin.setId(user.getId());
		userLogin.setEmail(user.getEmail());
		userLogin.setPassword(passwd);
		userLogin.setLast_login_time(new Date());
		userLogin.setStatus(User_Status.normal);
		flag = userLoginMapper.save(userLogin);
		if(flag < 1){
			throw new TransactionException(Error_Type.SYSTEM_ERROR,null,null);
		}
		return returnData;
	}

}
