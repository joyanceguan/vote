package com.zjyx.vote.impl.transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zjyx.vote.api.model.dto.VoteDto;
import com.zjyx.vote.api.model.dto.VoteOptionMini;
import com.zjyx.vote.api.model.dto.VoteRuleDto;
import com.zjyx.vote.api.model.enums.Vote_Option_Type;
import com.zjyx.vote.api.model.enums.Vote_Status;
import com.zjyx.vote.api.model.persistence.Vote;
import com.zjyx.vote.api.model.persistence.VoteOption;
import com.zjyx.vote.api.model.persistence.VoteTypeRelate;
import com.zjyx.vote.api.transaction.IVoteTransService;
import com.zjyx.vote.api.utils.VoteRuleUtils;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.exceptions.TransactionException;
import com.zjyx.vote.common.model.ReturnData;
import com.zjyx.vote.impl.mapper.VoteMapper;
import com.zjyx.vote.impl.mapper.VoteOptionMapper;
import com.zjyx.vote.impl.mapper.VoteTypeRelateMapper;

@Service
public class VoteTransSerivceImpl implements IVoteTransService{

	@Resource
	VoteMapper voteMapper;
	
	@Resource
	VoteOptionMapper voteOptionMapper;
	
	@Resource
	VoteTypeRelateMapper voteTypeRelateMapper;

	@Transactional("votetm")
	@Override
	public ReturnData<Vote> saveVote(VoteDto voteDto, VoteRuleDto voteRule, List<VoteOption> voteOptions,List<Integer> types) {
		ReturnData<Vote> returnData = new ReturnData<Vote>();
		if(voteDto == null || voteOptions == null || voteOptions.isEmpty()){
			throw new TransactionException(Error_Type.PARAM_ERROR,null,null);
		}
		if(StringUtils.isBlank(voteDto.getTitle())){
			throw new TransactionException(Error_Type.PARAM_ERROR,null,"标题不能为空");
		}
		if(voteDto.getVoteChooseType() == null){
			throw new TransactionException(Error_Type.PARAM_ERROR,null,"投票选择类型为空");
		}
		Date now = new Date();
		if(voteDto.getEndTime()!=null){
			if(voteDto.getEndTime().compareTo(now) < 0){
				throw new TransactionException(Error_Type.PARAM_ERROR,null,"无效的投票截止时间");
			}
			if(voteDto.getBeginTime()!=null){
				if(voteDto.getEndTime().compareTo(voteDto.getBeginTime())<0){
					throw new TransactionException(Error_Type.PARAM_ERROR,null,"无效的投票时间区间");
				}
			}
		}
		int limitType = 0;
		String limitRule = null;
		//限制规则判断
		ReturnData<VoteRuleDto> voteRuleReturnData = VoteRuleUtils.verifyVoteRuleDto(voteRule);
		Error_Type voteRuleErrorType = voteRuleReturnData.getErrorType();
		if(voteRuleErrorType == Error_Type.SERVICE_ERROR){
			voteRule = null;
		}else if(voteRuleErrorType == Error_Type.PARAM_ERROR){
			throw new TransactionException(Error_Type.PARAM_ERROR,null,voteRuleReturnData.getErrorMessage());
		}
		limitType = VoteRuleUtils.getLimitType(voteRule);
	    limitRule = JSON.toJSONString(voteRule);
		List<VoteOptionMini> list = new ArrayList<VoteOptionMini>();
		for(VoteOption voteOption : voteOptions){
			Vote_Option_Type type = voteOption.getVote_option_type();
			if(type == null) {
			   	throw new TransactionException(Error_Type.PARAM_ERROR,null,"选项类型为空");
			}
			if(StringUtils.isBlank(voteOption.getOption_desc())){
				throw new TransactionException(Error_Type.PARAM_ERROR,null,"选项内容不能为空");
			}
		}
		if(voteDto.getBeginTime()!=null){
			if(voteDto.getBeginTime().compareTo(now) < 0){
				voteDto.setBeginTime(now);
			}
		}
		Vote vote = new Vote();
		vote.setTitle(voteDto.getTitle());
		vote.setVote_choose_type(voteDto.getVoteChooseType());
		vote.setBegin_time(voteDto.getBeginTime());
		vote.setEnd_time(voteDto.getEndTime());
		vote.setLimit_rule(limitRule);
		vote.setLimit_type(limitType);
		vote.setStatus(Vote_Status.close);
		vote.setVote_explain(voteDto.getVoteExplain());
		if(voteDto.getCreateUserId() == null){
			//-1代表系统创建
			vote.setCreate_user_id(-1L);
		}
		int flag = 0;
		if(voteDto.getVoteId() == null){
			vote.setOption_mini("null");//下面会修改
			flag = voteMapper.save(vote);
			if(flag < 1){
				throw new TransactionException(Error_Type.SYSTEM_ERROR,null,"保存投票失败");
			}
		}else{
			vote.setId(voteDto.getVoteId());
			//删除所有投票选项
			flag = voteOptionMapper.deleteByVoteId(voteDto.getVoteId());
			//删除所有关联类型
			flag = voteTypeRelateMapper.deleteByVoteId(voteDto.getVoteId());
		}
		for(VoteOption voteOption : voteOptions){
			voteOption.setVote_id(vote.getId());
		}
		flag = voteOptionMapper.batchSave(voteOptions);
		if(voteOptions.size()!=flag){
			throw new TransactionException(Error_Type.SYSTEM_ERROR,null,"保存选项失败");
		}
		voteOptions = voteOptionMapper.selectByVoteId(vote.getId());
		for(VoteOption voteOption : voteOptions){
			VoteOptionMini voteOptionMini = new VoteOptionMini();
			voteOptionMini.setOption(voteOption.getOption_desc());
			voteOptionMini.setSort(voteOption.getSort());
			voteOptionMini.setVote_option_type(voteOption.getVote_option_type());
			voteOptionMini.setId(voteOption.getId());
			list.add(voteOptionMini);
		}
		Collections.sort(list, new Comparator<VoteOptionMini>(){
		    @Override
		    public int compare(VoteOptionMini o1, VoteOptionMini o2) {
		    	if(o1.getSort() > o2.getSort()){
		    		return 1;
		    	}else
		    		return -1;
		}});
		vote.setOption_mini(JSON.toJSONString(list));
		flag = voteMapper.update(vote);
		if(flag < 1){
			throw new TransactionException(Error_Type.SYSTEM_ERROR,null,"修改投票失败");
		}
		//插入类型投票关联表
		//类型关联
        List<VoteTypeRelate> typeRelateList = new ArrayList<VoteTypeRelate>();
        for(Integer type:types){
        	VoteTypeRelate voteTypeRelate = new VoteTypeRelate();
        	voteTypeRelate.setType_id(type);
        	voteTypeRelate.setVote_id(vote.getId());
        	typeRelateList.add(voteTypeRelate);
        }
		flag = voteTypeRelateMapper.batchSave(typeRelateList);
		if(flag != types.size()){
			throw new TransactionException(Error_Type.SYSTEM_ERROR,null,"投票类型关联失败");
		}
		returnData.setResultData(vote);
		return returnData;
	}
	
//	private List<VoteOption> getRemoves(List<VoteOption> a,List<VoteOption> b){
//		//修改需要注意修改选项
////		List<VoteOption> voteOptionResult = voteOptionMapper.selectByVoteId(voteDto.getVoteId());
////		List<VoteOption>  dbRemoves = getRemoves(voteOptionResult,voteOptions);
////		List<VoteOption>  dbAdds = getRemoves(voteOptions,voteOptionResult);
////		if(!dbRemoves.isEmpty()){
////			List<Long> removeIds = new ArrayList<Long>();
////			for(VoteOption voteOption : dbRemoves){
////				removeIds.add(voteOption.getId());
////			}
////			voteOptionMapper.deleteByIds(removeIds);
////		}
////		if(!dbAdds.isEmpty()){
////			voteOptionMapper.batchSave(dbAdds);
////		}
//		List<VoteOption> removeVotes = new ArrayList<VoteOption>();
//		for(int i = 0;i < a.size();i++){
//			VoteOption dbVoteOption = a.get(i);
//			for(int j = 0;j < b.size();j++){
//				VoteOption voteOption = b.get(j);
//				if(dbVoteOption.getId().longValue() ==  voteOption.getId().longValue()){
//					break;
//				}
//				//到最后都没找到
//				if(j == b.size() - 1){
//					removeVotes.add(dbVoteOption);
//				}
//			}
//		}
//		return removeVotes;
//	}

}
