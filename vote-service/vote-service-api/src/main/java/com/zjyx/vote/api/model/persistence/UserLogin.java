package com.zjyx.vote.api.model.persistence;

import java.util.Date;

import com.zjyx.vote.api.model.enums.User_Status;

public class UserLogin {
    
	//id
	private Long id;
	//用户名
	private String username;
	//邮箱
	private String email;
	//密码
	private String password;
	//最后登录时间
	private Date last_login_time;
	//状态
	private User_Status status;
	//创建时间
	private Date create_time;
	//修改时间
	private Date update_time;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public User_Status getStatus() {
		return status;
	}
	public void setStatus(User_Status status) {
		this.status = status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
