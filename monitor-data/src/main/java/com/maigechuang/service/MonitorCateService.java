package com.maigechuang.service;

import com.maigechuang.entity.MonitorCate;
import com.maigechuang.entity.WarningInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mgc on 2020/11/26.
 */


public interface MonitorCateService {
    int getMonitorValueByLevel(int level);

    List<MonitorCate> getGradeData( int page, int limit);

    int getCount();

    void updateMonitorValueById( int id,int monitorValue);


}
