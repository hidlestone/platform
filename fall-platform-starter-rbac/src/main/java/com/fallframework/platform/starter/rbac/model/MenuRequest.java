package com.fallframework.platform.starter.rbac.model;

import com.fallframework.platform.starter.api.model.OpenTypeEnum;
import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class MenuRequest extends BasePageRequest {

	private static final long serialVersionUID = 7111135764843134508L;

	private Long id;

	private Long parentId;

	private Byte level;
	
	private String menuCode;

	private String menuName;

	private String menuDesc;

	private String path;

	private String funcLink;

	private OpenTypeEnum openType;

	private String icon;

	private Byte order;

	private String remark;

	private StatusEnum status;

}
