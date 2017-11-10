package com.wjl.rest.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wjl.rest.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-jedis.xml"})
public class TestRedisSpring {

//	@Autowired
//	private JedisClient jedisClient;
//	
//	@Test
//	public void test(){
//		jedisClient.set("test", "test01");
//		System.out.println(jedisClient.get("test"));
//	}
//	
//	@Autowired
//	private JedisPool jedisPool;
//	
//	@Test
//	public void testJedisPool() {
//		//创建一个jedisPool的对象。
//		Jedis jedis = jedisPool.getResource();
////		jedis.auth("redis");
//		//调用jedis对象的方法，方法名称和redis的命令一致。
//		String string = jedis.get("test");
//		System.out.println("jedisPool:"+string);
//		//关闭jedis和jedisPool。
//		jedis.close();
//		jedisPool.close();
//	}

}
