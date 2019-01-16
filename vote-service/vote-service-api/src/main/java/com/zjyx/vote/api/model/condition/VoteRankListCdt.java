package com.zjyx.vote.api.model.condition;

import java.util.Date;
import java.util.List;

import com.zjyx.vote.api.model.enums.Sex;
import com.zjyx.vote.common.model.BasePageCondition;

public class VoteRankListCdt extends BasePageCondition{
	
	private String table_name;

	private Date beginTime;
	
	private List<Long> voteIds;
	
	private Sex sex;//性别

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public List<Long> getVoteIds() {
		return voteIds;
	}

	public void setVoteIds(List<Long> voteIds) {
		this.voteIds = voteIds;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
}
