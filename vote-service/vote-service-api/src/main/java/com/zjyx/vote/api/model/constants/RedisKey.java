package com.zjyx.vote.api.model.constants;

public class RedisKey {

	//排行榜（无时间限制，按用户访问量倒序排列）
	public final static String VOTE_RANK_KEY = "VOTE:VOTE_RANK";

	//热投（有时间限制，按用户访问量倒序排列前100）
	public final static String VOTE_HOT_KEY = "VOTE:VOTE_HOT";
	
	//保存投票记录临时列表，没投一次插入一次，每一段时间将从redis批量更新到数据库
	public final static String VOTERECORD_SAVE_KEY = "VOTE:VOTERECORD_SAVE";
	
	//类型前缀，存放类型排行
	public final static String TYPE_PREFIX = "VOTE:TYPE_";
	
	//性别存放前缀
	public final static String SEX_PREFIX = "VOTE:SEX_";
}
