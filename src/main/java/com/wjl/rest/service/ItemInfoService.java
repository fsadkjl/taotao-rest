package com.wjl.rest.service;

import com.wjl.common.pojo.TaotaoResult;

public interface ItemInfoService {
	TaotaoResult getItemInfo(Long id);
	TaotaoResult getItemDesc(Long id);
	TaotaoResult getItemParamData(Long id);
}
