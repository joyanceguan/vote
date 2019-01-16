package com.zjyx.vote.admin.controller.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;
import com.zjyx.authority.api.model.bean.SysRoleBean;
import com.zjyx.authority.api.model.dto.BaseView;
import com.zjyx.authority.api.model.dto.PageInfoDto;
import com.zjyx.authority.api.model.persistence.SysRole;
import com.zjyx.authority.api.service.RoleService;
import com.zjyx.authority.impl.helper.BaseViewHelper;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleService roleservice;
	
	@RequestMapping("/list")
	public ModelAndView list(SysRoleBean bean){
		bean.setCurrentPage(4);
		bean.setOnePageSize(2);
		System.out.println(JSONObject.toJSONString(bean));
		PageInfoDto<SysRole> roles = roleservice.getList(bean);
		ModelAndView mv = new ModelAndView("views/index");
		System.out.println(JSONObject.toJSONString(roles));
		return mv;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public BaseView save(SysRole bean){
		bean.setCreateuser("prescott");
		bean.setRoleId("1");
		bean.setRoleName("天管");
		bean.setSiteId((short) 1);
		return roleservice.save(bean);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public BaseView update(String roleId){
		return BaseViewHelper.getSuccessBaseView();
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(String roleId){
		ModelAndView model = new ModelAndView("views/index");
		if(StringUtils.isEmptyOrWhitespaceOnly(roleId)){
			model.addObject("model", BaseViewHelper.getBaseView(true, "201", "参数不能为空"));
			return model;
		}
		SysRole role = roleservice.findByRoleId(roleId);
		if(role == null){
			model.addObject("model", BaseViewHelper.getBaseView(true, "202", "无此记录"));
		}
		return model;
	}
}
