package com.maigechuang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.maigechuang.service","com.maigechuang.controller","com.maigechuang.mapper","com.maigechuang"})
//extends SpringBootServletInitializer
public class MonitorApplication {

    //测试
    //测试
    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }

/*
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MonitorApplication.class);
    }*/




}
