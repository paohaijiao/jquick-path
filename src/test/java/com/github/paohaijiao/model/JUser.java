package com.github.paohaijiao.model;

import com.github.paohaijiao.anno.JSONObject;
import com.github.paohaijiao.anno.JSONPath;

@JSONObject
public class JUser {

    @JSONPath("$.name")
    private String username;

    @JSONPath("$.age")
    private int userAge;

    @JSONPath("$.address.city")
    private String city;
}
