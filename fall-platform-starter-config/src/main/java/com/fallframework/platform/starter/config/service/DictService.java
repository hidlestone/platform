package com.fallframework.platform.starter.config.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.Dict;

import java.util.List;

public interface DictService extends IService<Dict> {

	ResponseResult saveDict(Dict dict);

	Leaf<Dict> list(Dict dict);

	List<Dict> getAllDicts();

}
