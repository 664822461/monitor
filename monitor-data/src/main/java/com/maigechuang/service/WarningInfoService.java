package com.maigechuang.service;

import com.maigechuang.entity.WarningInfo;
import com.maigechuang.entity.vo.WariningDataVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mgc on 2020/11/26.
 */
public interface WarningInfoService {

    /**
     * 插入一条预警信息
     * @param warningInfo
     * @return
     */
    int insertWarningInfo(WarningInfo warningInfo);

    WarningInfo findWarningInfoByTid( int tid);

    void updateWarnInfoById( int id, String time);


    int getCountByDate( String startTime,  String endTime);

    int getAllCount();
    void updateWaitDay(int id);

    List<WariningDataVO> findAllInfo(int page, int limit);

    int updateHandlerById(int id,String time);

        /*修改备注信息*/

    int updatebeizhuByid(@Param("id")int id,@Param("beizhu")String beizhu);

    int deleteWarningByTid(@Param("id") int id);
}
