package com.maigechuang.config;

import com.maigechuang.config.DataSourceConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * JdbcTemplate多数据源配置
 * 依赖于数据源配置
 *
 * @see DataSourceConfig
 */

@Configuration
public class JdbcTemplateDataSourceConfig {

    //JdbcTemplate主数据源ds1数据源
    @Primary
    @Bean(name = "caijiJdbcTemplate")
    public JdbcTemplate ds1JdbcTemplate(@Qualifier("caijiDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

//JdbcTemplate第二个ds2数据源
    @Bean(name = "jkJdbcTemplate")
    public JdbcTemplate ds2JdbcTemplate(@Qualifier("jkDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
