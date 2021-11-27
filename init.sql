-- 初始化脚本


-- 系统参数组/系统参数明细项 数据量较少，不使用ID
CREATE TABLE `s_sys_param_group` (
	`code` VARCHAR (50) NOT NULL COMMENT '系统参数组编码',
	`desc` VARCHAR (200) DEFAULT NULL COMMENT '系统参数组描述',
	`status` CHAR (1) NOT NULL DEFAULT '1' COMMENT '是否启用：0-停用，1-启用',
	`create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`code`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统参数组表';

CREATE TABLE `s_sys_param_item` (
	`code` VARCHAR (50) NOT NULL COMMENT '系统参数编码',
	`value` VARCHAR (2000) DEFAULT NULL COMMENT '系统参数值',
	`encrypted_value` VARCHAR (2000) DEFAULT NULL COMMENT '加密值',
	`desc` VARCHAR (200) DEFAULT NULL COMMENT '系统参数描述',
	`status` CHAR (1) NOT NULL DEFAULT '1' COMMENT '是否启用：0-停用，1-启用',
	`group_code` VARCHAR (50) NOT NULL COMMENT '系统参数组编码',
	`create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`code`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统参数明细项表';


CREATE TABLE `s_i18n_resource` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`lang_code` VARCHAR (10) NOT NULL COMMENT '语言编码',
	`resource_key` VARCHAR (150) NOT NULL COMMENT '资源key',
	`resource_value` text COMMENT '资源value',
	`module_code` VARCHAR (255) DEFAULT 'common' COMMENT '所属module',
	`create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '国际化资源表';


CREATE TABLE `s_permission` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`permission_code` VARCHAR (255) NOT NULL COMMENT '权限编码',
	`permission_name` VARCHAR (255) DEFAULT NULL COMMENT '权限名称即接口名称',
	`resource_value` VARCHAR (255) DEFAULT NULL COMMENT '资源值即URL',
	`order_num` INT (4) DEFAULT NULL COMMENT '顺序',
	`authc_type` VARCHAR (255) DEFAULT 'auth' COMMENT '认证类型：anon(允许匿名访问)，auth(登录即可访问)',
	`create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统接口权限表';







