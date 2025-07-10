package com.github.paohaijiao.builder;

import com.github.paohaijiao.core.JPath;
import com.github.paohaijiao.core.JSONPath;
import com.github.paohaijiao.function.JPredicate;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.selector.filterExpression.JFilterExpression;
import org.junit.Test;

import java.awt.print.Book;
import java.io.IOException;
import java.util.List;

public class JBuilder {
    @Test
    public void string() throws IOException {
        String json = "{ \"store\": { \"books\": [ " +
                "{ \"title\": \"Book 1\", \"price\": 10, \"author\": \"Author 1\" }, " +
                "{ \"title\": \"Book 2\", \"price\": 15, \"author\": \"Author 2\" }, " +
                "{ \"title\": \"Book 3\", \"price\": 20, \"author\": \"Author 1\" } " +
                "] } }";

        JSONPathResult result = JSONPathQueryBuilder.from(json)
                .currentRoot()
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
