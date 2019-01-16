package com.zjyx.authority.impl.mapper;

import com.zjyx.authority.api.model.persistence.SysFuncRole;

public interface SysFuncRoleMapper {
	
    int insert(SysFuncRole record);
    int insertSelective(SysFuncRole record);
}