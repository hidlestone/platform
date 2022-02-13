```sql


CREATE TABLE `d_wm_returnstockdtllocation` (
  `returnstockdtllocationid` bigint(20) NOT NULL COMMENT 'ID',
  `returnstockdtlid` bigint(20) DEFAULT NULL COMMENT '退库单细单ID',
  `storagetypecode` varchar(50) DEFAULT NULL COMMENT '仓储类型编码',
  `storagetypename` varchar(100) DEFAULT NULL COMMENT '仓储类型名称',
  `wmareacode` varchar(50) DEFAULT NULL COMMENT '库区编码',
  `wmareaname` varchar(100) DEFAULT NULL COMMENT '库区名称',
  `wmlocationcode` varchar(50) DEFAULT NULL COMMENT '库位编码',
  `locationqty` decimal(20,6) DEFAULT NULL COMMENT '库位本次入库数量',
  `storageunittype` varchar(50) DEFAULT NULL COMMENT '存储单元类型',
  `storageUnitCode` varchar(50) DEFAULT NULL COMMENT '存储单元编码',
  `versionid` bigint(20) DEFAULT NULL COMMENT '版本号',
  `clientcode` varchar(50) DEFAULT NULL COMMENT '集团编码',
  `createuserid` bigint(20) DEFAULT NULL COMMENT '创建人id',
    `createusername` varchar(100) DEFAULT NULL COMMENT '创建人名称',
    `createtime` datetime DEFAULT NULL COMMENT '创建时间',
    `modifyuserid` bigint(20) DEFAULT NULL COMMENT '修改人id',
    `modifyusername` varchar(100) DEFAULT NULL COMMENT '修改人名称',
    `modifytime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`returnstockdtllocationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退库库位信息';






```