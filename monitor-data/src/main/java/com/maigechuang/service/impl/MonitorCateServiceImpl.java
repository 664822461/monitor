package com.maigechuang.service.impl;

import com.maigechuang.entity.MonitorCate;
import com.maigechuang.mapper.caiji.MonitorCateMapper;
import com.maigechuang.service.MonitorCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mgc on 2020/11/26.
 */
@Service
public class MonitorCateServiceImpl implements MonitorCateService{

    @Autowired
    MonitorCateMapper monitorCateMapper;

    @Override
    public int getMonitorValueByLevel(int level) {
        return monitorCateMapper.getMonitorValueByLevel(level);
    }

    @Override
    public List<MonitorCate> getGradeData(int page, int limit) {

        int size = (page-1)*limit;
        return monitorCateMapper.getGradeData(page,size);
    }

    @Override
    public int getCount() {
        return monitorCateMapper.getCount();
    }

    @Override
    public void updateMonitorValueById(int id, int monitorValue) {
         monitorCateMapper.updateMonitorValueById(id,monitorValue);
    }


}
