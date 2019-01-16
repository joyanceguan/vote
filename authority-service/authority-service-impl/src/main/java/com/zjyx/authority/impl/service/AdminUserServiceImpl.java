package com.zjyx.authority.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjyx.authority.api.model.persistence.AdminUser;
import com.zjyx.authority.api.service.AdminUserService;
import com.zjyx.authority.impl.mapper.AdminUserMapper;


@Service
public class AdminUserServiceImpl implements AdminUserService{
	
	@Autowired
	AdminUserMapper adminUserMapper;
	
	public AdminUser getAdminUser(Integer id){
		return adminUserMapper.selectById(id);
	}

	@Override	
	public int updateAdminUser(AdminUser adminUser){
		return adminUserMapper.update(adminUser);
	}

	@Override
	public AdminUser getAdminUser(String name, String pass) {
		return adminUserMapper.loginByNamePass(name, pass);
	}
}
