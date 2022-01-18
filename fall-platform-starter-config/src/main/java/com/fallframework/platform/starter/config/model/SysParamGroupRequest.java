package com.fallframework.platform.starter.config.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class SysParamGroupRequest extends BasePageRequest {

	private static final long serialVersionUID = -494029364432150004L;

	private String code;

	private String desc;

	private String status;

	private List<SysParamItemRequest> sysParamItemList;

}
