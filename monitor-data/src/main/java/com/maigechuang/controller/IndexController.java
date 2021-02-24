package com.maigechuang.controller;


import com.maigechuang.config.DataSourceConfig;
import com.maigechuang.config.DataSourceConfigJK2018;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;

@Controller
public class IndexController {


    @Autowired
    DataSourceProperties caijiDataSourceProperties;

    @RequestMapping("/monitor/list")
    public String getlistPage(){

        return "monitor/list";
    }

    @RequestMapping("/warn")
    public String warn(){


        return  "monitor/warn";
    }


    @RequestMapping("/index")
    public String index(){

        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/add")
    public String getadd(){
        return "collect/add";
    }

    @RequestMapping("/note")
    public String getnote(){
        return "other/note";
    }


    @RequestMapping("/category/grade")
    public String getgrade(){
        return "category/grade";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("/edituser")
    public String useredit(){

        return "user/edit";
    }

}
