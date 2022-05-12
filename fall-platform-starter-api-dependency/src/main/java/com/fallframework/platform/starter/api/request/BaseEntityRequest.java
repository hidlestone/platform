package com.fallframework.platform.starter.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础请求参数类
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class BaseEntityRequest implements Serializable {

	private static final long serialVersionUID = -3908244974737701868L;

	@ApiModelProperty(value = "创建用户ID")
	private Long createUserId;

	@ApiModelProperty(value = "修改用户ID")
	private Long modifyUserId;

	@ApiModelProperty(value = "创建时间")
	private Date gmtCreate;

	@ApiModelProperty(value = "更改时间")
	private Date gmtModified;

	@ApiModelProperty(value = "起始创建时间")
	private Date gmtCreateStart;

	@ApiModelProperty(value = "结束创建时间")
	private Date gmtCreateEnd;

	@ApiModelProperty(value = "起始更改时间")
	private Date gmtModifiedStart;

	@ApiModelProperty(value = "结束更改时间")
	private Date gmtModifiedEnd;

}
