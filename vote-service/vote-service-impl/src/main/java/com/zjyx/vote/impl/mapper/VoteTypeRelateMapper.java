package com.zjyx.vote.impl.mapper;

import java.util.List;

import com.zjyx.vote.api.model.persistence.VoteTypeRelate;
import com.zjyx.vote.api.model.result.VoteTypeResult;

public interface VoteTypeRelateMapper {

	public int batchSave(List<VoteTypeRelate> list);
	
	public List<Long> selectByTypeId(int type_id);
	
	public List<Integer> selectByVoteId(Long vote_id);
	
	public int deleteByVoteId(Long vote_id);
	
	public List<VoteTypeResult> selectByVoteIds(List<Long> list);
	
}
