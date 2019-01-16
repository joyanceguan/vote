package com.zjyx.vote.impl.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

import com.zjyx.vote.api.model.condition.SexRankCdt;
import com.zjyx.vote.api.model.condition.VoteCdtn;
import com.zjyx.vote.api.model.condition.VoteTypeCdn;
import com.zjyx.vote.api.model.constants.RedisKey;
import com.zjyx.vote.api.model.enums.Sex;
import com.zjyx.vote.api.model.enums.Vote_Status;
import com.zjyx.vote.api.model.persistence.Vote;
import com.zjyx.vote.api.model.persistence.VoteType;
import com.zjyx.vote.api.model.result.VoteResult;
import com.zjyx.vote.api.model.result.VoteTypeResult;
import com.zjyx.vote.api.service.IVoteService;
import com.zjyx.vote.api.utils.VoteRecordUtils;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.BasePageCondition;
import com.zjyx.vote.common.model.PageInfo;
import com.zjyx.vote.common.model.ReturnData;
import com.zjyx.vote.impl.mapper.VoteMapper;
import com.zjyx.vote.impl.mapper.VoteRecordMapper;
import com.zjyx.vote.impl.mapper.VoteTypeRelateMapper;


@Service
public class VoteServiceImpl implements IVoteService{

	@Resource
	VoteMapper voteMapper;
	
	@Resource
	VoteRecordMapper voteRecordMapper;
	
	@Resource
	VoteTypeRelateMapper voteTypeRelateMapper;
	
	@Resource
	RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public PageInfo<Vote> list(VoteCdtn voteCdtn) {
		PageInfo<Vote> pageInfo = new PageInfo<Vote>();
		if(voteCdtn == null || voteCdtn.getCurrentPage() < 1 || voteCdtn.getOnePageSize() < 1){
			pageInfo.setErrorType(Error_Type.PARAM_ERROR);
		}
		int counts = voteMapper.count(voteCdtn);
		if(counts > 0){
			List<Vote> list = voteMapper.list(voteCdtn);
			List<Long> voteIds = new ArrayList<Long>();
			for(Vote vote:list){
				voteIds.add(vote.getId());
			}
			List<VoteTypeResult> voteTypeResults = voteTypeRelateMapper.selectByVoteIds(voteIds);
			Map<Long,List<VoteType>> map = VoteTypeResult.transToMap(voteTypeResults);
			pageInfo.setPageInfo(voteCdtn ,counts, list, map);
		}
		return pageInfo;
	}

	@Override
	public ReturnData<Vote> selectById(Long id) {
		ReturnData<Vote> returnData = new ReturnData<Vote>();
		Vote vote = voteMapper.selectById(id);
		returnData.setResultData(vote);
		return returnData;
	}

	@Override
	public ReturnData<Integer> updateStatus(Long id, Vote_Status vote_Status) {
		ReturnData<Integer> returnData = new ReturnData<Integer>();
		Vote vote = new Vote();
		vote.setId(id);
		vote.setStatus(vote_Status);
		int flag = voteMapper.update(vote);
		//更新redis统计数据
		updateRedis(id,vote_Status);
		returnData.setResultData(flag);
		return returnData;
	}
	
	/**
	 * 因已经将统计信息放入redis中，如果用户已经投票了下架的话，将统计信息改成负数，上架将负数改成正数，避免下架的投票在首页还能被用户看到
	 * @param id
	 * @param vote_Status
	 */
	private void updateRedis(Long id,Vote_Status vote_Status){
		String redisKey = RedisKey.VOTE_RANK_KEY;
		Double score = redisTemplate.opsForZSet().score(redisKey, id);
		//如果存在该投票
		if(score!=null){
			BigDecimal scoreBigDecimal = new BigDecimal(""+score);
			//如果是关闭
			if(vote_Status == Vote_Status.close){
				if(scoreBigDecimal.compareTo(BigDecimal.ZERO) == 0){
					//如果是0，下架就删除
					redisTemplate.opsForZSet().remove(redisKey, id);
				}else if(scoreBigDecimal.compareTo(BigDecimal.ZERO) > 0){
					//如果是已经有投票了，那么将其变成负数
					BigDecimal increment = BigDecimal.ZERO.subtract(scoreBigDecimal.multiply(new BigDecimal(2)));
					redisTemplate.opsForZSet().incrementScore(redisKey, id, increment.doubleValue());
				}else{
					//已经是负数，不需要改了
				}
			}
			//如果是开启
			else if(vote_Status == Vote_Status.normal){
				if(scoreBigDecimal.compareTo(BigDecimal.ZERO) >= 0){
					//什么都不干
				}else{
					//如果是负数，恢复成正数
					BigDecimal increment = BigDecimal.ZERO.add(scoreBigDecimal.multiply(new BigDecimal(2)));
					redisTemplate.opsForZSet().incrementScore(redisKey, id, increment.doubleValue());
				}
			}
		}else{
			//没有这个投票id的统计，则新增key，设置投票数为0
			redisTemplate.opsForZSet().add(redisKey, id, 0);
		}
	}


	@Override
	public PageInfo<Vote> typeList(VoteTypeCdn condition) {
		PageInfo<Vote> pageInfo = new PageInfo<Vote>();
		if(condition == null || condition.getTypeId() < 1 || condition.getTypeId() < 1){
			pageInfo.setErrorType(Error_Type.PARAM_ERROR);
			return pageInfo;
		}
		List<Long> voteIds = voteTypeRelateMapper.selectByTypeId(condition.getTypeId());
		if(voteIds.size() > 0){
			String sort = " order by update_time desc";
			List<Vote> list = voteMapper.selectByIds(voteIds,sort,condition.getStatus());
			pageInfo.setPageInfo(condition ,voteIds.size(), list, null);
		}
		return pageInfo;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ReturnData<List<Vote>> hotList(){
		ReturnData<List<Vote>> returnData = new ReturnData<List<Vote>>();
		List<Vote> resultList = null;
		Object v = redisTemplate.opsForValue().get(RedisKey.VOTE_HOT_KEY);
	    if(v!=null){
	    	List<VoteResult> list = (List<VoteResult>) v;
	    	resultList = new ArrayList<Vote>();
	    	List<Long> ids = new ArrayList<Long>();
	    	for(VoteResult voteResult : list){
	    		ids.add(voteResult.getId());
	    	}
	    	List<Vote> voteList = voteMapper.selectByIds(ids, null, null);
	    	Map<Long,Vote> map = Vote.listToMap(voteList);
	    	for(VoteResult voteResult : list){
	    		resultList.add(map.get(voteResult.getId()));
	    	}
	    } else{
	    	//还没人投票呢,那么先默认按修改时间倒序排列
	    	int rank = 100;
	    	VoteCdtn voteCdtn = new VoteCdtn();
	    	voteCdtn.setStatus(Vote_Status.normal);
	    	voteCdtn.setOnePageSize(rank);
	    	resultList = voteMapper.list(voteCdtn);
	    }
	    returnData.setResultData(resultList);
	    return returnData;
	}
	
	@Override
	public PageInfo<Vote> rankList(BasePageCondition condition) {
		PageInfo<Vote> pageInfo = new PageInfo<Vote>();
		if(condition == null || condition.getCurrentPage() < 1 || condition.getOnePageSize() < 1){
			pageInfo.setErrorType(Error_Type.PARAM_ERROR);
			return pageInfo;
		}
		//初始化数据
		int totalCount = 0;
		int beginNum = condition.getBeginNum();
		int onePageSize = condition.getOnePageSize();
		String redisKey = RedisKey.VOTE_RANK_KEY;
		Set<TypedTuple<Object>> values = redisTemplate.opsForZSet().reverseRangeByScoreWithScores(redisKey, 0, Integer.MAX_VALUE, beginNum, onePageSize);
		totalCount = redisTemplate.opsForZSet().zCard(redisKey).intValue();
		List<Long> voteIds = new ArrayList<Long>();
		//获取投票id
		for(TypedTuple<Object> type:values){
			Long voteId = (Long) type.getValue();
			voteIds.add(voteId);
	    }
		List<Vote> voteList = null;
		Map<Long,Long> extendMap = null;
		if(!voteIds.isEmpty()){
			//获取id对应的投票列表
		    voteList = voteMapper.selectByIds(voteIds, null,null);
		    Map<Long,Vote> map = Vote.listToMap(voteList);
		    //投票id，投票数量映射map
		    extendMap = new HashMap<Long,Long>();
		    voteList = new ArrayList<Vote>();
		    for(TypedTuple<Object> type:values){
		    	Long voteId = (Long) type.getValue();
		    	Vote vote= map.get(voteId);
		    	voteList.add(vote);
		    	extendMap.put(voteId,type.getScore().longValue());
		    }
		}
		pageInfo.setPageInfo(condition ,totalCount, voteList, extendMap);
		return pageInfo;
	}

	@Override
	public ReturnData<Vote> random(Long userId) {
		ReturnData<Vote> returnData = new ReturnData<Vote>();
		//用户必须登录才行
		if(userId == null){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
			return returnData;
		}
		String redisKey = RedisKey.VOTE_RANK_KEY;
		int onePageSize = 50;
		int totalCount = redisTemplate.opsForZSet().zCard(redisKey).intValue();
		//
		int index = totalCount > onePageSize ? totalCount - onePageSize : 1;
		Random random = new Random();
		Set<TypedTuple<Object>> values = redisTemplate.opsForZSet().reverseRangeByScoreWithScores(redisKey, 0, Integer.MAX_VALUE, random.nextInt(index), onePageSize);
		//获取用户投过票的项目
		List<Long> voteIdList1 = voteRecordMapper.getVoteIdByUser(VoteRecordUtils.VOTE_RECORD_1, userId);
		List<Long> voteIdList2 = voteRecordMapper.getVoteIdByUser(VoteRecordUtils.VOTE_RECORD_2, userId);
		voteIdList1.addAll(voteIdList2);
		//获取投票id
		Long randomVoteId = null;
		List<Long> voteIds = new ArrayList<Long>();
		for(TypedTuple<Object> type:values){
			Long voteId = (Long) type.getValue();
			if(!voteIdList1.contains(voteId)){
				voteIds.add(voteId);
			}
		}
		if(voteIds!=null){
			randomVoteId = voteIds.get(random.nextInt(voteIds.size()));
		}
		Vote vote = null;
		if(randomVoteId != null){
		    vote = voteMapper.selectById(randomVoteId);
		}
		returnData.setResultData(vote);
		return returnData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<Vote> typeWithRankList(VoteTypeCdn  condition) {
		PageInfo<Vote> pageinfo = new PageInfo<Vote>();
		if(condition == null || !condition.isRightPageInfo() || condition.getTypeId() < 1){
			pageinfo.setErrorType(Error_Type.PARAM_ERROR);
			return pageinfo;
		}
		List<Vote> sortList = null;
		//如果需要榜单排列，当第一页时是需要的
		if(condition.getCurrentPage() == 1){
			Object v = redisTemplate.opsForValue().get(RedisKey.TYPE_PREFIX + condition.getTypeId());
			if(v != null){
				List<VoteResult> voteResultList = (List<VoteResult>) v;
		    	List<Long> ids = new ArrayList<Long>();
		    	for(VoteResult voteResult : voteResultList){
		    		ids.add(voteResult.getId());
		    	}
		    	List<Vote> voteList = voteMapper.selectByIds(ids, null, null);
		    	Map<Long,Vote> map = Vote.listToMap(voteList);
		    	sortList = new ArrayList<Vote>();
		    	for(VoteResult voteResult : voteResultList){
		    		sortList.add(map.get(voteResult.getId()));
		    	}
			}
		}
		pageinfo = typeList(condition);
		pageinfo.setExtendInfo(sortList);
		return pageinfo;
	}

	@Override
	public ReturnData<Integer> deleteById(Long id) {
		ReturnData<Integer> returnData = new ReturnData<Integer>();
		if(id == null){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
			return returnData;
		}
		//TODO JOY 删除需要判断是否有投票和统计数据
		int flag = voteMapper.delelteById(id);
		flag = voteTypeRelateMapper.deleteByVoteId(id);
		returnData.setResultData(flag);
		return returnData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<Vote> sexRankList(SexRankCdt condition) {
		PageInfo<Vote> pageInfo = new PageInfo<Vote>();
		if(condition == null || condition.getCurrentPage() < 1 || condition.getOnePageSize() < 1 
				|| condition.getSex() == null || condition.getSex() == Sex.unknown){
			pageInfo.setErrorType(Error_Type.PARAM_ERROR);
			return pageInfo;
		}
		//初始化数据
		String redisKey = RedisKey.SEX_PREFIX + condition.getSex().getId();
		Object v = redisTemplate.opsForValue().get(redisKey);
		if(v == null){
			return pageInfo;
		}
		List<VoteResult> vouchResultList= (List<VoteResult>) v;
		if(vouchResultList == null || vouchResultList.isEmpty()){
			return pageInfo;
		}
		int beginNum = condition.getBeginNum();
		if(beginNum >= vouchResultList.size()){
			return pageInfo;
		}
		int onePageSize = condition.getOnePageSize();
		int endNum = beginNum + onePageSize;
		if(endNum > vouchResultList.size()){
			endNum = vouchResultList.size();
		}
		List<Long> ids = new ArrayList<Long>();
		for(int i = beginNum ; i < endNum ; i++){
			ids.add(vouchResultList.get(i).getId());
		}
		List<Vote> returnList = null; 
		List<Vote> voteList = voteMapper.selectByIds(ids, null, null);
		if(voteList!=null && voteList.size()>0){
			Map<Long,Vote> map = Vote.listToMap(voteList);
			returnList = new ArrayList<Vote>();
			for(Long id:ids){
				returnList.add(map.get(id));
			}
		}
		pageInfo.setPageInfo(condition ,vouchResultList.size(), voteList, null);
		return pageInfo;
	}
	
}
