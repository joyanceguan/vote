package com.zjyx.vote.impl.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.zjyx.vote.api.model.enums.Vote_Choose_Type;

public class VoteChooseTypeHandler extends BaseTypeHandler<Vote_Choose_Type> {

	@Override
	public Vote_Choose_Type getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return Vote_Choose_Type.valueOf(rs.getInt(columnName));
	}

	@Override
	public Vote_Choose_Type getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return Vote_Choose_Type.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public Vote_Choose_Type getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return Vote_Choose_Type.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			Vote_Choose_Type parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getId());
	}

}
