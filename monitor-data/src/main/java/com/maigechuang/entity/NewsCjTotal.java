package com.maigechuang.entity;

import lombok.Data;

import java.util.Date;

@Data
public class NewsCjTotal {

    private int id;
    private int tid;
    private int total;
    private Date cjtime;
    private int isrest;
}
