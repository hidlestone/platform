package com.fallframework.platform.starter.rbac.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class MenuQueryRequest extends BasePageRequest {

	private static final long serialVersionUID = -1176042930719311116L;

	private Long userId;

	private List<Long> roleIds;

}
