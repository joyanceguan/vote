package com.zjyx.vote.front.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zjyx.vote.api.model.dto.ResponseTimeDto;
import com.zjyx.vote.api.model.persistence.Vote;
import com.zjyx.vote.api.model.result.VoteResult;
import com.zjyx.vote.api.service.IVoteRecordService;
import com.zjyx.vote.api.service.IVoteService;
import com.zjyx.vote.common.model.ReturnData;
import com.zjyx.vote.front.utils.WebContextHelper;

@Controller
public class DetailController {

	@Resource
	IVoteService voteService;
	
	@Resource
	IVoteRecordService voteRecordService;
	
	@RequestMapping("/detail")
	public ModelAndView detail(Long id){
		ModelAndView mv = new ModelAndView("front/detail");
		ReturnData<Vote> returnData = voteService.selectById(id);
		Vote vote = returnData.getResultData();
		mv.addObject("vote", vote);
		return mv;
	}
	
	@RequestMapping("/random")
	public ModelAndView random(){
		ModelAndView mv = new ModelAndView("front/random");
		Long userId = WebContextHelper.getUserId();
		if(userId == null){
			mv.addObject("errorInfo", "随机投仅限登录用户使用");
			return mv;
		}
		ReturnData<Vote> returnData = voteService.random(userId);
		mv.addObject("vote", returnData.getResultData());
		return mv;
	}
	
    @ResponseBody
	@RequestMapping("/responseTimeCount")
    public ReturnData<List<ResponseTimeDto>> responseTimeCount(Long voteId){
    	return voteRecordService.responseTimeCount(voteId);
    }
    
    @ResponseBody
   	@RequestMapping("/chooseOptionsCount")
       public ReturnData<List<VoteResult>> chooseOptionsCount(Long voteId){
       	return voteRecordService.chooseOptionsCount(voteId);
    }
	
//	@ResponseBody
//	@RequestMapping("/randominfo")
//	public ReturnData<Vote> randomInfo(){
//		ReturnData<Vote> returnData = new ReturnData<Vote>();
//		Long userId = WebContextHelper.getUserId();
//		if(userId == null){
//			returnData.setErrorInfo(Error_Type.SERVICE_ERROR, null, "随机投仅限登录用户使用");
//			return returnData;
//		}
//		returnData = voteService.random(userId);
//		return returnData;
//	}
}
