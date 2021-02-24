package com.maigechuang.controller;


import com.maigechuang.entity.NewsCjTotal;
import com.maigechuang.entity.NewsSource;
import com.maigechuang.entity.bo.CaijiTotal;
import com.maigechuang.entity.bo.MonitorData;
import com.maigechuang.entity.bo.MonitorSearch;
import com.maigechuang.entity.bo.ReadData;
import com.maigechuang.entity.response.PageResult;
import com.maigechuang.service.NewCjTotalService;
import com.maigechuang.service.NewsSourceService;
import com.maigechuang.service.impl.NewsSourceServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class MonitorController {


    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private Calendar c = Calendar.getInstance();

    @Autowired
    NewCjTotalService newCjTotalService;

    @Autowired
    NewsSourceService newsSourceService;

    @RequestMapping("/monitor/listdata")
    @ResponseBody
    public PageResult<MonitorData> getlist(MonitorSearch monitorData, int page, int limit, String startTime,String endTime){

        if(page == 0){
            page = 1;
        }

        if(StringUtils.isBlank(startTime)){
            c.setTime(new Date());

            c.add(Calendar.DAY_OF_MONTH, -1);

            startTime = df.format(c.getTime());

        }

        if(StringUtils.isBlank(endTime)){
            endTime = df.format(new Date());

        }else{


            try {
                c.setTime(df.parse(endTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DAY_OF_MONTH, 1);

            endTime = df.format(c.getTime());
        }
        System.out.println("");
        List<MonitorData> cjTotalList = newCjTotalService.getCjTotalList(monitorData, page, limit,startTime,endTime);

        PageResult pageResult = new PageResult();
        pageResult.setCount(newCjTotalService.getTotal(monitorData,startTime,endTime));
        pageResult.setData(cjTotalList);
        pageResult.setCode(0);
        return pageResult;
    }

    @RequestMapping("/monitor/toplist")
    @ResponseBody
    public List<CaijiTotal> gettoplist(){


        c.setTime(new Date());
  c.add(Calendar.DAY_OF_MONTH, -1);



        List<CaijiTotal> caijiTotalList = new ArrayList<>();

        List<NewsCjTotal> topList = newCjTotalService.findTopList(df.format(c.getTime()),df.format(new Date()));
        for (NewsCjTotal newsCjTotal:
                topList) {
            CaijiTotal caijiTotal = new CaijiTotal();
            caijiTotal.setTotal(newsCjTotal.getTotal());
            caijiTotal.setDate(newsSourceService.findNewsById(newsCjTotal.getTid()).getSourceWebSite());
            caijiTotalList.add(caijiTotal);
        }

        return caijiTotalList;
    }



    @RequestMapping("/readData")
    @ResponseBody
    public List<ReadData> readData(Model model,String cjname){
        List<ReadData> readList = new ArrayList<>();
        List<NewsSource> newsByName = newsSourceService.findNewsByName(cjname);

        for(int i=10;i>0;i--) {
            ReadData readData = newCjTotalService.readcountById(i - 1, i, newsByName.get(0).getId());
            if(readData.getTotal() == null){
                readData.setTotal(0);
            }
            readList.add(readData);
                }


                return readList;

    }



    @RequestMapping("/readDatapage")
    public String readDatapage(Model model,String cjname){

        model.addAttribute("cjname",cjname);


        return "monitor/readpage";

    }





}
