package com.maigechuang.entity;


import lombok.Data;

@Data
public class NewsSearchVO {

    private String searchArea;
    private String searchName;
    private String searchAddress;
    private int searchType;

}
