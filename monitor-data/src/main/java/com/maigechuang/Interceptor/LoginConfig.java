package com.maigechuang.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by mgc on 2020/11/29.
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        //默认到登陆页
        registry.addViewController("/").setViewName("forward:index");
    }
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器


        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new MyInterceptor());
        interceptorRegistration.addPathPatterns("/**").addPathPatterns("index.html");                      //所有路径都被拦截
        interceptorRegistration.excludePathPatterns(                         //添加不拦截路径
                "/login",
                "/error/**",
                "/user/login",//登录
                "/**/*.html",            //html静态资源
                "/**/*.js",              //js静态资源
                "/**/*.css",             //css静态资源
                "/**/*.woff",
                "/**/*.ttf",
                "/**/*.png"
        );


    }
}
