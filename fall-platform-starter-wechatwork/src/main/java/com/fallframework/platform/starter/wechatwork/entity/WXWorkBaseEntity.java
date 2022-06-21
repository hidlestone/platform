package com.fallframework.platform.starter.wechatwork.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fallframework.platform.starter.data.mp.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class WXWorkBaseEntity extends BaseEntity {

	private static final long serialVersionUID = -6247812470913165481L;

	@TableField(exist = false)
	private String errmsg;

	@TableField(exist = false)
	private Integer errcode;

}
