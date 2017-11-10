package com.wjl.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.rest.dao.JedisClient;
/**
 * 同步首页大广告位的缓存数据
 * @author wujiale
 * 2017-10-30 下午9:22:36
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService{
	private static final Logger log = LoggerFactory.getLogger(RedisCacheServiceImpl.class);
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_BIGAD_KEY}")
	private String INDEX_BIGAD_KEY;
	
	@Value("${INDEX_CATEGORY_KEY}")
	private String INDEX_CATEGORY_KEY;

	@Override
	public TaotaoResult syncBigAdCache(Long cid) {
		long l = jedisClient.hdel(INDEX_BIGAD_KEY, cid+"");
		if (l > 0) {
			return TaotaoResult.ok();
		}
		log.error("清空缓存失败");
		return TaotaoResult.build(500, "清空缓存失败");
	}

}
