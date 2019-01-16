package com.zjyx.vote.api.model.dto;

import com.zjyx.vote.api.model.enums.Vote_Option_Type;

public class VoteOptionMini {

	//id
	private Long id;
	//选项
	private String option;
	//选项类型
	private Vote_Option_Type vote_option_type;
	//排序
	private int sort;
	
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public Vote_Option_Type getVote_option_type() {
		return vote_option_type;
	}
	public void setVote_option_type(Vote_Option_Type vote_option_type) {
		this.vote_option_type = vote_option_type;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
