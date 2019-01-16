package com.zjyx.vote.api.model.persistence;

public class VoteTypeRelate {

	private Long id;
	private Integer type_id;
	private Long vote_id;
	
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
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	
}
