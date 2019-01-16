package com.zjyx.vote.api.model.persistence;

import com.zjyx.vote.api.model.enums.UpDown_Status;

public class Carousel {

	private Long id;//id
	private String image_url;//图片地址
	private int sort;//排序
	private String link_url;//跳转链接
	private String image_desc;//图片描述
	private UpDown_Status status;//状态
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	public String getImage_desc() {
		return image_desc;
	}
	public void setImage_desc(String image_desc) {
		this.image_desc = image_desc;
	}
	public UpDown_Status getStatus() {
		return status;
	}
	public void setStatus(UpDown_Status status) {
		this.status = status;
	}
	
}
