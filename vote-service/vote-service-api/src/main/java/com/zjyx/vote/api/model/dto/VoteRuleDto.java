package com.zjyx.vote.api.model.dto;


public class VoteRuleDto {

	//登录限制
	private boolean loginLimit;
	
	
	//是否每人投票总数限制
	private boolean everyoneTotalLimit;
	//每人限投次数（和everyoneTotalLimit联用，everyoneTotalLimit = true时，everyoneCount有效）
    private int everyoneCount;
    //是否每人投票频率限制
    private boolean everyoneRateLimit;
    //everyoneRateLimit = true时，每人everyoneTime内限制能投几次（如一个人，1天能投5次，那么everyoneTime = 24*60，everyoneRateCount =5）
    private int everyoneTime;
    //everyoneRateLimit = true时，每人everyoneTime内限制能投几次（如一个人，1天能投5次，那么everyoneTime = 24*60，everyoneRateCount =5）
    private int everyoneRateCount;
    
    
	//是否ip总数限制
	private boolean ipTotalLimit;
	//每个ip限投次数（和ipTotalLimit联用，ipTotalLimit = true时，ipCount有效）
    private int ipCount;
    //是否每个ip投票频率限制
    private boolean ipRateLimit;
    //ipRateLimit = true时，每人ipTime内限制能投几次（如一个ip，1天能投5次，那么ipTime = 24*60，ipRateCount =5）
    private int ipTime;
    //ipRateLimit = true时，每人ipTime内限制能投几次（如一个ip，1天能投5次，那么ipTime = 24*60，ipRateCount =5）
    private int ipRateCount;
    
    
	public boolean isLoginLimit() {
		return loginLimit;
	}
	public void setLoginLimit(boolean loginLimit) {
		this.loginLimit = loginLimit;
	}
	public boolean isEveryoneTotalLimit() {
		return everyoneTotalLimit;
	}
	public void setEveryoneTotalLimit(boolean everyoneTotalLimit) {
		this.everyoneTotalLimit = everyoneTotalLimit;
	}
	public int getEveryoneCount() {
		return everyoneCount;
	}
	public void setEveryoneCount(int everyoneCount) {
		this.everyoneCount = everyoneCount;
	}
	public boolean isEveryoneRateLimit() {
		return everyoneRateLimit;
	}
	public void setEveryoneRateLimit(boolean everyoneRateLimit) {
		this.everyoneRateLimit = everyoneRateLimit;
	}
	public int getEveryoneRateCount() {
		return everyoneRateCount;
	}
	public void setEveryoneRateCount(int everyoneRateCount) {
		this.everyoneRateCount = everyoneRateCount;
	}
	public boolean isIpTotalLimit() {
		return ipTotalLimit;
	}
	public void setIpTotalLimit(boolean ipTotalLimit) {
		this.ipTotalLimit = ipTotalLimit;
	}
	public int getIpCount() {
		return ipCount;
	}
	public void setIpCount(int ipCount) {
		this.ipCount = ipCount;
	}
	public boolean isIpRateLimit() {
		return ipRateLimit;
	}
	public void setIpRateLimit(boolean ipRateLimit) {
		this.ipRateLimit = ipRateLimit;
	}
	public int getIpRateCount() {
		return ipRateCount;
	}
	public void setIpRateCount(int ipRateCount) {
		this.ipRateCount = ipRateCount;
	}
	public int getEveryoneTime() {
		return everyoneTime;
	}
	public void setEveryoneTime(int everyoneTime) {
		this.everyoneTime = everyoneTime;
	}
	public int getIpTime() {
		return ipTime;
	}
	public void setIpTime(int ipTime) {
		this.ipTime = ipTime;
	}
    
}
