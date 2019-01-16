package com.zjyx.vote.impl.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.zjyx.vote.api.model.enums.Vote_Status;

public class VoteStatusHandler extends BaseTypeHandler<Vote_Status> {

	@Override
	public Vote_Status getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return Vote_Status.valueOf(rs.getInt(columnName));
	}

	@Override
	public Vote_Status getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return Vote_Status.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public Vote_Status getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return Vote_Status.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			Vote_Status parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getId());
	}

}
