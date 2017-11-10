package com.wjl.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.rest.service.RedisCacheService;

@Controller
public class RedisCacheController {
	
	@Autowired
	private RedisCacheService redisCacheService;
	
	/**
	 * 同步首页大广告位的缓存数据
	 * @param contentCategoryId
	 * @return
	 */
	@RequestMapping("/syncBigAdCache/{categoryId}")
	@ResponseBody
	public TaotaoResult syncBigAdCache(@PathVariable Long categoryId){
		redisCacheService.syncBigAdCache(categoryId);
		return TaotaoResult.ok();
	}
	
	/**
	 * 同步首页分类列表的缓存数据
	 * @param contentCategoryId
	 * @return
	 */
//	@RequestMapping("/syncCategoryCache")
//	@ResponseBody
//	public TaotaoResult syncCategoryCache(){
//		jedisClient.hdel(INDEX_CATEGORY_KEY, "categoryData");
//		return TaotaoResult.ok();
//	}
}
