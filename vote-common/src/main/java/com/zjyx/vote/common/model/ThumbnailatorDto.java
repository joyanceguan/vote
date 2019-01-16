package com.zjyx.vote.common.model;

import net.coobird.thumbnailator.geometry.Positions;

public class ThumbnailatorDto {

	private String fromPath;//原图存放位置
	
	private String toPath;//目标存放位置
	
	private float scale;//缩放比例
	
	private int width;//宽度
	
	private int height;//高度
	
	private String watermark;//水印图片路径
	
	private boolean isAdapt;//是否根据图片大小算出缩放比例
	
	private String base64;
	
	private Positions positions;//水印位置

	public boolean isAdapt() {
		return isAdapt;
	}

	public void setAdapt(boolean isAdapt) {
		this.isAdapt = isAdapt;
	}

	public String getFromPath() {
		return fromPath;
	}

	public void setFromPath(String fromPath) {
		this.fromPath = fromPath;
	}

	public String getToPath() {
		return toPath;
	}

	public void setToPath(String toPath) {
		this.toPath = toPath;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getWatermark() {
		return watermark;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}
	
	public void calScale(int size){
		//当在1m和0.5m之间，则按1：1压缩
		if(1024*1024>size){
			this.setScale(1f);
		}else if(1024*1024*2>=size&&size>=1024*1024){
			this.setScale(0.5f);
		}else{
			this.setScale(0.3f);
		}
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public Positions getPositions() {
		return positions;
	}

	public void setPositions(Positions positions) {
		this.positions = positions;
	}
	
}
