package com.maigechuang.service.impl;

import com.maigechuang.entity.WarningInfo;
import com.maigechuang.entity.vo.WariningDataVO;
import com.maigechuang.mapper.caiji.WarningInfoMapper;
import com.maigechuang.service.WarningInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mgc on 2020/11/26.
 */

@Service
public class WarningServiceImpl implements WarningInfoService {


    @Autowired
    WarningInfoMapper warningInfoMapper;


    @Override
    public int insertWarningInfo(WarningInfo warningInfo) {

        int i = warningInfoMapper.insertWarningInfo(warningInfo);
        return i;
    }

    @Override
    public WarningInfo findWarningInfoByTid(int tid) {
        return warningInfoMapper.findWarningInfoByTid(tid);
    }

    @Override
    public void updateWarnInfoById(int id, String time) {
        warningInfoMapper.updateWarnInfoById(id,time);
    }

    @Override
    public int getCountByDate(String startTime, String endTime) {
        return warningInfoMapper.getCountByDate(startTime,endTime);
    }

    @Override
    public int getAllCount() {
        return warningInfoMapper.getAllCount();
    }

    @Override
    public void updateWaitDay(int id) {
        warningInfoMapper.updateWaitDay(id);
    }

    @Override
    public List<WariningDataVO> findAllInfo(int page, int limit) {
        int size = (page-1)*limit;
        return warningInfoMapper.findAllInfo(page,limit);
    }

    @Override
    public int updateHandlerById(int id,String time) {
         return warningInfoMapper.updateHandlerById(id,time);
}

    @Override
    public int updatebeizhuByid(int id, String beizhu) {

        return  warningInfoMapper.updatebeizhuByid(id,beizhu);
    }

    @Override
    public int deleteWarningByTid(int id) {
        return warningInfoMapper.deleteWarningByTid(id);
    }


}
