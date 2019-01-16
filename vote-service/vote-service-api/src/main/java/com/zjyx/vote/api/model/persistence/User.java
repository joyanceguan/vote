package com.zjyx.vote.api.model.persistence;

import java.util.Date;

import com.zjyx.vote.api.model.enums.Sex;
import com.zjyx.vote.api.model.enums.User_Source;


public class User {

	//id
	private Long id;
	//邮箱
	private String email;
	//性别
	private Sex sex;
	//年龄
	private int age;
	//姓名
	private String name;
	//用户名(冗余字段)
	private String username;
	//住址
	private String address;
	//头像
	private String head_image;
	//创建时间
	private Date create_time;
	//修改时间
	private Date update_time;
    //来源
	private User_Source source;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHead_image() {
		return head_image;
	}
	public void setHead_image(String head_image) {
		this.head_image = head_image;
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
	public User_Source getSource() {
		return source;
	}
	public void setSource(User_Source source) {
		this.source = source;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
