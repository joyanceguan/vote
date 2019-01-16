package com.zjyx.vote.admin.param;

import java.util.List;

import com.zjyx.vote.api.model.dto.VoteDto;
import com.zjyx.vote.api.model.dto.VoteRuleDto;
import com.zjyx.vote.api.model.persistence.VoteOption;


public class VoteParam {

   private VoteDto vote;
  
   private List<VoteOption> options;
   
   private VoteRuleDto voteRule;
   
   private List<Integer> types;
   
   public VoteDto getVote() {
	  return vote;
   }

   public void setVote(VoteDto vote) {
	  this.vote = vote;
   }

   public List<VoteOption> getOptions() {
	  return options;
   }

   public void setOptions(List<VoteOption> options) {
	  this.options = options;
   }

   public VoteRuleDto getVoteRule() {
	  return voteRule;
   }

   public void setVoteRule(VoteRuleDto voteRule) {
	  this.voteRule = voteRule;
   }

   public List<Integer> getTypes() {
	  return types;
   }

   public void setTypes(List<Integer> types) {
	  this.types = types;
   }

}
