package com.zjyx.vote.test.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zjyx.vote.api.model.persistence.Vote;
import com.zjyx.vote.impl.mapper.VoteMapper;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class VoteTest {
	
	@Autowired
	VoteMapper mapper;
	
	@Test
	public void testSelectById(){
		Vote vote = mapper.selectById(1L);
		System.out.println(JSON.toJSONString(vote));
	}
	
}
