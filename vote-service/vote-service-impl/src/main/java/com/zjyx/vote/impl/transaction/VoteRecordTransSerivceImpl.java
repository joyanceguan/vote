package com.zjyx.vote.impl.transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjyx.vote.api.model.persistence.Vote;
import com.zjyx.vote.api.model.persistence.VoteRecord;
import com.zjyx.vote.api.transaction.IVoteRecordTransSerivce;
import com.zjyx.vote.api.utils.VoteRecordUtils;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.exceptions.TransactionException;
import com.zjyx.vote.common.model.ReturnData;
import com.zjyx.vote.impl.mapper.VoteMapper;
import com.zjyx.vote.impl.mapper.VoteRecordMapper;

@Service
public class VoteRecordTransSerivceImpl implements IVoteRecordTransSerivce{

	@Resource
	VoteRecordMapper voteRecordMapper;
	
	@Resource
	VoteMapper voteMapper;
	
	@Transactional("votetm")
	@Override
	public ReturnData<Integer> batchSave(Set<VoteRecord> voteRecords) {
		ReturnData<Integer> returnData = new ReturnData<Integer>();
		if(voteRecords == null || voteRecords.isEmpty()){
			returnData.setErrorInfo(Error_Type.PARAM_ERROR, null, null);
			return returnData;
		}
		List<Long> voteIds = new ArrayList<Long>();
		Iterator<VoteRecord> iterator = voteRecords.iterator();
		while(iterator.hasNext()){
			VoteRecord voteRecord = iterator.next();
			if(voteRecord == null)
				iterator.remove();
			voteIds.add(voteRecord.getVote_id());
		}
		List<Vote> votes = voteMapper.selectByIds(voteIds, null, null);
		List<VoteRecord> list1 = new ArrayList<VoteRecord>();
		List<VoteRecord> list2 = new ArrayList<VoteRecord>();
		List<VoteRecord> list3 = new ArrayList<VoteRecord>();
		List<VoteRecord> list4 = new ArrayList<VoteRecord>();
		Map<Long,Vote> map = Vote.listToMap(votes);
		for(VoteRecord voteRecord:voteRecords){
			String tableName = VoteRecordUtils.getRecordTableName(map.get(voteRecord.getVote_id()));
			if(tableName.contains("1")){
				voteRecord.setTable_name(tableName);
				list1.add(voteRecord);
			}else if(tableName.contains("2")){
				voteRecord.setTable_name(tableName);
				list2.add(voteRecord);
			}else if(tableName.contains("3")){
				voteRecord.setTable_name(tableName);
				list3.add(voteRecord);
			}else if(tableName.contains("4")){
				voteRecord.setTable_name(tableName);
				list4.add(voteRecord);
			}
		}
		if(list1.size()>0){
		    int size = voteRecordMapper.batchSave(list1);
		    if(size < list1.size()){
		    	throw new TransactionException(Error_Type.SYSTEM_ERROR,null,null);
		    }
		}
		if(list2.size()>0){
			int size = voteRecordMapper.batchSave(list2);
			if(size < list2.size()){
		    	throw new TransactionException(Error_Type.SYSTEM_ERROR,null,null);
		    }
		}
		if(list3.size()>0){
			int size = voteRecordMapper.batchSave(list3);
			if(size < list3.size()){
		    	throw new TransactionException(Error_Type.SYSTEM_ERROR,null,null);
		    }
		}
		if(list4.size()>0){
			int size = voteRecordMapper.batchSave(list4);
			if(size < list4.size()){
		    	throw new TransactionException(Error_Type.SYSTEM_ERROR,null,null);
		    }
		}
		return returnData;
	}

}
