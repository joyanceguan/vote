package com.zjyx.vote.impl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjyx.vote.api.model.condition.VoteCdtn;
import com.zjyx.vote.api.model.enums.Vote_Status;
import com.zjyx.vote.api.model.persistence.Vote;

public interface VoteMapper {

	public int save(Vote vote);
	
	public int update(Vote vote);
	
	public Vote selectById(Long id);
	
	public int delelteById(Long id);
	
	public List<Vote> list(VoteCdtn voteCdtn);
	
	public int count(VoteCdtn voteCdtn);
	
	public List<Vote> selectByIds(@Param("list")List<Long> list,@Param("sort")String sort,@Param("status")Vote_Status status);
	
	public int selectByIdsCount(List<Long> list);
	
}
