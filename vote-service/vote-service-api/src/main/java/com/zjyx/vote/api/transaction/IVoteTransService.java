package com.zjyx.vote.api.transaction;

import java.util.List;

import com.zjyx.vote.api.model.dto.VoteDto;
import com.zjyx.vote.api.model.dto.VoteRuleDto;
import com.zjyx.vote.api.model.persistence.Vote;
import com.zjyx.vote.api.model.persistence.VoteOption;
import com.zjyx.vote.common.model.ReturnData;


public interface IVoteTransService {

	public ReturnData<Vote> saveVote(VoteDto voteDto,VoteRuleDto ruleDto,List<VoteOption> voteOptions,List<Integer> types);

}
