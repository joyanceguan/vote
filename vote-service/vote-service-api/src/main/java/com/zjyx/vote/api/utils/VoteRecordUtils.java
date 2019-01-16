package com.zjyx.vote.api.utils;

import com.zjyx.vote.api.model.enums.Vote_Choose_Type;
import com.zjyx.vote.api.model.persistence.Vote;
import com.zjyx.vote.common.utils.BinaryUtils;

public class VoteRecordUtils {
	
	public static final String VOTE_RECORD_1 = "vote_record_1";
	public static final String VOTE_RECORD_2 = "vote_record_2";
	public static final String VOTE_RECORD_3 = "vote_record_3";
	public static final String VOTE_RECORD_4 = "vote_record_4";
	
	/**
	 * 投票记录表做分表
	 * vote_record_1:单选 登录 
	 * vote_record_2:多选 登录
	 * vote_record_3:单选 无需登录
	 * vote_record_4:多选 无需登录
	 * 根据投票信息决定投票记录放入哪个表中
	 * @param vote
	 * @return
	 */
	public static String getRecordTableName(Vote vote){
		String tableName = null;
		//获取选择类型
		Vote_Choose_Type chooseType = vote.getVote_choose_type();
		//获取是否登录
		int limitType = vote.getLimit_type();
		int bitValue = BinaryUtils.bitValue(limitType, 1);
		if(chooseType == Vote_Choose_Type.single){
		   //有登录限制
		   if(bitValue == 1){
			   tableName = VOTE_RECORD_1;
		   }else{
			   tableName = VOTE_RECORD_3;
		   }
		}else{
			//有登录限制
			if(bitValue == 1){
				tableName = VOTE_RECORD_2;
			}else{
				tableName = VOTE_RECORD_4;
			}
		}
		return tableName;
	}
	
	public static String[] getAllTables(){
		String[] array = new String[]{VOTE_RECORD_1,VOTE_RECORD_2,VOTE_RECORD_3,VOTE_RECORD_4};
		return array;
	}
}
