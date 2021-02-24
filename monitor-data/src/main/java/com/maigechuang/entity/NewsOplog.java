package com.maigechuang.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data


public class NewsOplog {


    private int id;
    private String userIp;
    private String  opTime;
    private String text;


}
