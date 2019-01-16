package com.zjyx.vote.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zjyx.vote.admin.param.VoteParam;
import com.zjyx.vote.admin.viewmodel.BaseVM;
import com.zjyx.vote.api.model.condition.VoteCdtn;
import com.zjyx.vote.api.model.dto.VoteDto;
import com.zjyx.vote.api.model.dto.VoteRuleDto;
import com.zjyx.vote.api.model.enums.Vote_Choose_Type;
import com.zjyx.vote.api.model.enums.Vote_Option_Type;
import com.zjyx.vote.api.model.enums.Vote_Status;
import com.zjyx.vote.api.model.persistence.Vote;
import com.zjyx.vote.api.model.persistence.VoteOption;
import com.zjyx.vote.api.model.persistence.VoteType;
import com.zjyx.vote.api.service.IVoteService;
import com.zjyx.vote.api.service.IVoteTypeService;
import com.zjyx.vote.api.transaction.IVoteTransService;
import com.zjyx.vote.api.utils.VoteRuleUtils;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.PageInfo;
import com.zjyx.vote.common.model.ReturnData;

@Controller
@RequestMapping("/admin")
public class VoteController {

	@Resource
	IVoteTransService voteTransService;
	
	@Resource
	IVoteService voteService; 
	
	@Resource
	IVoteTypeService voteTypeService;
	
	@RequestMapping("/voteList")
	public ModelAndView voteList(VoteCdtn voteCdtn){
		ModelAndView mv = new ModelAndView("views/vote_list");
		PageInfo<Vote> pageinfo = voteService.list(voteCdtn);
		mv.addObject("statusList", Vote_Status.values());
		mv.addObject("pageinfo", pageinfo);
		mv.addObject("condition", voteCdtn);
		Map<Long,List<VoteType>> zmap= (Map<Long, List<VoteType>>) pageinfo.getExtendInfo();
		System.out.println(JSON.toJSONString(zmap));
		mv.addObject("extendInfo", zmap);
		Map<Long,Object> map = new HashMap<Long,Object>();
		List<VoteType> x = new ArrayList<VoteType>();
		VoteType v1 = new VoteType();
		v1.setId(1);
		v1.setType_name("a");
		VoteType v2 = new VoteType();
		v2.setId(2);
		v2.setType_name("b");
		x.add(v1);
		x.add(v2);
		map.put(1l, x);
		mv.addObject("test", map);
		return mv;
	}
	
	@RequestMapping("/updatevotestatus")
	public ModelAndView updatevotestatus(Long id,Vote_Status status){
		ModelAndView mv = new ModelAndView("redirect:/admin/voteList");
		voteService.updateStatus(id, status);
		return mv;
	}
	
	@RequestMapping("/vote")
	public ModelAndView vote(Long id){
		ModelAndView mv = new ModelAndView("views/vote_add");
		if(id != null){
			ReturnData<Vote> returnData = voteService.selectById(id);
			mv.addObject("vote", returnData.getResultData());
		}
		ReturnData<List<VoteType>> returnData= voteTypeService.selectAll();
		mv.addObject("voteTypes", returnData.getResultData());
		mv.addObject("voteChooseTypes", Vote_Choose_Type.values());
		mv.addObject("optionTypes", Vote_Option_Type.values());
		return mv;
	}
	
	
	
	@RequestMapping("/saveVote")
	@ResponseBody
	public BaseVM saveVote(@RequestBody VoteParam voteParam){
		BaseVM vm = new BaseVM();
		VoteDto vote = voteParam.getVote();
		List<VoteOption> options = voteParam.getOptions();
		VoteRuleDto voteRule = voteParam.getVoteRule();
		if(vote == null || options == null || options.isEmpty()){
			vm.setErrorInfo(Error_Type.PARAM_ERROR, null, "无效的投票信息");
			return vm;
		}
		if(StringUtils.isBlank(vote.getTitle())){
			vm.setErrorInfo(Error_Type.PARAM_ERROR, null, "无效的投票标题");
			return vm;
		}
		if(vote.getVoteChooseType() == null){
			vm.setErrorInfo(Error_Type.PARAM_ERROR, null, "无效的投票选择类型");
			return vm;
		}
		Date now = new Date();
		if(vote.getEndTime().compareTo(now) < 0){
			vm.setErrorInfo(Error_Type.PARAM_ERROR, null, "无效的投票截止时间");
			return vm;
		}
		if(vote.getBeginTime()!=null && vote.getEndTime()!=null){
			if(vote.getEndTime().compareTo(vote.getBeginTime())<0){
				vm.setErrorInfo(Error_Type.PARAM_ERROR, null, "无效的投票时间区间");
				return vm;
			}
		}
        if(voteParam.getTypes() == null || voteParam.getTypes().isEmpty()){	
        	vm.setErrorInfo(Error_Type.PARAM_ERROR, null, "请选择至少一个类型");
			return vm;
        }
		Iterator<VoteOption> optionIt = options.iterator();
		while(optionIt.hasNext()){
			VoteOption option = optionIt.next();
			if(option == null){
				//删除空选项
				optionIt.remove();
			}else{
				Vote_Option_Type type = option.getVote_option_type();
				if(type == null){
					vm.setErrorInfo(Error_Type.PARAM_ERROR, null, "无效的选项类型");
					return vm;
				}
				if(type == Vote_Option_Type.character){
					if(StringUtils.isBlank(option.getOption_desc()) || option.getOption_desc().length() > 100 ||  option.getOption_desc().length() < 1){
						vm.setErrorInfo(Error_Type.PARAM_ERROR, null, "无效的选项");
						return vm;
					}
				}
			}
		}
		ReturnData<VoteRuleDto> voteRuleReturnData = VoteRuleUtils.verifyVoteRuleDto(voteRule);
		Error_Type voteRuleErrorType = voteRuleReturnData.getErrorType();
		if(voteRuleErrorType == Error_Type.SERVICE_ERROR){
			voteRule = null;
		}else if(voteRuleErrorType == Error_Type.PARAM_ERROR){
			vm.setErrorInfo(Error_Type.PARAM_ERROR, null, voteRuleReturnData.getErrorMessage());
			return vm;
		}
		ReturnData<Vote> returnData= voteTransService.saveVote(vote, voteRule, options, voteParam.getTypes());
		vm.setErrorInfo(returnData);
		return vm;
	}
	
	@RequestMapping("/deleteVote")
	public ModelAndView delete(Long id){
		ModelAndView mv = new ModelAndView("redirect:/admin/voteList");
		voteService.deleteById(id);
		return mv;
	}
}
