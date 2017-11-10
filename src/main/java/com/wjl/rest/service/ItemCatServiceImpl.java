package com.wjl.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wjl.common.util.JsonUtils;
import com.wjl.mapper.TbItemCatMapper;
import com.wjl.pojo.TbItemCat;
import com.wjl.pojo.TbItemCatExample;
import com.wjl.pojo.TbItemCatExample.Criteria;
import com.wjl.rest.dao.JedisClient;
import com.wjl.rest.pojo.CatNode;
import com.wjl.rest.pojo.CatResult;
/**
 * 查询分类列表的service
 * <p>Title: getCatList</p>
 * <p>Description: </p>
 * @param parentId
 * @return
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Value("${INDEX_CATEGORY_KEY}")
	private String INDEX_CATEGORY_KEY;
	
	@Autowired
	private JedisClient jedisClient;

	@Override
	public CatResult getItemCatList() {
		
		try {
			String string = jedisClient.hget(INDEX_CATEGORY_KEY, "categoryData");
			if (string != null && !StringUtils.isBlank(string)) {
				return JsonUtils.jsonToPojo(string, CatResult.class);
			}
		} catch (Exception e) {
			System.out.println("请启用redis");
		}
		//查询分类列表
		CatResult catResult = new CatResult();
		catResult.setData(getCatList(0));
		try {
			jedisClient.hset(INDEX_CATEGORY_KEY, "categoryData", JsonUtils.objectToJson(catResult));
		} catch (Exception e) {
			System.out.println("请启用redis");
		}
		return catResult;
	}
	
	/**
	 * 查询分类列表的service
	 * <p>Title: getCatList</p>
	 * <p>Description: </p>
	 * @param parentId
	 * @return
	 */
	private List<?> getCatList(long parentId) {
		//创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//返回值list
		List resultList = new ArrayList<>();
		//向list中添加节点
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			//判断是否为父节点
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				
				resultList.add(catNode);
				count ++;
				//第一层只取14条记录
				if (parentId == 0 && count >=14) {
					break;
				}
			//如果是叶子节点
			} else {
				resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
			}
		}
		return resultList;
	}


}
