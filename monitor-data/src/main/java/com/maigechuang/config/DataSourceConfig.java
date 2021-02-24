
package com.maigechuang.config;

import com.maigechuang.config.cutconfig.DataSourceType;
import com.maigechuang.config.cutconfig.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DataSourceConfig {

    //主数据源配置 ds1数据源
    @Primary
    @Bean(name = "caijiDataSourceProperties")
    //@ConfigurationProperties(prefix = "spring.datasource.caiji")
    public DataSourceProperties ds1DataSourceProperties() {

        DataSourceProperties d =  new DataSourceProperties();
        d.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        d.setUsername("sa");
        d.setUrl("jdbc:sqlserver://192.168.1.102:1433;DatabaseName=JK2018");
        d.setPassword("Server123$%^");

        return d;
    }

    //主数据源 ds1数据源
    @Primary
    @Bean(name = "caijiDataSource")
    public DataSource ds1DataSource(@Qualifier("caijiDataSourceProperties") DataSourceProperties dataSourceProperties) {
        System.out.println(dataSourceProperties);
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }


    //第二个ds2数据源配置
    @Bean(name = "jkDataSourceProperties")
    //@ConfigurationProperties(prefix = "spring.datasource.jk")
    public DataSourceProperties ds2DataSourceProperties(){

        DataSourceProperties d =  new DataSourceProperties();
        d.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        d.setUsername("sa");
        d.setUrl("jdbc:sqlserver://192.168.1.102:1433;DatabaseName=jk");
        d.setPassword("Server123$%^");

        return d;
    }
    //第二个ds2数据源
    @Bean("jkDataSource")
    public DataSource ds2DataSource(@Qualifier("jkDataSourceProperties") DataSourceProperties dataSourceProperties) {
        System.out.println(dataSourceProperties);
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DataSource(@Qualifier("caijiDataSource") DataSource test1DataSource,
                                        @Qualifier("jkDataSource") DataSource test2DataSource) {

        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceType.DataBaseType.TEST01, test1DataSource);
        targetDataSource.put(DataSourceType.DataBaseType.TEST02, test2DataSource);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(test1DataSource);
        return dataSource;
    }




}
