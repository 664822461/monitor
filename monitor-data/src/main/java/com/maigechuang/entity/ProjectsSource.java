package com.maigechuang.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectsSource {

    private int Id;
    private String Name;
    private String Area;
    private String Trade;
    private Date PublishTime;
    private Date CreatedOn;
    private String ContentText;
    private String OrgUrl;
    private String Remark;
    private int Flag;
    private String Department;
    private String Territory;
    private String CustomerType;
    private String PurchaseType;
    private double PurchaseMoney;
    private String ProfessionType;
    private double InvestmentMoney;
    private double SuperintendenceCost;
    private String Qualification;
    private Date ModifyOn;
    private boolean IsDeleted;
    private String CreatedBy;
    private String ModifiedBy;
    private int Sort;
    private String TenderNo;
    private Date TenderOpenTime;
    private String TenderPers;
    private String CapitalSource;
    private String TenderAgency;
    private String TenderType;
    private String OwnerCompany;
    private Date ShuntTime;
    private int MatchingId;
}
