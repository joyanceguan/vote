package com.zjyx.vote.impl.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.zjyx.vote.api.model.enums.User_Source;


public class UserSourceTypeHandler extends BaseTypeHandler<User_Source> {

	@Override
	public User_Source getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return User_Source.valueOf(rs.getInt(columnName));
	}

	@Override
	public User_Source getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return User_Source.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public User_Source getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return User_Source.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			User_Source parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getId());
	}

}

