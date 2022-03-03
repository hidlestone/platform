package com.fallframework.platform.starter.ureport.datasource;

import com.bstek.ureport.definition.datasource.BuildinDatasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ureport 数据源
 *
 * @author zhuangpf
 */
@Component
public class UreportDataSource implements BuildinDatasource {

	private Logger LOGGER = LoggerFactory.getLogger(UreportDataSource.class);

	private static final String NAME = "ureportdatasource";
	@Autowired
	private DataSource dataSource;

	@Override
	public String name() {
		return NAME;
	}

	@Override
	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			LOGGER.error("ureport datasource connect fial");
			e.printStackTrace();
		}
		return null;
	}

}
