package com.zjyx.vote.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zjyx.vote.admin.viewmodel.BaseVM;
import com.zjyx.vote.api.model.persistence.VoteType;
import com.zjyx.vote.api.service.IVoteTypeService;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.ReturnData;

@Controller
@RequestMapping("/admin")
public class VoteTypeController {

	@Resource
	IVoteTypeService voteTypeService;
	
	@RequestMapping("/saveVoteType")
	@ResponseBody
	public BaseVM save(String name){
		BaseVM bv = new BaseVM();
		if(StringUtils.isBlank(name) || name.length() > 4){
			bv.setErrorInfo(Error_Type.PARAM_ERROR, null, null);
			return bv;
		}
		ReturnData<Integer> returnData = voteTypeService.save(name);
		bv.setErrorInfo(returnData);
		return bv;
	}
	
	@RequestMapping("/voteTypeList")
	public ModelAndView voteTypes(){
		ModelAndView mv = new ModelAndView("views/vote_type_list");
		ReturnData<List<VoteType>> returnData = voteTypeService.selectAll();
		List<VoteType> list = returnData.getResultData();
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping("/voteType")
	public ModelAndView voteType(){
		ModelAndView mv = new ModelAndView("views/vote_type_add");
		return mv;
	}
	
	@RequestMapping("/deleteVoteType")
	public ModelAndView deleteVoteType(Long id){
		ModelAndView mv = new ModelAndView("redirect:/admin/voteTypeList");
		voteTypeService.deleteById(id);
		return mv;
	}
}
