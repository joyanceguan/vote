package com.zjyx.vote.api.model.dto;

import java.util.Date;

import com.zjyx.vote.api.model.enums.Vote_Choose_Type;
import com.zjyx.vote.common.utils.DateUtils;

public class VoteDto {
	
	//投票id
    private Long voteId;
	//标题
    private String title;
    //投票开始时间
    private Date beginTime;
    //投票结束时间
    private Date endTime;
    //投票类型
  	private Vote_Choose_Type voteChooseType;
    //创建人id
  	private Long createUserId;
  	//投票说明
  	private String voteExplain;
  	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = DateUtils.parseDateFromString(beginTime,DateUtils.FORMAT3);
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime =  DateUtils.parseDateFromString(endTime,DateUtils.FORMAT3);
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public Long getVoteId() {
		return voteId;
	}
	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}
	public Vote_Choose_Type getVoteChooseType() {
		return voteChooseType;
	}
	public void setVoteChooseType(Vote_Choose_Type voteChooseType) {
		this.voteChooseType = voteChooseType;
	}
	public String getVoteExplain() {
		return voteExplain;
	}
	public void setVoteExplain(String voteExplain) {
		this.voteExplain = voteExplain;
	}
}
