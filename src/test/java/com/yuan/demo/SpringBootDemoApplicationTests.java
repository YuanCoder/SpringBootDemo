package com.yuan.demo;

import com.yuan.demo.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

//	public static Logger logger= LoggerFactory.getLogger(SpringbootRedisApplicationTests.class);
	 
	@Autowired
	RedisDao redisDao;

	@Test
	public void contextLoads() {
		redisDao.setKey("name","forezp");
		redisDao.setKey("age","11");
		/*logger.info(redisDao.getValue("name"));
		logger.info(redisDao.getValue("age"));*/
		System.out.println(redisDao.getValue("name"));
		System.out.println(redisDao.getValue("age"));
	}

}
