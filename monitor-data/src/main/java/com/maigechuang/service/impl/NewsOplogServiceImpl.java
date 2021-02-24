package com.maigechuang.service.impl;

import com.maigechuang.entity.NewsOplog;
import com.maigechuang.mapper.caiji.NewsOplogMapper;
import com.maigechuang.service.NewsOplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsOplogServiceImpl implements NewsOplogService {



    @Autowired
    NewsOplogMapper newsOplogMapper;



    @Override
    public List<NewsOplog> getNewsLogList() {
        return newsOplogMapper.getNewsLogList();
    }

    @Override
    public int insertlog(String userIp,String text,String opTime) {

        NewsOplog newsOplog = new NewsOplog();
        newsOplog.setOpTime(opTime);
        newsOplog.setText(text);
        newsOplog.setUserIp(userIp);
        return newsOplogMapper.insertlog(newsOplog);
    }



}
