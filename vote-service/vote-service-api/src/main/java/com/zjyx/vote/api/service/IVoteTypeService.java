package com.zjyx.vote.api.service;

import java.util.List;

import com.zjyx.vote.api.model.persistence.VoteType;
import com.zjyx.vote.common.model.ReturnData;

public interface IVoteTypeService {

	public ReturnData<Integer> save(String name);
	
	public ReturnData<List<VoteType>> selectAll();
	
	public ReturnData<Integer> deleteById(Long id);
	
}
