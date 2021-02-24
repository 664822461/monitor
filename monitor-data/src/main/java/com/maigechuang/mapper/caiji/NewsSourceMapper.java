package com.maigechuang.mapper.caiji;

import com.maigechuang.entity.NewsCjTotal;
import com.maigechuang.entity.NewsSearchVO;
import com.maigechuang.entity.NewsSource;
import com.maigechuang.entity.bo.CaijiTotal;
import com.maigechuang.entity.vo.ExportNewsSourceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 66482 on 2020/6/2.
 */

@Mapper
public interface NewsSourceMapper {

    int getNewsCount(Map<String,Object> map);

    List<NewsSource> getNewsList(Map<String,Object> map);

    int deletenewsById(@Param("id") int id);

    NewsSource findNewsById(@Param("id") int id);

    int insertNews(NewsSource newsSource);

    int updateNews(NewsSource newsSource);

    List<NewsSource> getNameList();

    List<NewsSource> findNewsByName(@Param("name") String name);

    CaijiTotal getaddtotal(@Param("start") int start, @Param("end") int end);

    int getAllCount();

    int getCountByDate(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<ExportNewsSourceVo> getAllNews(NewsSearchVO newsSearch);

    List<NewsSource> findtest();
}
