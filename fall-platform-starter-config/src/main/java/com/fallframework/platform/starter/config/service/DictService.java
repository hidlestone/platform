package com.fallframework.platform.starter.config.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.Dict;

public interface DictService extends IService<Dict> {

	ResponseResult saveDict(Dict dict);

	ResponseResult<Page<Dict>> list(Dict dict);

}
