
package com.maigechuang.config;

import com.maigechuang.config.cutconfig.DataSourceType;
import com.maigechuang.config.cutconfig.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * Mybatis主数据源ds1配置
 * 多数据源配置依赖数据源配置
 * @see  DataSourceConfig
 */

@Configuration
@MapperScan(basePackages = DataSourceConfigJK.SCAN, sqlSessionTemplateRef  = "jkSqlSessionTemplate")
public class DataSourceConfigJK {

    static final String SCAN = "com.maigechuang.mapper.jk.*";
    //主数据源 ds1数据源

    @Bean("jkSqlSessionFactory")
    public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("jkDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);


        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath*:mapper/*.xml"));


        return sqlSessionFactory.getObject();
    }


    @Bean(name = "jkTransactionManager")
    public DataSourceTransactionManager ds1TransactionManager(@Qualifier("jkDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean(name = "jkSqlSessionTemplate")
    public SqlSessionTemplate ds1SqlSessionTemplate(@Qualifier("jkSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }



}
