package com.zjyx.authority.api.model.result;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zjyx.authority.api.model.persistence.AdminUser;

public class AdminUserResult extends AdminUser{

	private String real_name;//姓名
	private int sex;//性别  1:�? 2:�?
	private int age;//年龄
	private String organization;//组织
	private String email;//邮件
	private String mobile;//电话
	private String address;//地址
	private Date create_time;//创建时间
	private Date update_time;//修改时间
	
	private String role_name;//角色名称
	
	private String province;//省份
	private String city;//城市
	private String country;//国家
	
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@SuppressWarnings("unchecked")
	public static Map<Integer,AdminUserResult> fromListToMap(List<AdminUserResult> list){
		if(list == null || list.size() == 0){
			return Collections.EMPTY_MAP;
		}
		Map<Integer,AdminUserResult> map = new HashMap<Integer,AdminUserResult>();
		for(AdminUserResult adminUserResult:list){
			map.put(adminUserResult.getId(), adminUserResult);
		}
		return map;
	}
}
