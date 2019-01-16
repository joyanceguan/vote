package com.zjyx.vote.front.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zjyx.vote.api.model.condition.SexRankCdt;
import com.zjyx.vote.api.model.condition.VoteCdtn;
import com.zjyx.vote.api.model.enums.Sex;
import com.zjyx.vote.api.model.enums.Vote_Status;
import com.zjyx.vote.api.model.persistence.Vote;
import com.zjyx.vote.api.service.IVoteService;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.BasePageCondition;
import com.zjyx.vote.common.model.PageInfo;

@Controller
public class IndexController {

	@Resource
	IVoteService voteService;
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("front/index");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/allList")
	public PageInfo<Vote> getAllList(BasePageCondition condition){
		VoteCdtn cdt = new VoteCdtn();
		cdt.setCurrentPage(condition.getCurrentPage());
		cdt.setOnePageSize(condition.getOnePageSize());
		cdt.setStatus(Vote_Status.normal);
		PageInfo<Vote> pageInfo = voteService.list(cdt);
		return pageInfo;
	}
	
	@ResponseBody
	@RequestMapping("/sexrank")
	public PageInfo<Vote> sexRank(SexRankCdt condition){
		PageInfo<Vote> vm = new PageInfo<Vote>();
		if(condition.getSex() == null || condition.getSex() == Sex.unknown){
			vm.setErrorType(Error_Type.PARAM_ERROR);
			return vm;
		}
		vm = voteService.sexRankList(condition);
		return vm;
	}
	
}
