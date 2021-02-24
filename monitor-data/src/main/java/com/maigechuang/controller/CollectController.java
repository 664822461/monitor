package com.maigechuang.controller;

import com.maigechuang.entity.NewsCjTotal;
import com.maigechuang.entity.NewsSearchVO;
import com.maigechuang.entity.NewsSource;
import com.maigechuang.entity.Page;
import com.maigechuang.entity.bo.CaijiTotal;
import com.maigechuang.entity.response.CURDResult;
import com.maigechuang.entity.vo.ExportNewsSourceVo;
import com.maigechuang.entity.vo.IndexShow;
import com.maigechuang.mapper.caiji.NewsSourceMapper;
import com.maigechuang.service.NewsOplogService;
import com.maigechuang.service.NewsSourceService;
import com.maigechuang.service.ProjectsSourceService;
import com.maigechuang.service.WarningInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CollectController {


    @Autowired
    private ProjectsSourceService projectsSourceService;

    @Autowired
    private NewsSourceService newsSourceService;

    @Autowired
    private NewsOplogService newsOplogService;


    @Autowired
    private WarningInfoService warningInfoService;

    private SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd");

    private SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");






    @RequestMapping("/collect/delete")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public CURDResult deletenews(@RequestParam("id") int id, HttpServletRequest request){

        newsOplogService.insertlog(request.getRemoteAddr(),"删除了采集源【"+newsSourceService.findNewsById(id).getSourceWebSite()+"】",myFmt2.format(new Date()));

        int i = newsSourceService.deleteNewsById(id);
         warningInfoService.deleteWarningByTid(id);
        CURDResult curdResult = new CURDResult();
        if(i != 1){
            curdResult.setSuccess(0);
        }



        return curdResult;

    }


    @RequestMapping("/collect/list")
    public String getPage(NewsSearchVO newsSearch,Model model, Page page ){


        model.addAttribute("searchArea",newsSearch.getSearchArea());
        model.addAttribute("searchName",newsSearch.getSearchName());
        model.addAttribute("searchAddress",newsSearch.getSearchAddress());
        model.addAttribute("searchType",newsSearch.getSearchType());

        page.setRows(newsSourceService.getNewsCount(newsSearch));
        page.setPath("/collect/list");

        List<NewsSource> newsList = newsSourceService.getNewsList(page.getOffset(), page.getLimit(),newsSearch);
        int allCount = newsSourceService.getAllCount();
        model.addAttribute("newsList", newsList);
        model.addAttribute("allCount", allCount);
        if(StringUtils.isBlank(newsSearch.getSearchAddress()) && StringUtils.isBlank(newsSearch.getSearchArea()) &&
                StringUtils.isBlank(newsSearch.getSearchName()) && newsSearch.getSearchType() == 0){

            model.addAttribute("searchArea","");
            model.addAttribute("searchName","");
            model.addAttribute("searchAddress","");
            model.addAttribute("searchType",0);
        }


        return "collect/list";
    }



    @RequestMapping("/collect/add")
    @Transactional
    @ResponseBody
    public CURDResult add(NewsSource newsSource,Model model,HttpServletRequest request){
        CURDResult curdResult = new CURDResult();
        List<NewsSource> newsByName = newsSourceService.findNewsByName(newsSource.getSourceWebSite());


        if(StringUtils.isBlank(newsSource.getArea())){
            curdResult.setSuccess(0);
            curdResult.setMsg("地区不能设置为空，请检查！");
            return curdResult;
        }
        if(StringUtils.isBlank(newsSource.getSourceWebSite())){
            curdResult.setSuccess(0);
            curdResult.setMsg("名称不能设置为空，请检查！");
            return curdResult;
        }
        if(StringUtils.isBlank(newsSource.getSourceInfo())){
            curdResult.setSuccess(0);
            curdResult.setMsg("地址不能设置为空，请检查！");
            return curdResult;
        }






        if(newsByName.size() > 0){
            curdResult.setSuccess(0);
            curdResult.setMsg("已有重复名称采集源，请检查！");
            return curdResult;
        }

        newsSource.setStatus(1);
        newsSource.setUpdateTime(myFmt.format(new Date()));

        newsOplogService.insertlog(request.getRemoteAddr(),"增加了采集源【"+newsSource.getSourceWebSite()+"】",myFmt2.format(new Date()));


        int i = newsSourceService.insertNews(newsSource);

        if(i != 1){
            curdResult.setSuccess(0);
        }

        curdResult.setMsg("");
        return curdResult;
    }



    @RequestMapping("/collect/edit")
    @Transactional
    public String edit(@RequestParam("id") int id,Model model,HttpServletRequest request){
        NewsSource news = newsSourceService.findNewsById(id);
        model.addAttribute("news",news);
        return "collect/edit";
    }





    @RequestMapping("/collect/edt")
    @Transactional
    @ResponseBody
    public CURDResult edt(NewsSource newsSource,Model model,HttpServletRequest request){
        CURDResult curdResult = new CURDResult();
        List<NewsSource> newsByName = newsSourceService.findNewsByName(newsSource.getSourceWebSite());
        if(StringUtils.isBlank(newsSource.getArea())){
            curdResult.setSuccess(0);
            curdResult.setMsg("地区不能设置为空，请检查！");
            return curdResult;
        }
        if(StringUtils.isBlank(newsSource.getSourceWebSite())){
            curdResult.setSuccess(0);
            curdResult.setMsg("名称不能设置为空，请检查！");
            return curdResult;
        }
        if(StringUtils.isBlank(newsSource.getSourceInfo())){
            curdResult.setSuccess(0);
            curdResult.setMsg("地址不能设置为空，请检查！");
            return curdResult;
        }




        NewsSource newsOld = newsSourceService.findNewsById(newsSource.getId());
        if(newsByName.size() > 0){//大于
            System.out.println("xx");
        if(!(newsSourceService.findNewsById(newsByName.get(0).getId()).getSourceWebSite().equals(newsOld.getSourceWebSite()))){


                curdResult.setSuccess(0);
                curdResult.setMsg("已有重复名称采集源，请检查！");
                return curdResult;

        }
        }
        newsOplogService.insertlog(request.getRemoteAddr(),

                "修改了采集源【"+newsOld.getSourceWebSite()+"】"+" <br /> " +
                        "原名称："+newsOld.getSourceWebSite()+",地区："+newsOld.getArea()+",地址："+newsOld.getSourceInfo()+",监测等级："+newsOld.getLevel()+",所属类别："+newsOld.getType()+"<br />" +
                        "更正后名称："+newsSource.getSourceWebSite()+",地区："+newsSource.getArea()+",地址："+newsSource.getSourceInfo()+",监测等级："+newsSource.getLevel()+",所属类别："+newsSource.getType(),

                myFmt2.format(new Date()));


        int i = newsSourceService.updateNews(newsSource);

        if(i != 1){
            curdResult.setSuccess(0);
        }

        curdResult.setMsg("");
        return curdResult;
    }


    @RequestMapping("/collect/getaddtotal")
    @ResponseBody
    public List<CaijiTotal> getaddTotal(){

        List<CaijiTotal> list = new ArrayList<>();

        for(int i=10;i>0;i--) {

            CaijiTotal getaddtotal = newsSourceService.getaddtotal(i-1,i);
            list.add(getaddtotal);
        }

        return list;
    }


    @RequestMapping("/collect/export")
    @ResponseBody
    public void export(NewsSearchVO newsSearch, HttpServletResponse response){
        List<ExportNewsSourceVo> allNews = newsSourceService.getAllNews(newsSearch);
        //创建poi导出数据对象
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook();

        //创建sheet页
        SXSSFSheet sheet = sxssfWorkbook.createSheet("采集源列表");
        //创建表头
        SXSSFRow headRow = sheet.createRow(0);
        //设置表头信息
        headRow.createCell(0).setCellValue("序号");
        headRow.createCell(1).setCellValue("地区");
        headRow.createCell(2).setCellValue("名称");
        headRow.createCell(3).setCellValue("一级网址");
        headRow.createCell(4).setCellValue("类别");
        headRow.createCell(5).setCellValue("监测级别");
        headRow.createCell(6).setCellValue("更新时间");
        // 遍历上面数据库查到的数据
        int x = 1;
        for (ExportNewsSourceVo l : allNews) {
            //序号

            //填充数据
            SXSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            //序号
            dataRow.createCell(0).setCellValue(x);
            //看你实体类在进行填充
            dataRow.createCell(1).setCellValue(l.getArea());
            dataRow.createCell(2).setCellValue(l.getSourceWebSite());
            dataRow.createCell(3).setCellValue(l.getSourceInfo());
            dataRow.createCell(4).setCellValue(l.getCategoryValue());
            dataRow.createCell(5).setCellValue(l.getMonlevel());
            dataRow.createCell(6).setCellValue(l.getUpdateTime());

            x++;
        }

        // 下载导出
        String filename = "采集源";
        // 设置头信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        //一定要设置成xlsx格式
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename + ".xlsx", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //创建一个输出流
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //写入数据
        try {
            sxssfWorkbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 关闭
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sxssfWorkbook.close();
        } catch (IOException e) {


        }
    }




    @RequestMapping("/collect/indexshow")
    @ResponseBody
    public IndexShow indexshow() {
        IndexShow indexShow = new IndexShow();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(new Date());
        c1.add(Calendar.DAY_OF_MONTH, -1);
        c2.setTime(new Date());
        c2.add(Calendar.DAY_OF_MONTH, -2);

        Date time1 = c1.getTime();
        Date time2 = c2.getTime();

        //昨日采集标讯
        int yesData = projectsSourceService.findyesData(myFmt.format(time1), myFmt.format(new Date()));
        //昨日有效标讯
        int efvData = projectsSourceService.findeyesfvData(myFmt.format(time1), myFmt.format(new Date()));
        System.out.println(myFmt.format(time1));
        System.out.println(myFmt.format(new Date()));

        //前日采集标讯
        int beforeyesData = projectsSourceService.beforeyesData(myFmt.format(time2), myFmt.format(time1));
        //前日有效标讯
        int beforeyesefvData = projectsSourceService.beforeyesefvData(myFmt.format(time2), myFmt.format(time1));

        // 相比前日比例
        String yesDataScale = count(yesData, beforeyesData);

        // 相较前日比例

        String efvDataScale  = count(efvData, beforeyesefvData);


        //新增监测采集源
        int addNews = newsSourceService.getCountByDate(myFmt.format(time1), myFmt.format(new Date()));

        // 总监测采集源

        int newsCount = newsSourceService.getAllCount();
        //新增预警信息
        //这里第一次写的时候出错了 改了mapper 但是这里没有改 -记一下
        int monitorData = warningInfoService.getCountByDate(myFmt.format(time1), myFmt.format(new Date()));
        // //待处理任务总数
        int monitorDataCount = warningInfoService.getAllCount();


        indexShow.setYesData(String.valueOf(yesData));
        indexShow.setEfvData(String.valueOf(efvData));
        indexShow.setYesDataScale(yesDataScale);
        indexShow.setEfvDataScale(efvDataScale);
        indexShow.setAddNews(String.valueOf(addNews));
        indexShow.setNewsCount(String.valueOf(newsCount));
        indexShow.setMonitorData(String.valueOf(monitorData));
        indexShow.setMonitorDataCount(String.valueOf(monitorDataCount));
        return  indexShow;



    }

    private String count(int x,int y ){

    int temp1 = x;
    int temp2 = y ;
    int  temp3= x - y;
        String temp = String.valueOf(temp3);

       if(temp.charAt(0) == '-'){
          return "减少"+ temp.substring(1);
       }else {
           return "增长" + temp;
       }

    }
}
