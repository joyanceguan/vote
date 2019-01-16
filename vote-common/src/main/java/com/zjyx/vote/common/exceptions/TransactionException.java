package com.zjyx.vote.common.exceptions;

import com.zjyx.vote.common.enums.Error_Type;

public class TransactionException extends RuntimeException{

	private static final long serialVersionUID = -5656136902391612647L;

	/**
     * 错误类型
     */
    protected Error_Type errorType = Error_Type.SUCCESS;
    
    /**
     * 业务异常吗
     */
    protected String errorCode;
    
    /**
     * 错误信息
     */
    protected String errorMessage;
	   
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

	public TransactionException(Error_Type errorType,String errorCode,String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorType = errorType;
	}
	
	
}
