package com.maigechuang.service;

import com.maigechuang.entity.NewsOplog;

import java.util.List;

public interface NewsOplogService {

    List<NewsOplog> getNewsLogList();

    int insertlog(String userIp,String text,String opTime);
}
