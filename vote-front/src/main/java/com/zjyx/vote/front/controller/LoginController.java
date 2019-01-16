package com.zjyx.vote.front.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zjyx.vote.api.model.dto.UserLoginDto;
import com.zjyx.vote.api.service.IUserLoginService;
import com.zjyx.vote.common.constants.Regex;
import com.zjyx.vote.common.constants.VoteConstants;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.ReturnData;
import com.zjyx.vote.common.utils.RegexUtils;
import com.zjyx.vote.common.utils.WebContextHelper;
import com.zjyx.vote.front.param.LoginParam;
import com.zjyx.vote.front.viewmodel.LoginVM;

@Controller
public class LoginController {
	
	@Resource
	IUserLoginService userLoginService;

	@RequestMapping("/login")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("front/login");
		return mv;
	}
	
	@RequestMapping("/dologin")
	@ResponseBody
	public LoginVM login(LoginParam param){
		LoginVM vm = new LoginVM();
		if(StringUtils.isBlank(param.getEmail()) || StringUtils.isBlank(param.getPassword())){
			vm.setErrorType(Error_Type.PARAM_ERROR);
			vm.setErrorMessage("邮箱和密码不能为空");
			return vm;
		}
		if(!RegexUtils.isMatch(Regex.REGEX_EMAIL, param.getEmail())){
			vm.setErrorType(Error_Type.PARAM_ERROR);
			vm.setErrorMessage("请书写正确的邮箱");
			return vm;
		}
		ReturnData<UserLoginDto> returnData = userLoginService.login(param.getEmail(), param.getPassword());
		UserLoginDto userLogin = returnData.getResultData();
		if(userLogin!=null){
			WebContextHelper.setSessionValue(VoteConstants.USER_SESSION, userLogin);
		}
		vm.setErrorInfo(returnData);
		return vm;
	}
	
}
