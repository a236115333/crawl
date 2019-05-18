package com.etoak.crawl.util;

public class ContentBody {
    String error;
    String extra;
    listBody data;
    int code;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public listBody getData() {
        return data;
    }

    public void setData(listBody data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
