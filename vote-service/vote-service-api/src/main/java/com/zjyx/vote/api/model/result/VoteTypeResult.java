package com.zjyx.vote.api.model.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zjyx.vote.api.model.persistence.VoteType;

public class VoteTypeResult {

	private Long voteId;
	
	private Integer typeId;
	
	private String typeName;

	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public static Map<Long,List<VoteType>> transToMap(List<VoteTypeResult> results){
		if(results == null || results.isEmpty())
			return null;
		Map<Long,List<VoteType>> map = new HashMap<Long,List<VoteType>>();
		for(VoteTypeResult vtr:results){
			if(map.containsKey(vtr.getVoteId())){
				List<VoteType> voteTypeList = map.get(vtr.getVoteId());
				voteTypeList.add(transVoteType(vtr));
			}else{
				List<VoteType> voteTypeList = new ArrayList<VoteType>();
				voteTypeList.add(transVoteType(vtr));
				map.put(vtr.getVoteId(), voteTypeList);
			}
		}
		return map;
	}
	
	public static VoteType transVoteType(VoteTypeResult vtr){
		if(vtr == null)
			return null;
		VoteType vt = new VoteType();
		vt.setId(vtr.getTypeId());
		vt.setType_name(vtr.getTypeName());
		return vt;
	}
}
