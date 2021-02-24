package com.maigechuang.mapper.caiji;

import com.maigechuang.entity.WarningInfo;
import com.maigechuang.entity.vo.WariningDataVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mgc on 2020/11/26.
 */

@Mapper
public interface WarningInfoMapper {

    /**
     * 插入一条预警信息
     * @param warningInfo
     * @return
     */
    int insertWarningInfo(WarningInfo warningInfo);

    WarningInfo findWarningInfoByTid(@Param("tid") int tid);

    void updateWarnInfoById(@Param("id") int id,@Param("time") String time);

    int getCountByDate(@Param("startTime") String startTime,@Param("endTime") String endTime);

    int getAllCount();

    void updateWaitDay(@Param("id") int id);

    int deleteWarningByTid(@Param("id") int id);

    /**
     * 查询所有预警信息
     * @return
     */
    List<WariningDataVO> findAllInfo(@Param("page") int page, @Param("size") int size);

    int updateHandlerById(@Param("id")int id,@Param("time") String time);

    /*修改备注信息*/

    int updatebeizhuByid(@Param("id")int id,@Param("beizhu")String beizhu);

}
