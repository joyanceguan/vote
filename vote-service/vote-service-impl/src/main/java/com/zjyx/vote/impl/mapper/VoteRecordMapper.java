package com.zjyx.vote.impl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjyx.vote.api.model.condition.VoteRankListCdt;
import com.zjyx.vote.api.model.persistence.VoteRecord;
import com.zjyx.vote.api.model.result.VoteRecordResult;
import com.zjyx.vote.api.model.result.VoteResult;

public interface VoteRecordMapper {

	public int batchSave(@Param("table_name") String tableName,@Param("list") List<VoteRecord> list);
	
	public int save(VoteRecord voteRecord);
	
	public List<VoteResult> rankList(VoteRankListCdt condtion);
	
	public int rankListCount(VoteRankListCdt condtion);
	
	public List<Long> getVoteIdByUser(@Param("table_name") String tableName,@Param("user_id")Long userId);
	
	public List<VoteRecordResult> getByVoteIdUserId(@Param("vote_id") Long vote_id,@Param("user_id")Long userId,@Param("table_name") String tableName);

    public List<VoteResult> chooseOptionsCount(@Param("vote_id") Long vote_id,@Param("table_name") String tableName);

    public List<Integer> responseTimeCount(@Param("vote_id") Long vote_id,@Param("table_name") String tableName);
}
