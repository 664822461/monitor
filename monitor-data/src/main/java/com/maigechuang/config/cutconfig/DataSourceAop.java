package com.maigechuang.config.cutconfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Aspect
@Component
public class DataSourceAop {
    @Before("execution(* com.maigechuang.service.impl..*.*(..))")
    public void setDataSource2test01() {

        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.TEST01);
    }

    @Before("execution(* com.maigechuang.service.jkimpl..*.*(..))")
    public void setDataSource2test02() {

        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.TEST02);
    }
}
