package com.zjyx.vote.api.model.persistence;

public class RankVote {

	private Long vote_id;
	
	private Long vote_count;

	public Long getVote_id() {
		return vote_id;
	}

	public void setVote_id(Long vote_id) {
		this.vote_id = vote_id;
	}

	public Long getVote_count() {
		return vote_count;
	}

	public void setVote_count(Long vote_count) {
		this.vote_count = vote_count;
	}
	
}
