package com.zjyx.vote.api.model.persistence;

import java.util.Date;

import com.zjyx.vote.api.model.enums.Vote_Option_Type;


public class VoteOption {

	//id
	private Long id;
    //投票id
	private Long vote_id;
	//选项
	private String option_desc;
	//选项类型
	private Vote_Option_Type vote_option_type;
	//排序
	private int sort;
	//创建时间
	private Date create_time;
	//修改时间
	private Date update_time;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVote_id() {
		return vote_id;
	}
	public void setVote_id(Long vote_id) {
		this.vote_id = vote_id;
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
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getOption_desc() {
		return option_desc;
	}
	public void setOption_desc(String option_desc) {
		this.option_desc = option_desc;
	}
}
