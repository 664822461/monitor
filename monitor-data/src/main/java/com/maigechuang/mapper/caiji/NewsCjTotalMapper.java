package com.maigechuang.mapper.caiji;

import com.maigechuang.entity.NewsCjTotal;
import com.maigechuang.entity.bo.MonitorData;
import com.maigechuang.entity.bo.ReadData;
import com.maigechuang.entity.vo.WarningInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsCjTotalMapper {
    //分页和条件查询
    List<MonitorData> getCjTotalList(Map map);

    //查询总数  有条件就得带上
    int getTotal(Map map);

    void insertData(NewsCjTotal newsCjTotal);

    List<NewsCjTotal> findTopList(@Param("cjtime") String cjtime,@Param("endTime")String endTime);


    ReadData readcountById(@Param("start") int start,@Param("end") int end,@Param("tid") int tid);

    /*
        根据采集源id和等级时间查结果
     */
    WarningInfoVo findWarningData(@Param("tid") int tid,@Param("cjtime") String cjtime);


    int updateIsrestByTid(@Param("tid") int tid,@Param("time") String time);
}
