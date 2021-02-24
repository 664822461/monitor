
package com.maigechuang.config;

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


/**
 * Mybatis主数据源ds1配置
 * 多数据源配置依赖数据源配置
 * @see  DataSourceConfig
 */

@Configuration
@MapperScan(basePackages = DataSourceConfigJK2018.SCAN, sqlSessionTemplateRef  = "caijiSqlSessionTemplate")
public class DataSourceConfigJK2018 {

    static final String SCAN = "com.maigechuang.mapper.caiji.*";
    //主数据源 ds1数据源
    @Primary
    @Bean("caijiSqlSessionFactory")
    public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("caijiDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);


        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath*:/mapper/*.xml"));
        sqlSessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);

        return sqlSessionFactory.getObject();
    }

    @Primary
    @Bean(name = "caijiTransactionManager")
    public DataSourceTransactionManager ds1TransactionManager(@Qualifier("caijiDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "caijiSqlSessionTemplate")
    public SqlSessionTemplate ds1SqlSessionTemplate(@Qualifier("caijiSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
