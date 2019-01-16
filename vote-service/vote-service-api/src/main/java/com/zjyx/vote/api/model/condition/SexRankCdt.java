package com.zjyx.vote.api.model.condition;

import com.zjyx.vote.api.model.enums.Sex;
import com.zjyx.vote.common.model.BasePageCondition;

public class SexRankCdt extends BasePageCondition{

	private Sex sex;

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
}
