package com.wjl.rest.service;

import java.util.List;

import com.wjl.pojo.TbContent;

public interface ContentService {
	List<TbContent> getContentList(long contentCid);
}
