package com.maigechuang.task;

import com.maigechuang.entity.NewsSource;
import com.maigechuang.entity.WarningInfo;
import com.maigechuang.entity.vo.WarningInfoVo;
import com.maigechuang.mapper.caiji.NewsCjTotalMapper;
import com.maigechuang.mapper.caiji.NewsSourceMapper;
import com.maigechuang.service.MonitorCateService;
import com.maigechuang.service.WarningInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class WarningScheduleTask {
    private Calendar c = Calendar.getInstance();
    private SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");


    @Autowired
    WarningInfoService warningInfoService;

    @Autowired
    NewsSourceMapper newsSourceMapper;

    @Autowired
    MonitorCateService monitorCateService;
    @Autowired
    NewsCjTotalMapper newsCjTotalMapper;

    //1级2天，2级5天，3级10天，4级20天

    @Scheduled(cron = "0 0 4 * * ?")
    @Transactional(propagation = Propagation.REQUIRED)
    private void configureTasks(){

        //首先把所有的采集源都查出来
        List<NewsSource> nameList = newsSourceMapper.getNameList();


        //然后遍历每一个次采集源
        for(NewsSource newsSource : nameList){
            int level = newsSource.getLevel();
            /*
            * 查到采集源的预警天数
            * */
            int monitorValueByLevel = monitorCateService.getMonitorValueByLevel(level);
            c.setTime(new Date());
            c.add(Calendar.DAY_OF_MONTH, -monitorValueByLevel);
            Date time = c.getTime();

            //  select sum(total) from [JK2018].[dbo].[news_cj_total]  where tid = 77 and cjtime >= '2020-11-23'

            /*
                去total表里面查  这个逻辑没有错 但是要是我已经处理过了呢
             */

            //去预警信息表里面看有没有这个采集源的预警记录

            WarningInfo warnInfo = warningInfoService.findWarningInfoByTid(newsSource.getId());
            if(warnInfo == null){

            }else if(warnInfo.getIsHandle() == 0) {
                //这里有个bug处理
                //一个20天没采集到信息的采集源 推出预警信息    然后我还未处理 第二天他又采集到信息了  这样的话还是判定未处理的一个状态
                //如果他重新有信息了  我应该把它从预警信息表里面设置为已处理
                WarningInfoVo warningDataTemp =newsCjTotalMapper.findWarningData(newsSource.getId(), df.format(time));
                if(warningDataTemp.getTemp() == 0 && warningDataTemp.getTotal() == 0){
                    warningInfoService.updateWarnInfoById(warnInfo.getId(), df.format(new Date()));
                    warningInfoService.updateWaitDay(warnInfo.getId());
                    continue;

                }else {
                    warningInfoService.updateHandlerById(warnInfo.getId(),df.format(new Date()));
                    continue;
                }


            }

            WarningInfoVo warningData = newsCjTotalMapper.findWarningData(newsSource.getId(), df.format(time));


            if(warningData.getTemp() == 0 && warningData.getTotal() == 0){




                WarningInfo warningInfo = new WarningInfo();
                warningInfo.setTid(newsSource.getId());
                warningInfo.setCreatedTime(df.format(new Date()));
                warningInfo.setIsHandle(0);
                warningInfo.setLevel(level);
                warningInfo.setWaitDay(monitorValueByLevel);
                warningInfo.setTempTime(df.format(new Date()));
                warningInfoService.insertWarningInfo(warningInfo);

            }

        }




    }

}
