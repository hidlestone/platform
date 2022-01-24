package com.fallframework.platform.starter.config.model;

import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.api.response.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class SysParamGroupResponse extends BaseEntityResponse {

	private static final long serialVersionUID = 3021357866402826870L;

	private String code;

	private String desc;

	private StatusEnum status;

	List<SysParamItemResponse> sysParamItemList;
}
