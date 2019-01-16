package com.zjyx.vote.common.constants;


public class VoteConstants {

	//默认每页展示条数
	public final static int DEFAULT_ONEPAGESIZE = 10;
	//密码盐值
	public final static String PASSWD_SALT = "U90KDFJ081C287PL";
	//session名
	public final static String USER_SESSION = "user";
	//后台session名
	public final static String ADMIN_USER_SESSION_NAME = "admin";
	//热门投票的时间限定(单位：分钟)
	public final static int HOT_VOTE_TIME = 24*60;
	//发送redis失败重试的次数
	public final static int RESEND_REDIS_TIME = 3;
	//热投显示前多少
	public final static int HOT_RANK = 100;
	//每个类型显示多少
	public final static int TYPE_RANK = 10;
	
	public static final int ONECE_MAX_CONTAINER = 5;//用户一次最多可上传几张图片
	public static final int MAX_FILE_SIZE = 2;//上传图片最大不能超过多少兆
}
