package com.zjyx.vote.impl.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.zjyx.vote.api.model.enums.Vote_Option_Type;

public class VoteOptionTypeHandler extends BaseTypeHandler<Vote_Option_Type> {

	@Override
	public Vote_Option_Type getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return Vote_Option_Type.valueOf(rs.getInt(columnName));
	}

	@Override
	public Vote_Option_Type getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return Vote_Option_Type.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public Vote_Option_Type getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return Vote_Option_Type.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			Vote_Option_Type parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getId());
	}

}


