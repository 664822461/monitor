package com.maigechuang.service;

import com.maigechuang.entity.NewsCjTotal;
import com.maigechuang.entity.SendMessage;
import com.maigechuang.entity.bo.MonitorData;
import com.maigechuang.entity.bo.MonitorSearch;
import com.maigechuang.entity.bo.ReadData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface NewCjTotalService {

    //分页和条件查询
    List<MonitorData> getCjTotalList(MonitorSearch monitorData, int page, int limit, String startTime, String endTime);

    //查询总数  有条件就得带上
    int getTotal(MonitorSearch monitorData,String startTime,String endTime);

    List<NewsCjTotal> findTopList(String cjtime,String endTime);

    ReadData readcountById(int start, int end, int tid);

    int updateIsrestByTid(int tid,String time);

}
