package com.zjyx.vote.test.helloworld;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zjyx.vote.api.model.condition.VoteRankListCdt;
import com.zjyx.vote.api.model.result.VoteResult;
import com.zjyx.vote.impl.mapper.VoteRecordMapper;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath*:vote-app.xml", "classpath*:vote-mybatis.xml" }) 
public class VoteRecordTest {

	@Resource
	VoteRecordMapper voteRecordMapper;
	
	@Test
	public void testRankListCount(){
		VoteRankListCdt condtion = new VoteRankListCdt();
		condtion.setTable_name("vote_record_1");
		condtion.setBeginTime(new Date());
		int count = voteRecordMapper.rankListCount(condtion);
		System.out.println(count);
	}
	
	@Test
	public void testRankList(){
		VoteRankListCdt condtion = new VoteRankListCdt();
		condtion.setTable_name("vote_record_1");
		condtion.setBeginTime(new Date());
		List<VoteResult> list = voteRecordMapper.rankList(condtion);
		System.out.println(JSON.toJSONString(list));
	}
}
