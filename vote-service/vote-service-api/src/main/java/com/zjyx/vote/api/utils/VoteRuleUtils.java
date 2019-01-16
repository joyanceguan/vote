package com.zjyx.vote.api.utils;

import com.zjyx.vote.api.model.dto.VoteRuleDto;
import com.zjyx.vote.common.constants.ErrorCode;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.ReturnData;

public class VoteRuleUtils {

	/**
	 * @param voteRule
	 * 返回限制类型，从右往左数 第一位：isLoginLimit 第二位：everyoneTotalLimit 第三位：everyoneRateLimit 第四位：ipTotalLimit 第五位：ipRateLimit
	 */
	public static int getLimitType(VoteRuleDto voteRule){
		if(voteRule == null){
			return 0;
		}
		int limitType = 0;
		//第一位
		if(voteRule.isLoginLimit()){
			limitType += 1;
		}
		//第二位
		if(voteRule.isEveryoneTotalLimit()){
			limitType += 1 << 1;
		}
		//第三位
		if(voteRule.isEveryoneTotalLimit()){
			limitType += 1 << 2;
		}
		//第四位
		if(voteRule.isIpTotalLimit()){
			limitType += 1 << 3;
		}
		//第五位
		if(voteRule.isIpRateLimit()){
			limitType += 1 << 4;
		}
		return limitType;
	}
	
	/**
	 * @param voteRule
	 * 验证投票规则是否格式正确
	 */
	public static ReturnData<VoteRuleDto> verifyVoteRuleDto(VoteRuleDto voteRule){
		ReturnData<VoteRuleDto> returnData = new ReturnData<VoteRuleDto>();
		if(voteRule == null){
			returnData.setErrorInfo(Error_Type.SERVICE_ERROR, ErrorCode.NO_RESULT, "无返回结果");
		}else{
			//如果没有登录限制，也没有ip限制，就算没有任何限制
			if(!voteRule.isLoginLimit() && !(voteRule.isIpTotalLimit() || voteRule.isIpRateLimit())){
				returnData.setErrorInfo(Error_Type.SERVICE_ERROR, ErrorCode.NO_RESULT, "无返回结果");
			}
			//如果有登录且有限制
			else if(voteRule.isLoginLimit() && (voteRule.isEveryoneTotalLimit() || voteRule.isEveryoneRateLimit())){
				//如果是每人投票频率限制
				if(voteRule.isEveryoneRateLimit()){
					int minute = voteRule.getEveryoneTime();
					int count = voteRule.getEveryoneRateCount();
					if(minute <  1 || count < 1){
						returnData.setErrorInfo(Error_Type.PARAM_ERROR, null, "无效的登录频率限制");
					}
				}
				//如果是每人投票总数限制
				else if(voteRule.isEveryoneTotalLimit()){
					int count = voteRule.getEveryoneCount();
					if(count < 1){
						returnData.setErrorInfo(Error_Type.PARAM_ERROR, null, "无效的每人限投次数");
					}
				}
			}
			//未登录但有限制
			else if(!voteRule.isLoginLimit() && (voteRule.isIpTotalLimit() || voteRule.isIpRateLimit())){
				//如果是每个ip投票频率限制
				if(voteRule.isIpRateLimit()){
					int minute = voteRule.getIpTime();
					int count = voteRule.getIpRateCount();
					if(minute <  1 || count < 1){
						returnData.setErrorInfo(Error_Type.PARAM_ERROR, null, "无效的ip频率限制");
					}
				}
				//如果是每个ip投票总数限制
				if(voteRule.isIpTotalLimit()){
					int count = voteRule.getIpCount();
					if(count < 1){
						returnData.setErrorInfo(Error_Type.PARAM_ERROR, null, "无效的每个ip限投次数");
					}
				}
			}
		}
		return returnData;
	}
	
}
