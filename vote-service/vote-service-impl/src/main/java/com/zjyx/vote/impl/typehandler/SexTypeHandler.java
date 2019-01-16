package com.zjyx.vote.impl.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.zjyx.vote.api.model.enums.Sex;


public class SexTypeHandler extends BaseTypeHandler<Sex> {

	@Override
	public Sex getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return Sex.valueOf(rs.getInt(columnName));
	}

	@Override
	public Sex getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return Sex.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public Sex getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return Sex.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			Sex parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getId());
	}

}
