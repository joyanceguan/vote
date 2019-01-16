package com.zjyx.authority.api.service;

import com.zjyx.authority.api.model.bean.SysRoleBean;
import com.zjyx.authority.api.model.dto.BaseView;
import com.zjyx.authority.api.model.dto.PageInfoDto;
import com.zjyx.authority.api.model.persistence.SysRole;

public interface RoleService{

	PageInfoDto<SysRole> getList(SysRoleBean condition);

	BaseView save(SysRole bean);

	SysRole findByRoleId(String roleId);

}
