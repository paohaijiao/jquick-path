package com.github.paohaijiao.builder;

import com.github.paohaijiao.model.JSONPathResult;
import org.junit.Test;

import java.io.IOException;

public class JBuilder {
    @Test
    public void string() throws IOException {
        String json = "{ \"store\": { \"books\": [ " +
                "{ \"title\": \"Book 1\", \"price\": 10, \"author\": \"Author 1\" }, " +
                "{ \"title\": \"Book 2\", \"price\": 15, \"author\": \"Author 2\" }, " +
                "{ \"title\": \"Book 3\", \"price\": 20, \"author\": \"Author 1\" } " +
                "] } }";

        JSONPathResult result = JSONPathQueryBuilder.from(json, Object.class)
                .filter(
                        JSONPathQueryBuilder.filter(Object.class)
                                .eq("author", "Author 1")
                                .and()
                                .lt("price", 15)
                )
                .select(
                        JSONPathQueryBuilder.select(Object.class)
                                .field("title")
                                .field("price")
                )
                .sort(
                        JSONPathQueryBuilder.sort(Object.class)
                                .desc("price")
                )
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
}
