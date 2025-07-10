package com.github.paohaijiao.builder;

import com.github.paohaijiao.enums.JRoot;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.selector.root.JPath;
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

        JSONPathResult result = JSONPathQueryBuilder.from(json)
                .document(JPath.fromRoot(JRoot.ROOT).property("store").property("books"))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }

//    @Test
//    public void object2() throws IOException {
//        String json="";
//        List<Book> books = JSONPath.of(json)
//                .path("store.books")
//                .filter(JPath.filter(Book.class)
//                        .eq("category", "fiction")
//                        .and()
//                        .gt("price", 10))
//                .select(JPath.select(Book.class)
//                        .field("title")
//                        .field("author"))
//                .sort(JPath.sort(Book.class)
//                        .asc("price")
//                        .desc("publishDate"))
//                .limit(10)
//                .skip(20)
//                .execute()
//                //.toList(Book.class);
//    }
}
