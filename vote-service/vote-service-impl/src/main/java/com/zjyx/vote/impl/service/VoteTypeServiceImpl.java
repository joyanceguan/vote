package com.zjyx.vote.impl.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zjyx.vote.api.model.persistence.VoteType;
import com.zjyx.vote.api.service.IVoteTypeService;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.ReturnData;
import com.zjyx.vote.impl.mapper.VoteTypeMapper;

@Service
public class VoteTypeServiceImpl implements IVoteTypeService{

	@Resource
	VoteTypeMapper voteTypeMapper;
	
	@Override
	public ReturnData<Integer> save(String name) {
		ReturnData<Integer> returnData = new ReturnData<Integer>();
		if(StringUtils.isBlank(name) || name.length() > 5){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
			return returnData;
		}
		VoteType voteType = new VoteType();
		voteType.setType_name(name);
		int flag = voteTypeMapper.save(voteType);
		returnData.setResultData(flag);
		return returnData;
	}

	@Override
	public ReturnData<List<VoteType>> selectAll() {
		ReturnData<List<VoteType>> returnData = new ReturnData<List<VoteType>>();
		List<VoteType> list = voteTypeMapper.selectAll();
		returnData.setResultData(list);
		return returnData;
	}

	@Override
	public ReturnData<Integer> deleteById(Long id) {
		ReturnData<Integer> returnData = new ReturnData<Integer>();
		if(id == null){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
			return returnData;
		}
		//TODO JOY 需要校验是否能删除
		int flag = voteTypeMapper.deleteById(id);
		returnData.setResultData(flag);
		return returnData;
	}

}
