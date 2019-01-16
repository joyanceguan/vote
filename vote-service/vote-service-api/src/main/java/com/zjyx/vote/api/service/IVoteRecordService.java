package com.zjyx.vote.api.service;

import java.util.List;

import com.zjyx.vote.api.model.condition.VoteRankListCdt;
import com.zjyx.vote.api.model.dto.ResponseTimeDto;
import com.zjyx.vote.api.model.persistence.User;
import com.zjyx.vote.api.model.persistence.VoteRecord;
import com.zjyx.vote.api.model.result.VoteResult;
import com.zjyx.vote.common.model.PageInfo;
import com.zjyx.vote.common.model.ReturnData;

public interface IVoteRecordService {

	
    /**
     * 根据条件排行	
     * @param cdt
     * @return
     */
	public PageInfo<VoteResult> rankList(VoteRankListCdt cdt);
	
	/**
	 * 保存投票记录
	 * @param records
	 * @param user
	 * @return
	 */
	public ReturnData<Integer> batchSave(List<VoteRecord> records,User user,Long voteId);
	
	/**
     * 响应时间统计	
     * @param voteId
     * @return
     */
	public ReturnData<List<ResponseTimeDto>> responseTimeCount(Long voteId);
	
	/**
	 * 投票选项统计
	 * @param voteId
	 * @return
	 */
	public ReturnData<List<VoteResult>> chooseOptionsCount(Long voteId);
}
