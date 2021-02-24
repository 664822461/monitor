package com.maigechuang.entity.vo;

import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;

/**
 * Created by mgc on 2020/11/26.
 */
@Data
public class IndexShow {

    //昨日采集标讯
    private String yesData;
    //相比前日比例
    private String yesDataScale;

    //昨日有效标讯
    private String efvData;
    //相较前日比例
    private String efvDataScale;

    //新增监测采集源
    private String addNews;
    //总监测采集源
    private String newsCount;

    //新增预警信息
    private String monitorData;
    //待处理任务总数
    private String monitorDataCount;



}
