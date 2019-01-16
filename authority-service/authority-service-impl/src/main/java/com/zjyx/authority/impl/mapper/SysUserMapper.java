package com.zjyx.authority.impl.mapper;

import com.zjyx.authority.api.model.persistence.SysUser;

public interface SysUserMapper {
	
    int insert(SysUser record);
    int insertSelective(SysUser record);
}