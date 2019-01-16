package com.zjyx.vote.common.utils;

public class BinaryUtils {

	/**
	 * 判断二进制数据第n位是否等于1
	 * @param n 待判断的二进制数
	 * @param b 待判断的位（右往左）
	 * @return
	 */
	public static int bitValue(int n,int b){
		int x = (n>>(b-1)) & 1;
		return x;
	}
	
	public static void main(String[] args) {
		System.out.println(BinaryUtils.bitValue(0, 1));
	}
}
