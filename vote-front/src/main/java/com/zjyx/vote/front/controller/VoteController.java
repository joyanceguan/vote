package com.zjyx.vote.front.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjyx.vote.api.model.persistence.User;
import com.zjyx.vote.api.model.persistence.VoteRecord;
import com.zjyx.vote.api.service.IVoteRecordService;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.BaseVM;
import com.zjyx.vote.common.utils.IPUtils;
import com.zjyx.vote.front.entity.VoteOptionEntity;
import com.zjyx.vote.front.param.VoteRecordParam;
import com.zjyx.vote.front.utils.WebContextHelper;

@Controller
public class VoteController {

	@Resource
	IVoteRecordService voteRecordService;
	
	@RequestMapping("/save")
	@ResponseBody
	public BaseVM save(HttpServletRequest request,VoteRecordParam param){
		BaseVM baseVM = new BaseVM();
		if(param == null){
			baseVM.setErrorInfo(Error_Type.PARAM_ERROR, null, null);
			return baseVM;
		}
		List<VoteOptionEntity> voteOptions = param.getVoteOptions();
		if(voteOptions == null || voteOptions.isEmpty()){
			baseVM.setErrorInfo(Error_Type.PARAM_ERROR, null, null);
			return baseVM;
		}
		if(param.getResponse_time() == null){
			baseVM.setErrorInfo(Error_Type.PARAM_ERROR, null, null);
			return baseVM;
		}
		if(param.getSee_type() == null){
			baseVM.setErrorInfo(Error_Type.PARAM_ERROR, null, null);
			return baseVM;
		}
		if(param.getVote_id() == null){
			baseVM.setErrorInfo(Error_Type.PARAM_ERROR, null, null);
			return baseVM;
		}
		List<VoteRecord> recordList = new ArrayList<VoteRecord>();
		Date now = new Date();
		for(VoteOptionEntity voteOption:voteOptions){
			if(StringUtils.isBlank(voteOption.getOptionDesc())){
				baseVM.setErrorInfo(Error_Type.PARAM_ERROR, null, null);
				return baseVM;
			}
			if(voteOption.getOptionId() == null || voteOption.getOptionId()<1){
				baseVM.setErrorInfo(Error_Type.PARAM_ERROR, null, null);
				return baseVM;
			}
			VoteRecord voteRecord = new VoteRecord();
			voteRecord.setCreate_time(now);
			voteRecord.setIp(IPUtils.getIpAddr(request));
			voteRecord.setOption_desc(voteOption.getOptionDesc());
			voteRecord.setOption_id(voteOption.getOptionId());
			voteRecord.setResponse_time(param.getResponse_time());
			voteRecord.setSee_type(param.getSee_type());
			recordList.add(voteRecord); 
		}
		User user = null;
		if(WebContextHelper.isLogin()){
	       user = WebContextHelper.getUser().getUser();
		}
		voteRecordService.batchSave(recordList, user, param.getVote_id());
		return baseVM;
	}
}
