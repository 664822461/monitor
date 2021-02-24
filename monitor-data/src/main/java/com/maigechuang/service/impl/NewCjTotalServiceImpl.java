package com.maigechuang.service.impl;

import com.maigechuang.entity.NewsCjTotal;
import com.maigechuang.entity.bo.MonitorData;
import com.maigechuang.entity.bo.MonitorSearch;
import com.maigechuang.entity.bo.ReadData;
import com.maigechuang.mapper.caiji.NewsCjTotalMapper;
import com.maigechuang.service.NewCjTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewCjTotalServiceImpl  implements NewCjTotalService{

    @Autowired
    NewsCjTotalMapper newsCjTotalMapper;

    @Override
    public List<MonitorData> getCjTotalList(MonitorSearch monitorData, int page, int limit, String startTime, String endTime) {
        Map map = new HashMap();
        map.put("limit",limit);
        int size = (page-1)*limit;
        map.put("size",size);
        map.put("MonitorSearch",monitorData);
        map.put("startTime",startTime);
        map.put("endTime",endTime);


        return newsCjTotalMapper.getCjTotalList(map);
    }

    @Override
    public int getTotal(MonitorSearch monitorData,String startTime, String endTime) {
        Map map = new HashMap();
        map.put("MonitorSearch",monitorData);
        map.put("startTime",startTime);
        map.put("endTime",endTime);

        return newsCjTotalMapper.getTotal(map);
    }

    @Override
    public List<NewsCjTotal> findTopList(String cjtime, String endTime) {

        return newsCjTotalMapper.findTopList(cjtime,endTime);
    }

    @Override
    public ReadData readcountById(int start, int end, int tid) {

        return newsCjTotalMapper.readcountById(start,end,tid);
    }

    @Override
    public int updateIsrestByTid(int tid, String time) {
        return newsCjTotalMapper.updateIsrestByTid(tid,time);
    }


}
