package com.zjyx.authority.impl.mapper;

import com.zjyx.authority.api.model.persistence.SysFunc;

public interface SysFuncMapper {
	
    int insert(SysFunc record);
    int insertSelective(SysFunc record);
}