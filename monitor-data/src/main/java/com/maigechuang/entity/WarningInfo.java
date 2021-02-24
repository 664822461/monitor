package com.maigechuang.entity;

import lombok.Data;

/**
 * Created by mgc on 2020/11/26.
 */

@Data
public class WarningInfo {

    private int id;
    private int tid;
    private int level;
    private String createdTime;
    private String updateTime;
    private int status;
    private int isHandle;
    private int waitDay;
    private String tempTime;
    private String beizhu;

}
