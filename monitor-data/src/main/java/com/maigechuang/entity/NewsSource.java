package com.maigechuang.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Created by 66482 on 2020/6/1.
 */

@Data

public class NewsSource {

    private int id;
    private String sourceWebSite;
    private String sourceInfo;
    private String area;
    private String updateTime;
    private int status;
    private int type;
    private int level;



}
