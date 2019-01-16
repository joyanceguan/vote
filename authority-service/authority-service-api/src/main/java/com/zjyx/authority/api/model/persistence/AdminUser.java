package com.zjyx.authority.api.model.persistence;

import java.util.Date;

import com.zjyx.authority.api.model.enums.Admin_Source;
import com.zjyx.authority.api.model.enums.UpDown_Status;

public class AdminUser {

	private Integer id;//id
	private String username;//用户名（目前是微
	private String user_code;//用户code
	private UpDown_Status status;
    private Admin_Source source;//来源
    private Integer role_id;//角色id
	private Date create_time;//创建时间
	private Date update_time;//修改时间
	private String headimgurl;//头像
	
	private String unionid;//unionid
	private String openid;//openid
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public UpDown_Status getStatus() {
		return status;
	}
	public void setStatus(UpDown_Status status) {
		this.status = status;
	}
	public Admin_Source getSource() {
		return source;
	}
	public void setSource(Admin_Source source) {
		this.source = source;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
    
}
