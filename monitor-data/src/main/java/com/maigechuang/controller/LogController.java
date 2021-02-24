package com.maigechuang.controller;


import com.maigechuang.entity.NewsOplog;
import com.maigechuang.service.NewsOplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LogController {

    @Autowired
    NewsOplogService newsOplogService;

    @RequestMapping("/log")
    public String getlog(Model model){


        List<NewsOplog> newsLogList = newsOplogService.getNewsLogList();
        model.addAttribute("newsLogList",newsLogList);
        return "log/log";
    }

}
