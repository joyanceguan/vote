package com.zjyx.authority.api.service;

import com.zjyx.authority.api.model.persistence.AdminUser;

public interface AdminUserService {
	
	public AdminUser getAdminUser(Integer id);
	
	public int updateAdminUser(AdminUser adminUser);
	
	public AdminUser getAdminUser(String name, String pass);

}
