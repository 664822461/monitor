package com.maigechuang.controller;

import com.maigechuang.entity.WarningInfo;
import com.maigechuang.entity.bo.MonitorData;
import com.maigechuang.entity.response.JSONResult;
import com.maigechuang.entity.response.PageResult;
import com.maigechuang.entity.vo.WariningDataVO;
import com.maigechuang.service.NewCjTotalService;
import com.maigechuang.service.NewsOplogService;
import com.maigechuang.service.NewsSourceService;
import com.maigechuang.service.WarningInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mgc on 2020/11/26.
 */

@Controller
public class WarningInfoController {

    @Autowired
    WarningInfoService warningInfoService;

    @Autowired
    NewCjTotalService newCjTotalService;

    @Autowired
    NewsSourceService newsSourceService;

    @Autowired
    private NewsOplogService newsOplogService;

    private SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd");

    private SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Calendar c = Calendar.getInstance();

    @RequestMapping("/warningInfo/listdata")
    @ResponseBody
    public PageResult<WarningInfo> getWarningInfo(int page, int limit) {
        if (page == 0) {
            page = 1;
        }
        List<WariningDataVO> allInfo = warningInfoService.findAllInfo(page, limit);

        PageResult pageResult = new PageResult();
        pageResult.setCount(warningInfoService.getAllCount());
        pageResult.setData(allInfo);
        pageResult.setCode(0);
        return pageResult;
    }


    @PostMapping("/warningInfo/WarnHandler")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONResult WarnHandler(@Param("id") int id, @Param("sourceWebSite") String sourceWebSite, HttpServletRequest request) {
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, -2);
        Date time = c.getTime();

        int x = warningInfoService.updateHandlerById(id, myFmt.format(new Date()));
        int id1 = newsSourceService.findNewsByName(sourceWebSite).get(0).getId();
        int y = newCjTotalService.updateIsrestByTid(id1, myFmt.format(time));


        if (x + y >= 2) {
            newsOplogService.insertlog(request.getRemoteAddr(), "【采集监测】---调整采集源[" + sourceWebSite + "]设为正常采集  ", myFmt2.format(new Date()));
            return JSONResult.ok();

        } else {

            newsOplogService.insertlog(request.getRemoteAddr(), "【采集监测】！！调整失败！！ 调整采集源【" + sourceWebSite + "】设为正常采集  ", myFmt2.format(new Date()));
            //throw  new RuntimeException("测试异常");
            return JSONResult.errorMsg("处理失败！");

        }
    }


        @GetMapping("/warningInfo/warnedit")
        public String warnedit (@Param("id") int id,@Param("beizhu") String beizhu ,Model model){

        if(beizhu == null){
            beizhu = "";
        }
            model.addAttribute("id",id);

            String[] split = beizhu.split("：");
            if(split.length  < 2){
                split = beizhu.split(":");
            }
            if(split.length  == 1){
                String user = split[0];
                user= user.replace(":","");;
                user=  user.replace("：","");
                model.addAttribute("user",user);
                return "monitor/warnedit";
            }
            String user = split[0];
           String beizhu2 = split[1];

           // String beizhu2 = "ssssasdfasdfa&#10;测试";
            model.addAttribute("user",user);
            model.addAttribute("beizhu",beizhu2);

        return "monitor/warnedit";

    }

    @GetMapping("/warningbeizhu/edit")
    @ResponseBody
    public JSONResult warnedit (@Param("id") int id,@Param("user")String user,@Param("beizhu")String beizhu){

       int i = warningInfoService.updatebeizhuByid(id,user+"："+beizhu);

       if(i > 0){
           return JSONResult.ok();
       }
        return JSONResult.errorMsg("修改失败！请检查！");

    }
}
