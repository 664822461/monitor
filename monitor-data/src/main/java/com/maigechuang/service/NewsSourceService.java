package com.maigechuang.service;

import com.maigechuang.entity.NewsCjTotal;
import com.maigechuang.entity.NewsSearchVO;
import com.maigechuang.entity.NewsSource;
import com.maigechuang.entity.bo.CaijiTotal;
import com.maigechuang.entity.vo.ExportNewsSourceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 66482 on 2020/6/2.
 */
public interface NewsSourceService {

    //获取采集源总数
    int getNewsCount(NewsSearchVO newsSearchVO);

    //分页获取列表
    List<NewsSource> getNewsList(int offset, int limit, NewsSearchVO newsSearchVO);

    int deleteNewsById(int id);

    NewsSource findNewsById(int id);

    int insertNews(NewsSource newsSource);

    int updateNews(NewsSource newsSource);

    List<NewsSource> findNewsByName(String name);

    CaijiTotal getaddtotal(int start, int end);

    int getAllCount();

    int getCountByDate( String startTime, String endTime);

    List<ExportNewsSourceVo> getAllNews(NewsSearchVO newsSearch);

}
