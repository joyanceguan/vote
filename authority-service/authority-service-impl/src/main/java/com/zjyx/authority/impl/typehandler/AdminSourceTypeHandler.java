package com.zjyx.authority.impl.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.zjyx.authority.api.model.enums.Admin_Source;


public class AdminSourceTypeHandler extends BaseTypeHandler<Admin_Source> {

	@Override
	public Admin_Source getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return Admin_Source.valueOf(rs.getInt(columnName));
	}

	@Override
	public Admin_Source getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return Admin_Source.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public Admin_Source getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return Admin_Source.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			Admin_Source parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getKey());
	}

}


