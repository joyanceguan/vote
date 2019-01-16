package com.zjyx.vote.admin.controller.authority;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;
import com.zjyx.authority.api.model.dto.BaseView;
import com.zjyx.authority.api.model.enums.UpDown_Status;
import com.zjyx.authority.api.model.persistence.AdminUser;
import com.zjyx.authority.api.service.AdminUserService;
import com.zjyx.authority.impl.helper.BaseViewHelper;


@Controller
@RequestMapping("/system")
public class LoginController {
	
	@Resource
	AdminUserService adminUserService;

	@ResponseBody
	@RequestMapping("/login")
	public BaseView select(String name, String pass){
		if(StringUtils.isEmptyOrWhitespaceOnly(name)){
			return BaseViewHelper.getBaseView(true, "10001", "用户名不能为空");
		}
		if(StringUtils.isEmptyOrWhitespaceOnly(pass)){
			return BaseViewHelper.getBaseView(true, "10002", "密码不能为空");
		}
		AdminUser user = adminUserService.getAdminUser(name, pass);
		if(user == null || StringUtils.isEmptyOrWhitespaceOnly(user.getUsername())){
			return BaseViewHelper.getBaseView(true, "20001", "密码错误");
		}
		if(UpDown_Status.DOWN == user.getStatus()){
			return BaseViewHelper.getBaseView(true, "20002", "无效用户");
		}
		return BaseViewHelper.getSuccessBaseView();
	}
	
}
