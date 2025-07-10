package com.github.paohaijiao.ext;

import com.github.paohaijiao.ext.template.JTemplateEngine;
import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class JSonSupportTest
{
    @Test
    public void rightDotExpr1() throws IOException {
        // 示例数据
        JSONObject data = new JSONObject();
        JSONObject map=new JSONObject();
        map.put("name", "Alice");
        map.put("age", 25);
        JSONObject map1=new JSONObject();
        map1.put("name", "Bob");
        map1.put("age", 30);
        data.put("users", Arrays.asList(map,map1));
        JTemplateEngine engine = new JTemplateEngine(data);
        JSONArray users = engine.evaluateAsArray("${users}");
        JSONArray singleUser = engine.evaluateAsArray("${users[0]}");
        JSONArray ages = engine.evaluateAsArray("${users[0].age}");
        JSONArray empty = engine.evaluateAsArray("${non.existent.path}");
    }
}
