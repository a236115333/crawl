package com.etoak.crawl.util;

import java.util.List;

public class responeBody {
String error;
String extra;
listTree data;
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

    public listTree getData() {
        return data;
    }

    public void setData(listTree data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
