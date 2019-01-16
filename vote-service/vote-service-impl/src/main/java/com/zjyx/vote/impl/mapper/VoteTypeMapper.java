package com.zjyx.vote.impl.mapper;

import java.util.List;

import com.zjyx.vote.api.model.persistence.VoteType;

public interface VoteTypeMapper {

	public int save(VoteType voteType);
	
	public List<VoteType> selectAll();
	
	public int deleteById(Long id);
	
}
