package com.maigechuang.entity;


import lombok.Data;

import java.util.Date;


public class SendMessage {
    private int SourceCount;
    private int ProvanceCount;
    private int InvalidCount;
    private boolean IsSend;
    private Date UpdatedTime;

    public int getSourceCount() {
        return SourceCount;
    }

    public void setSourceCount(int sourceCount) {
        SourceCount = sourceCount;
    }

    public int getProvanceCount() {
        return ProvanceCount;
    }

    public void setProvanceCount(int provanceCount) {
        ProvanceCount = provanceCount;
    }

    public int getInvalidCount() {
        return InvalidCount;
    }

    public void setInvalidCount(int invalidCount) {
        InvalidCount = invalidCount;
    }

    public boolean isSend() {
        return true;
    }

    public void setSend(boolean send) {
        IsSend = true;
    }

    public Date getUpdatedTime() {
        return UpdatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        UpdatedTime = updatedTime;
    }

    public int getAllCount() {
        return AllCount;
    }

    public void setAllCount(int allCount) {
        AllCount = allCount;
    }

    public int getValidCount() {
        return ValidCount;
    }

    public void setValidCount(int validCount) {
        ValidCount = validCount;
    }

    private int AllCount;
    private int ValidCount;


}
