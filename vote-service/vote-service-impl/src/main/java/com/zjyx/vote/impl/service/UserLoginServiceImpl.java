package com.zjyx.vote.impl.service;

import java.util.Date;
import java.util.concurrent.ExecutorService;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zjyx.vote.api.model.dto.UserLoginDto;
import com.zjyx.vote.api.model.enums.User_Status;
import com.zjyx.vote.api.model.persistence.User;
import com.zjyx.vote.api.model.persistence.UserLogin;
import com.zjyx.vote.api.service.IUserLoginService;
import com.zjyx.vote.common.constants.ErrorCode;
import com.zjyx.vote.common.constants.Regex;
import com.zjyx.vote.common.constants.VoteConstants;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.ReturnData;
import com.zjyx.vote.common.utils.Md5Utils;
import com.zjyx.vote.common.utils.RegexUtils;
import com.zjyx.vote.impl.mapper.UserLoginMapper;
import com.zjyx.vote.impl.mapper.UserMapper;


@Service
public class UserLoginServiceImpl implements IUserLoginService{
	
	@Resource
	UserLoginMapper userLoginMapper;
	
	@Resource
	ExecutorService syncExcutor;
	
	@Resource
	UserMapper userMapper;

	@Override
	public ReturnData<Integer> updatePassword(Long id, String password) {
		ReturnData<Integer> returnData = new ReturnData<Integer>();
		if(id == null || StringUtils.isBlank(password)){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
		}else{
			UserLogin userLogin = new UserLogin();
			userLogin.setPassword(password);
			userLogin.setId(id);
			int flag = userLoginMapper.update(userLogin);
			returnData.setResultData(flag);
		}
		return returnData;
	}

	@Override
	public ReturnData<Integer> updateStatus(Long id, User_Status status) {
		ReturnData<Integer> returnData = new ReturnData<Integer>();
		if(id == null || status == null){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
		}else{
			UserLogin userLogin = new UserLogin();
			userLogin.setStatus(status);
			userLogin.setId(id);
			int flag = userLoginMapper.update(userLogin);
			returnData.setResultData(flag);
		}
		return returnData;
	}

	@Override
	public ReturnData<UserLoginDto> login(String loginName,String password) {
		ReturnData<UserLoginDto> returnData = new ReturnData<UserLoginDto>();
		if(StringUtils.isBlank(loginName) || StringUtils.isBlank(password)){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
		}else{
		    UserLogin userLogin = null;
			boolean isEmail = RegexUtils.isMatch(Regex.REGEX_EMAIL, loginName);
			if(isEmail){
				userLogin = userLoginMapper.selectByEmail(loginName);
			}else{
			    userLogin = userLoginMapper.selectByUsername(loginName);
			}
			if(userLogin == null){
				returnData.setErrorType(Error_Type.SERVICE_ERROR);
				returnData.setErrorCode(ErrorCode.INVALID_USERNAME);
			}else{
				String passwdMd5 = Md5Utils.md5(password, VoteConstants.PASSWD_SALT);
				if(!passwdMd5.equals(userLogin.getPassword())){
					returnData.setErrorType(Error_Type.SERVICE_ERROR);
					returnData.setErrorCode(ErrorCode.INVALID_PASSWD);
				}else{
					User user = userMapper.selectById(userLogin.getId());
					User_Status status = userLogin.getStatus();
					if(status == User_Status.freeze){
						returnData.setErrorType(Error_Type.SERVICE_ERROR);
						returnData.setErrorCode(ErrorCode.USER_FREEZE);
					}
					UserLoginDto dto = new UserLoginDto();
					dto.setUserLogin(userLogin);
					dto.setUser(user);
					returnData.setResultData(dto);
					userLogin.setLast_login_time(new Date());
					//异步修改最后登录时间
					updateLoginTime(userLogin);
				}
			}
		}
		return returnData;
	}
	
	private void updateLoginTime(UserLogin userLogin){
		syncExcutor.execute(new Runnable() {
	         @Override
	         public void run() {
	        	 userLoginMapper.update(userLogin); 
	         }
     	});
	}

}
