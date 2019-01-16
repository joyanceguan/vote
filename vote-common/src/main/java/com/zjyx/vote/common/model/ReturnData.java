package com.zjyx.vote.common.model;

import com.zjyx.vote.common.enums.Error_Type;

public class ReturnData<T> {

  /**
   * 返回值
   */
   protected T resultData;
   
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

   public T getResultData() {
   	return resultData;
   }
   
   public void setResultData(T resultData) {
   	this.resultData = resultData;
   }
   
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
   
   public void setErrorInfo(Error_Type type,String errorCode,String errorMessage){ 
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorType = type;
  }
}
