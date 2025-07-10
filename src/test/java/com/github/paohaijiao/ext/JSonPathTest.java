package com.github.paohaijiao.ext;

import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;

import org.junit.Test;

import java.io.IOException;

public class JSonPathTest {

    @Test
    public void rightDotExpr1() throws IOException {
        JSONObject data = new JSONObject();
        JSONArray users = new JSONArray();
        JSONObject map1=new JSONObject();
        map1.put("name", "Alice");
        map1.put("age", 25);
        map1.put("email", "alice@example.com");
        users.add(map1);
        JSONObject map2=new JSONObject();
        map2.put("name", "Bob");
        map2.put("age", 30);
        map2.put("email", "bob@example.com");
        users.add(map2);
        JSONObject map3=new JSONObject();
        map3.put("name", "Charlie");
        map3.put("age", 20);
        map3.put("email", "charlie@example.com");
        users.add(map3);
        data.put("users", users);

        JSONArray result = new JQueryBuilder()
                .path("users")
                .filter(JFilterBuilder.and(
                        JFilterBuilder.gt("age", 20),
                        JFilterBuilder.regex("email", ".+@example\\.com")
                ))
                .project(JProjection.builder()
                        .include("name", "email")
                        .alias("email", "contactEmail")
                        .build())
                .sort(JSorting.builder()
                        .by("age", false)
                        .build())
                .paginate(0, 10)
                .execute(data);

        System.out.println(result.toString());
    }
}
