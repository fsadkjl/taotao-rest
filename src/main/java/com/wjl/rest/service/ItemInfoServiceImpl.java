package com.wjl.rest.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.common.util.JsonUtils;
import com.wjl.mapper.TbItemDescMapper;
import com.wjl.mapper.TbItemMapper;
import com.wjl.mapper.TbItemParamItemMapper;
import com.wjl.pojo.TbItem;
import com.wjl.pojo.TbItemDesc;
import com.wjl.pojo.TbItemParamItem;
import com.wjl.pojo.TbItemParamItemExample;
import com.wjl.rest.dao.JedisClient;
/**
 * 点击商品图片查询商品详细信息的service
 * @author wujiale
 * 2017-10-30 下午9:08:38
 */
@Service
public class ItemInfoServiceImpl implements ItemInfoService{
	
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Autowired
	private JedisClient jedisClient;
	
	/**
	 * 查询商品基本信息
	 * @param itemId
	 * @return
	 */
	@Override
	public TaotaoResult getItemInfo(Long id) {
		try {
			String string = jedisClient.get("ItemInfo:"+id+":base");
			if (StringUtils.isNotBlank(string)) {
				return TaotaoResult.ok(JsonUtils.jsonToPojo(string,TbItem.class));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("从redis缓存中读取商品详细信息失败");
		}
		TbItem item = tbItemMapper.selectByPrimaryKey(id);
		try {
			jedisClient.set("ItemInfo:"+id+":base", JsonUtils.objectToJson(item));
			jedisClient.expire("ItemInfo:"+id+":base", 86400);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("商品详细信息添加到redis缓存失败");
		}
		return TaotaoResult.ok(item);
	}
	
	/**
	 * 查询商品描述
	 * @param itemId
	 * @return
	 */
	@Override
	public TaotaoResult getItemDesc(Long id) {
		try {
			String string = jedisClient.get("ItemInfo:"+id+":desc");
			if (StringUtils.isNotBlank(string)) {
				return TaotaoResult.ok(JsonUtils.jsonToPojo(string, TbItemDesc.class));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("从redis缓存中读取商品描述失败");
		}
		TbItemDesc item = tbItemDescMapper.selectByPrimaryKey(id);
		try {
			jedisClient.set("ItemInfo:"+id+":desc", JsonUtils.objectToJson(item));
			jedisClient.expire("ItemInfo:"+id+":desc", 86400);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("商品描述添加到redis缓存失败");
		}
		return TaotaoResult.ok(item);
	}
	
	/**
	 * 查询商品参数
	 * @param itemId
	 * @return
	 */
	@Override
	public TaotaoResult getItemParamData(Long id) {
		try {
			String string = jedisClient.get("ItemInfo:"+id+":paramData");
			if (StringUtils.isNotBlank(string)) {
				return TaotaoResult.ok(JsonUtils.jsonToPojo(string, TbItemParamItem.class));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("从redis缓存中读取商品参数失败");
		}
		TbItemParamItemExample example = new TbItemParamItemExample();
		example.createCriteria().andItemIdEqualTo(id);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.size() <= 0) {
			return TaotaoResult.ok();
		}
		TbItemParamItem item = list.get(0);
		try {
			jedisClient.set("ItemInfo:"+id+":paramData", JsonUtils.objectToJson(item));
			jedisClient.expire("ItemInfo:"+id+":paramData", 86400);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("商品参数添加到redis缓存失败");
		}
		return TaotaoResult.ok(item);
	}

}
