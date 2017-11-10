package com.wjl.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjl.rest.pojo.CatResult;
import com.wjl.rest.service.ItemCatService;
/**
 * 查询首页分类列表的Controller
 * @author wujiale
 * 2017-10-30 下午9:23:44
 */
@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
