package com.maigechuang.task;

import com.maigechuang.entity.NewsCjTotal;
import com.maigechuang.entity.NewsSource;
import com.maigechuang.mapper.caiji.NewsCjTotalMapper;
import com.maigechuang.mapper.caiji.NewsSourceMapper;
import com.maigechuang.service.ProjectsSourceService;
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
public class StaticScheduleTask {
    private Calendar c = Calendar.getInstance();
    private SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");
    @Autowired
    NewsCjTotalMapper newsCjTotalMapper;

    @Autowired
    NewsSourceMapper newsSourceMapper;

    @Autowired
    ProjectsSourceService projectsSourceService;

    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional(propagation = Propagation.REQUIRED)
    private void configureTasks(){

        List<NewsSource> nameList = newsSourceMapper.getNameList();
        c.setTime(new Date());

        c.add(Calendar.DAY_OF_MONTH, -1);
        Date time = c.getTime();

        NewsCjTotal newsCjTotal = new NewsCjTotal();
        for(NewsSource newsSource : nameList){
            //数据的插入
             newsCjTotal.setTid(newsSource.getId());
             newsCjTotal.setCjtime(time);
             newsCjTotal.setTotal(projectsSourceService.findCountByName(newsSource.getSourceWebSite(),df.format(time),df.format(new Date())));
             newsCjTotalMapper.insertData(newsCjTotal);
         }


    }

}
