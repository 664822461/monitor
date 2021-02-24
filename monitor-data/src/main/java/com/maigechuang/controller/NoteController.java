package com.maigechuang.controller;

import com.maigechuang.entity.SendMessage;
import com.maigechuang.entity.bo.CaijiTotal;
import com.maigechuang.entity.bo.Evtotal;
import com.maigechuang.entity.response.PageResult;
import com.maigechuang.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

@Controller
public class NoteController {


    private  final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
@Autowired
    SendMessageService sendMessageService;

    @RequestMapping("/note/list")
    @ResponseBody
    public PageResult<SendMessage> getnoteList(int page, int limit){

        PageResult<SendMessage> pageResult = new PageResult();
        pageResult.setData(sendMessageService.getMessageList(page,limit));
        pageResult.setCode(0);
        pageResult.setCount(sendMessageService.getCount());



        return pageResult;
    }


    /**
     * 采集总数
     * @return
     */
    @RequestMapping("/note/main1")
    @ResponseBody
    public List<CaijiTotal> getmain1(){


        List<CaijiTotal> caijiTotals = new ArrayList<>();

        List<SendMessage> messageList = sendMessageService.getMessageList(1, 7);
            for(SendMessage sendMessage : messageList){
                CaijiTotal caijiTotal = new CaijiTotal();
                caijiTotal.setDate(df.format(sendMessage.getUpdatedTime()));
                caijiTotal.setTotal(sendMessage.getSourceCount());
                caijiTotals.add(caijiTotal);
            }

        return caijiTotals;
    }

    /**
     * 有效无效
     * @return
     */
    @RequestMapping("/note/main2")
    @ResponseBody
    public List<Evtotal> getmain2(){
        List<Evtotal> evtotalList = new ArrayList<>();

        List<SendMessage> messageList = sendMessageService.getMessageList(1, 7);
        for(SendMessage sendMessage : messageList){
            Evtotal evtotal = new Evtotal();
            evtotal.setDate(df.format(sendMessage.getUpdatedTime()));
            evtotal.setEffective(sendMessage.getValidCount());
            evtotal.setInvalid(sendMessage.getInvalidCount());
            evtotalList.add(evtotal);
        }

        return evtotalList;
    }
}
