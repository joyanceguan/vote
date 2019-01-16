package com.zjyx.vote.front.param;

import java.util.Date;
import java.util.List;

import com.zjyx.vote.api.model.enums.See_Type;
import com.zjyx.vote.front.entity.VoteOptionEntity;

public class VoteRecordParam {

	//选项
	private List<VoteOptionEntity> voteOptions;
	
	//投票id
	private Long vote_id; 
	
	//投票响应时间
	private Date response_time;
	
	//查看结果再投
	private See_Type see_type;

	public List<VoteOptionEntity> getVoteOptions() {
		return voteOptions;
	}

	public void setVoteOptions(List<VoteOptionEntity> voteOptions) {
		this.voteOptions = voteOptions;
	}

	public Long getVote_id() {
		return vote_id;
	}

	public void setVote_id(Long vote_id) {
		this.vote_id = vote_id;
	}

	public Date getResponse_time() {
		return response_time;
	}

	public void setResponse_time(Date response_time) {
		this.response_time = response_time;
	}

	public See_Type getSee_type() {
		return see_type;
	}

	public void setSee_type(See_Type see_type) {
		this.see_type = see_type;
	}
	
	
}
