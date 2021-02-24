package com.maigechuang.entity.response;

public class CURDResult {
    int success = 1;
    String msg = "";

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
