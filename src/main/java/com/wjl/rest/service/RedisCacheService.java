package com.wjl.rest.service;

import com.wjl.common.pojo.TaotaoResult;

public interface RedisCacheService {
	TaotaoResult syncBigAdCache(Long cid);
}
