package com.maigechuang.controller;

import com.maigechuang.entity.MonitorCate;
import com.maigechuang.entity.WarningInfo;
import com.maigechuang.entity.response.CURDResult;
import com.maigechuang.entity.response.PageResult;
import com.maigechuang.entity.vo.WariningDataVO;
import com.maigechuang.service.MonitorCateService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by mgc on 2020/11/27.
 */

@Controller
public class CategoryController {


    @Autowired
    MonitorCateService monitorCateService;

    @RequestMapping("/category/gradedata")
    @ResponseBody
    public PageResult<MonitorCate>  getGradedata(int page, int limit){

        if (page == 0) {
            page = 1;
        }
     //查到list

        PageResult pageResult = new PageResult();
        pageResult.setCount(monitorCateService.getCount());
        pageResult.setData(monitorCateService.getGradeData(page,limit));
        pageResult.setCode(0);
        return pageResult;

    }



    @GetMapping("/category/gradeedit")
    public String geteditPage(@RequestParam("id") int id, @RequestParam("monitorValue")int monitorValue, Model model){
        model.addAttribute("id",id);
        model.addAttribute("monitorValue",monitorValue);
        return "category/gradeedit";
    }

    @GetMapping("/grade/edt")
    @ResponseBody
    public CURDResult editPage(@RequestParam("id") int id, @RequestParam("monitorValue")int monitorValue, Model model){
        CURDResult curdResult = new CURDResult();

        if(id < 0 || monitorValue < 0){
            curdResult.setMsg("参数错误！");
            curdResult.setSuccess(0);
            return curdResult;
        }

        //修改操作
         monitorCateService.updateMonitorValueById(id, monitorValue);
        curdResult.setMsg("修改成功");
        curdResult.setSuccess(1);
        return curdResult;
    }



}
