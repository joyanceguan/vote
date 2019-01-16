package com.zjyx.vote.api.model.persistence;

import java.util.Date;

import com.zjyx.vote.api.model.enums.See_Type;
import com.zjyx.vote.api.model.enums.Sex;

public class VoteRecord {

	private String id;
	//投票id
	private Long vote_id;
	//选项id
	private Long option_id;
	//选项内容（冗余字段）
	private String option_desc;
	//用户id
	private Long user_id;
	//ip
	private String ip;
	//投票响应时间
	private Date response_time;
	//查看结果再投
	private See_Type see_type;
	//创建时间
	private Date create_time;
	//表名(非数据库字段名)
	private String table_name;
	//批次号（一次投票标志）
	private String batch_num;
	//=======================以下是做统计的用户冗余字段=======================
	//性别
	private Sex sex;
	//年龄
	private int age;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getVote_id() {
		return vote_id;
	}
	public void setVote_id(Long vote_id) {
		this.vote_id = vote_id;
	}
	public Long getOption_id() {
		return option_id;
	}
	public void setOption_id(Long option_id) {
		this.option_id = option_id;
	}
	public String getOption_desc() {
		return option_desc;
	}
	public void setOption_desc(String option_desc) {
		this.option_desc = option_desc;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Date getResponse_time() {
		return response_time;
	}
	public void setResponse_time(Date response_time) {
		this.response_time = response_time;
	}
	public See_Type getSee_type() {
		return see_type;
	}
	public void setSee_type(See_Type see_type) {
		this.see_type = see_type;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBatch_num() {
		return batch_num;
	}
	public void setBatch_num(String batch_num) {
		this.batch_num = batch_num;
	}
	
}
