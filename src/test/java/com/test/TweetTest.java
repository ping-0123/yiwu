package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.springmvc3.entity.Tweet;
import com.yinzhiwu.springmvc3.service.TweetService;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TweetTest {

	@Autowired 
	private TweetService tweetService;
	
	@Test
	public void test(){
		Tweet tweet = tweetService.get(16000001);
		byte[] content = tweet.getTweetContent().getContent();
		System.out.println(new String(content));
	}
}
