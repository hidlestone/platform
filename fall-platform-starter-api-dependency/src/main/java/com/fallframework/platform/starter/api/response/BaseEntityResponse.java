package com.fallframework.platform.starter.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础响应参数类
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class BaseEntityResponse implements Serializable {

	private static final long serialVersionUID = 4349971885970231113L;

	@ApiModelProperty("创建用户ID")
	private Long createUserId;

	@ApiModelProperty("创建用户ID")
	private Long modifyUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("创建时间")
	private Date gmtCreate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("更改时间")
	private Date gmtModified;

}
