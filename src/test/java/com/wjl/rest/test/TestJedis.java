package com.wjl.rest.test;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class TestJedis {

//	@Test
//	public void testJedisSingle() {
//		//创建一个jedis的对象。
//		Jedis jedis = new Jedis("192.168.203.128", 6379);
////		jedis.auth("redis");
//		//调用jedis对象的方法，方法名称和redis的命令一致。
//		jedis.set("key1", "jedis test");
//		String string = jedis.get("key1");
//		System.out.println("jedis:"+string);
//		//关闭jedis。
//		jedis.close();
//	}
//	
//	@Test
//	public void testJedisPool() {
//		//创建一个jedisPool的对象。
//		JedisPool jedisPool = new JedisPool("192.168.203.128", 6379);
//		Jedis jedis = jedisPool.getResource();
////		jedis.auth("redis");
//		//调用jedis对象的方法，方法名称和redis的命令一致。
//		String string = jedis.get("key1");
//		System.out.println("jedisPool:"+string);
//		//关闭jedis和jedisPool。
//		jedis.close();
//		jedisPool.close();
//	}
	
//	@Test
//	public void testJedisCluster() throws IOException {
//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("192.168.203.128", 7001));
//		nodes.add(new HostAndPort("192.168.203.128", 7002));
//		nodes.add(new HostAndPort("192.168.203.128", 7003));
//		nodes.add(new HostAndPort("192.168.203.128", 7004));
//		nodes.add(new HostAndPort("192.168.203.128", 7005));
//		nodes.add(new HostAndPort("192.168.203.128", 7006));
//		//创建一个jedisCluster的对象。
//		JedisCluster jedisCluster = new JedisCluster(nodes);
//		jedisCluster.set("test", "test01");
//		System.out.println(jedisCluster.get("test"));
//		jedisCluster.close();
//	}
	
//	@Test
//	public void test01(){
//		String image = "http://localhost:8080/image/1509356228223922.jpg,http://localhost:8080/image/1509356228299965.jpg,http://localhost:8080/image/1509356228360116.jpg,http://localhost:8080/image/1509356228429151.jpg";
//		image = new String(image.substring(0, image.indexOf(",")));
//		System.out.println(image);
//	}
	
//	@Test
//	public void test02(){
//		String aString = "'dsljk'";
//		aString = aString.replace("'", "").trim();
//		System.out.println(aString);
//	}

}
