package com.zjyx.vote.front.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zjyx.vote.api.model.enums.Sex;
import com.zjyx.vote.api.model.enums.User_Source;
import com.zjyx.vote.api.model.persistence.User;
import com.zjyx.vote.api.transaction.IUserTransSerivce;
import com.zjyx.vote.common.constants.Regex;
import com.zjyx.vote.common.constants.VoteConstants;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.BaseVM;
import com.zjyx.vote.common.model.ReturnData;
import com.zjyx.vote.common.utils.Md5Utils;
import com.zjyx.vote.common.utils.NumberUtils;
import com.zjyx.vote.common.utils.RegexUtils;
import com.zjyx.vote.front.param.RegisteParam;


@Controller
public class RegisteController {
	
	@Resource
	IUserTransSerivce userTransSerivce;

	@RequestMapping("/registe")
	public ModelAndView registe(){
		ModelAndView mv = new ModelAndView("front/registe");
		mv.addObject("sexList", Sex.values());
		return mv;
	}
	
	@RequestMapping("/registeUser")
	@ResponseBody
	public BaseVM registeUser(RegisteParam registe){
		BaseVM vm = new BaseVM();
		String password = registe.getPassword();
		String confirmPassword = registe.getConfirmPassword();
		String age = registe.getAge();
		Sex sex = registe.getSex();
		String email = registe.getEmail();
		if(StringUtils.isBlank(email)|| StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)
				||StringUtils.isBlank(age)||sex == null){
			vm.setErrorInfo(Error_Type.PARAM_ERROR, null, "必传信息为空");
			return vm;
		}
		if(!password.equals(confirmPassword)){
			vm.setErrorInfo(Error_Type.PARAM_ERROR, null, "密码和确认密码不一致");
			return vm;
		}
		if(password.length() > 20 || password.length() < 6){
			vm.setErrorInfo(Error_Type.PARAM_ERROR, null, "密码长度请控制在6-20");
			return vm;
		}
		int ageVal = NumberUtils.parseInt(age);
		if(ageVal < 7 || ageVal > 120){
			vm.setErrorInfo(Error_Type.PARAM_ERROR, null, "请输入正确的年龄");
			return vm;
		}
		if(StringUtils.isBlank(email) || !RegexUtils.isMatch(Regex.REGEX_EMAIL, email)){
			vm.setErrorInfo(Error_Type.PARAM_ERROR, null, "请输入正确格式的邮箱");
			return vm;
		}
		User user = new User();
		user.setAge(ageVal);
		user.setEmail(email);
		user.setSex(sex);
		user.setSource(User_Source.self);
		ReturnData<Integer> returnData = userTransSerivce.saveUser(Md5Utils.md5(password, VoteConstants.PASSWD_SALT), user);
		vm.setErrorInfo(returnData);
		return vm;
	}
	
}
