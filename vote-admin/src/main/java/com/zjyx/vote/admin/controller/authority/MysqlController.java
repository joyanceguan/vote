package com.zjyx.vote.admin.controller.authority;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjyx.authority.api.model.persistence.AdminUser;
import com.zjyx.authority.api.service.AdminUserService;


@Controller
@RequestMapping("/mysql")
public class MysqlController {
	
	@Resource
	AdminUserService adminUserService;

	@ResponseBody
	@RequestMapping("/select")
	public AdminUser select(Integer id){
		return adminUserService.getAdminUser(id);
	}
	
}
