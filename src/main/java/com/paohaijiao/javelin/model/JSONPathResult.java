package com.paohaijiao.javelin.model;

import java.util.Collections;
import java.util.List;

public class JSONPathResult {
    private Object data;
    private boolean isList;

    public JSONPathResult(Object data) {

        if (data instanceof List) {
            this.data = data;
            this.isList = true;
        } else {
            this.data = data;
            this.isList = false;
        }
    }
    public List<Object> getAsList() {
        return isList ? (List<Object>)data : Collections.singletonList(data);
    }

    public Object getRawData() {
        return data;
    }

    public boolean isList() {
        return isList;
    }
}
