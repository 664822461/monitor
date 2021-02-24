package com.maigechuang.service.impl;

import com.maigechuang.entity.NewsCjTotal;
import com.maigechuang.entity.NewsSearchVO;
import com.maigechuang.entity.NewsSource;
import com.maigechuang.entity.bo.CaijiTotal;
import com.maigechuang.entity.vo.ExportNewsSourceVo;
import com.maigechuang.mapper.caiji.NewsSourceMapper;
import com.maigechuang.service.NewsOplogService;
import com.maigechuang.service.NewsSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 66482 on 2020/6/2.
 */
@Service
public class NewsSourceServiceImpl implements NewsSourceService {

    @Autowired
    NewsSourceMapper newsSourceMapper;

    @Autowired
    NewsOplogService newsOplogService;

    @Override
    public int getNewsCount(NewsSearchVO newsSearchVO) {
        Map<String,Object> map = new HashMap<>();
        map.put("newsSearch",newsSearchVO);
        return newsSourceMapper.getNewsCount(map);
    }

    @Override

    public List<NewsSource> getNewsList(int offset, int limit, NewsSearchVO newsSearchVO) {

        Map<String,Object> map = new HashMap<>();
        map.put("limit",limit);
        map.put("size",offset);
        map.put("newsSearch",newsSearchVO);
        List<NewsSource> newsList = newsSourceMapper.getNewsList(map);
        return newsList;
    }

    @Override
    public int deleteNewsById(int id) {
        int i = newsSourceMapper.deletenewsById(id);

        return i;


    }

    @Override
    public List<ExportNewsSourceVo> getAllNews(NewsSearchVO newsSearchVO) {
        return newsSourceMapper.getAllNews(newsSearchVO);
    }

    @Override
    public NewsSource findNewsById(int id) {
        return newsSourceMapper.findNewsById(id);
    }

    @Override
    public int insertNews(NewsSource newsSource) {
        return newsSourceMapper.insertNews(newsSource);
    }

    @Override
    public int updateNews(NewsSource newsSource) {
        return newsSourceMapper.updateNews(newsSource);
    }

    @Override
    public List<NewsSource> findNewsByName(String name) {
        return newsSourceMapper.findNewsByName(name);
    }

    @Override
    public CaijiTotal getaddtotal(int start, int end) {
        return newsSourceMapper.getaddtotal(start,end);
    }

    @Override
    public int getAllCount() {
        return newsSourceMapper.getAllCount();
    }

    @Override
    public int getCountByDate(String startTime, String endTime) {
        return newsSourceMapper.getCountByDate(startTime,endTime);
    }


}