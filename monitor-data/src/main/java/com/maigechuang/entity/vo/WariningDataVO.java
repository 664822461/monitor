package com.maigechuang.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by mgc on 2020/11/27.
 */

@Data
public class WariningDataVO {

    private int id;
    private String area;
    private String SourceWebSite;
    private String SourceInfo;
    private int level;
    private Date CreatedTime;
    private int isHandle;
    private int waitDay;
    private String beizhu;



}
