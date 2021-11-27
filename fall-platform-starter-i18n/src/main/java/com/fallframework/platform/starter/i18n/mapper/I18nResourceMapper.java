package com.fallframework.platform.starter.i18n.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fallframework.platform.starter.i18n.entity.I18nResource;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface I18nResourceMapper extends BaseMapper<I18nResource> {
    
    int batchInsert(@Param("list") List<I18nResource> list);
    
    
}