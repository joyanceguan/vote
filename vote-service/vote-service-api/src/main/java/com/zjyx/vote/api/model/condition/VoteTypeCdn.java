package com.zjyx.vote.api.model.condition;

import com.zjyx.vote.api.model.enums.Vote_Status;
import com.zjyx.vote.common.model.BasePageCondition;

public class VoteTypeCdn extends BasePageCondition{

	private int typeId;
	
	private Vote_Status status;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public Vote_Status getStatus() {
		return status;
	}

	public void setStatus(Vote_Status status) {
		this.status = status;
	}
	
	
}
