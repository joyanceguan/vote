package com.zjyx.vote.impl.mapper;

import java.util.List;

import com.zjyx.vote.api.model.persistence.VoteOption;

public interface VoteOptionMapper {

	public int batchSave(List<VoteOption> list);
	
	public int batchUpdate(List<VoteOption> list);
	
	public List<VoteOption> selectByVoteId(Long voteId);
	
	public int deleteById(Long id);
	
	public int deleteByIds(List<Long> list);
	
	public int deleteByVoteId(Long id);
}
