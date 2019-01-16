package com.zjyx.vote.admin.viewmodel;

import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.ReturnData;

public class BaseVM{

	/**
	 * 错误类型
	 */
	private Error_Type errorType = Error_Type.SUCCESS;
	 
	/**
	 * 业务异常吗
	 */
	private String errorCode;
	 
    /**
     * 错误信息
     */
	private String errorMessage;

	public Error_Type getErrorType() {
		return errorType;
	}

	public void setErrorType(Error_Type errorType) {
		this.errorType = errorType;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	 
	@SuppressWarnings("rawtypes")
	public void setErrorInfo(ReturnData returnData){ 
		this.errorCode = returnData.getErrorCode();
		this.errorMessage = returnData.getErrorMessage();
		this.errorType = returnData.getErrorType();
	}
	
	public void setErrorInfo(Error_Type type,String errorCode,String errorMessage){ 
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorType = type;
	}
}
