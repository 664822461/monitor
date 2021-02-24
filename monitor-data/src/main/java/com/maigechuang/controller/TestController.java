package com.maigechuang.controller;

import com.maigechuang.entity.NewsSource;
import com.maigechuang.mapper.jk.NewsSourceTMapper;
import com.maigechuang.service.NewsSourceService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mgc on 2020/12/8.
 */

@Controller
public class TestController {

    private SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private NewsSourceService newsSourceService;
    //@RequestMapping("/test/add")
    public void test() {
        List<NewsSource> alist = new ArrayList<>();
//        导入excel数据到库中(解析excel)
        try {
//            获取Excel对象  解析97格式的excel
            XSSFWorkbook hssfWorkbook = new XSSFWorkbook(new FileInputStream("E://城乡建设局查.xls"));
//            获取表中的对象(按照索引读哪个表)
            XSSFSheet sheetAt = hssfWorkbook.getSheetAt(0);
//            获取表中的行
            for (Row row : sheetAt) {
//                第一行是标题  跳过
                if (row.getRowNum() == 0) {
                    continue;
                }
//                一格一格读数据
                String area = row.getCell(0).getStringCellValue();
                String source_web_site = row.getCell(1).getStringCellValue();
                String info = row.getCell(2).getStringCellValue();


//                封装数据到实体类中
                NewsSource newsSource = new NewsSource();
                newsSource.setArea(area);
                newsSource.setSourceWebSite(source_web_site );
                newsSource.setStatus(1);
                newsSource.setSourceInfo(info);
                newsSource.setUpdateTime(myFmt.format(new Date()));
                newsSource.setType(1);
                newsSource.setLevel(4);

                newsSourceService.insertNews(newsSource);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

@Autowired
    NewsSourceTMapper newsSourceTMapper;
/*    @RequestMapping("/test/del")
    @ResponseBody*/
    public String testdel() {
        int i = 0;
        List<NewsSource> alist = new ArrayList<>();
//        导入excel数据到库中(解析excel)
        try {
//            获取Excel对象  解析97格式的excel
            XSSFWorkbook hssfWorkbook = new XSSFWorkbook(new FileInputStream("C:\\Users\\mgc\\Desktop\\采集源修复进程\\已导入系统\\aa.xls"));
//            获取表中的对象(按照索引读哪个表)
            XSSFSheet sheetAt = hssfWorkbook.getSheetAt(0);
//            获取表中的行
            for (Row row : sheetAt) {
//                第一行是标题  跳过
                if (row.getRowNum() == 0) {
                    continue;
                }
                i++;
//                一格一格读数据
                String area = row.getCell(0).getStringCellValue();
                String source_web_site = row.getCell(1).getStringCellValue();
                String info = row.getCell(2).getStringCellValue();


        newsSourceTMapper.deletNews(area,source_web_site,info);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(i);
       return "ok";
    }
}
