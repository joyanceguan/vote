package com.zjyx.vote.api.service;

import java.util.List;

import com.zjyx.vote.api.model.condition.SexRankCdt;
import com.zjyx.vote.api.model.condition.VoteCdtn;
import com.zjyx.vote.api.model.condition.VoteTypeCdn;
import com.zjyx.vote.api.model.enums.Vote_Status;
import com.zjyx.vote.api.model.persistence.Vote;
import com.zjyx.vote.common.model.BasePageCondition;
import com.zjyx.vote.common.model.PageInfo;
import com.zjyx.vote.common.model.ReturnData;

public interface IVoteService {

	public PageInfo<Vote> list(VoteCdtn voteCdtn);
	
	public ReturnData<Vote> selectById(Long id);
	
	public ReturnData<Integer> updateStatus(Long id,Vote_Status vote_Status);
	
	public ReturnData<Integer> deleteById(Long id);
	
	//分类
	public PageInfo<Vote> typeList(VoteTypeCdn condition);
	
	//===============================以下为首页的各种接口===============================
	
	//热投
	public ReturnData<List<Vote>> hotList();
	
	//榜单投
	public PageInfo<Vote> rankList(BasePageCondition condition);
	
	//随机投
	public ReturnData<Vote> random(Long userId);
	
	//分类投
	public PageInfo<Vote> typeWithRankList(VoteTypeCdn condition);
	
	//根据用户行为统计投（目前写死只有性别，需要优化）
	public PageInfo<Vote> sexRankList(SexRankCdt condition);
	
}
