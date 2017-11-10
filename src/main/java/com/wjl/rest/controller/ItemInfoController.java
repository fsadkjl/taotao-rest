package com.wjl.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 点击商品图片查询商品详细信息的Controller
 * @author wujiale
 * 2017-10-30 下午9:11:02
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.rest.service.ItemInfoService;
/**
 * 点击商品图片查询商品详细信息的@Controller
 * @author wujiale
 * 2017-10-30 下午9:25:11
 */
@Controller
public class ItemInfoController {
	@Autowired
	private ItemInfoService itemInfoService;
	/**
	 * 查询商品基本信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/itemInfo/{itemId}")
	@ResponseBody
	public TaotaoResult getItemInfo(@PathVariable Long itemId){
		TaotaoResult taotaoResult = itemInfoService.getItemInfo(itemId);
		return taotaoResult;
	}
	/**
	 * 查询商品描述
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/itemDesc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable Long itemId){
		TaotaoResult taotaoResult = itemInfoService.getItemDesc(itemId);
		return taotaoResult;
	}
	/**
	 * 查询商品参数
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/itemParamData/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParamData(@PathVariable Long itemId){
		TaotaoResult taotaoResult = itemInfoService.getItemParamData(itemId);
		return taotaoResult;
	}
}
