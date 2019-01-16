package com.zjyx.vote.impl.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.exceptions.TransactionException;
import com.zjyx.vote.common.model.ReturnData;

@Component
@Aspect
@Order(1)
public class TransactionAop{
	
	private static final Logger errorLog = LoggerFactory.getLogger("errorLog");

	public final String POINT_CUT = "execution(public * com.zjyx.vote.api.transaction.*.*(..))";
	
	@SuppressWarnings({ "rawtypes", "unused" })
	@Around(POINT_CUT)
	public ReturnData process(ProceedingJoinPoint point){
		ReturnData returnData = null;
		Object[] args = point.getArgs();
		//错误记录error日志
		try {
			Object result = point.proceed();
			returnData = (ReturnData)result;
		} catch (TransactionException e) {
			errorLog.error("transaction aop exception", e);
			returnData = new ReturnData();
			returnData.setErrorCode(e.getErrorCode());
			returnData.setErrorMessage(e.getErrorMessage());
			returnData.setErrorType(e.getErrorType());
		} catch(Throwable e){
			errorLog.error("transaction aop throwable", e);
			returnData = new ReturnData();
			returnData.setErrorCode(null);
			returnData.setErrorMessage("transaction aop exception");
			returnData.setErrorType(Error_Type.SYSTEM_ERROR);
		}
		return returnData;
	}

}
