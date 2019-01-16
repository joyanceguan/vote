package com.zjyx.authority.impl.mapper;

import com.zjyx.authority.api.model.persistence.SysUserRole;

public interface SysUserRoleMapper {
	
    int insert(SysUserRole record);
    int insertSelective(SysUserRole record);
}