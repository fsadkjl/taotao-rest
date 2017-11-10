package com.wjl.rest.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wjl.common.util.JsonUtils;
import com.wjl.mapper.TbContentMapper;
import com.wjl.pojo.TbContent;
import com.wjl.pojo.TbContentExample;
import com.wjl.pojo.TbContentExample.Criteria;
import com.wjl.rest.dao.JedisClient;
/**
 * 首页获取大广告位的@Service 大广告位的id为89
 * @author wujiale
 * 2017-10-30 下午9:24:43
 */
@Service
public class ContentServiceImpl implements ContentService {
	private static final Logger log = LoggerFactory.getLogger(ContentServiceImpl.class);
	
	@Value("${INDEX_BIGAD_KEY}")
	private String INDEX_BIGAD_KEY;

	@Autowired
	private TbContentMapper contentMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public List<TbContent> getContentList(long contentCid) {
		try {
			String string = jedisClient.hget(INDEX_BIGAD_KEY, contentCid+"");
			if (!StringUtils.isBlank(string)) {
				return JsonUtils.jsonToList(string, TbContent.class);
			}
		} catch (Exception e) {
			log.warn("请启用redis");
		}
		//根据内容分类id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size()>0) {
			try {
				jedisClient.hset(INDEX_BIGAD_KEY,contentCid+"", JsonUtils.objectToJson(list));
			} catch (Exception e) {
				log.warn("请启用redis");
			}
			return list;
		}
		return null;
	}

}

