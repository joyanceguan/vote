package com.zjyx.authority.impl.mapper;

import java.util.List;

import com.zjyx.authority.api.model.bean.SysRoleBean;
import com.zjyx.authority.api.model.persistence.SysRole;

public interface SysRoleMapper {
    int insert(SysRole record);

    int insertSelective(SysRole record);

	int getListCount(SysRoleBean condition);

	List<SysRole> getListPage(SysRoleBean condition);
}