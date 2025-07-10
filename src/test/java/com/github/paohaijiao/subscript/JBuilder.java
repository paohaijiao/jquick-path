package com.github.paohaijiao.subscript;

import com.github.paohaijiao.builder.JSONPathQueryBuilder;
import com.github.paohaijiao.core.JPath;
import com.github.paohaijiao.core.JSONPath;
import com.github.paohaijiao.function.JPredicate;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.selector.subscript.JSubscripts;
import org.junit.Test;

import java.awt.print.Book;
import java.io.IOException;
import java.util.List;

public class JBuilder {
    @Test
    public void string() throws IOException {
        String json="";
        JSONPath.of(json)
                .path("books")
                .at(JSubscripts.index(0))
                .execute();
        JSONPath.of(json)
                .path("store")
                .at(JSubscripts.property("books"))
                .execute();

        JSONPath.of(json)
                .path("books")
                .at(JSubscripts.wildcard())
                .execute();
    }

    @Test
    public void object2() throws IOException {
        String jsonData="";
        JSONPath.of(jsonData)
                .path("books")
                .at(JSubscripts.slice(1, 5, 2))
                .execute();

        JSONPath.of(jsonData)
                .path("books")
                .at(JSubscripts.slice(-3, null))
                .execute();
    }
    @Test
    public void object3() throws IOException {
        String jsonData="";
        JPredicate<Book> expensive = JPredicate.gt("price", 100);
        JSONPath.of(jsonData)
                .path("books")
                .at(JSubscripts.filter(expensive))
                .execute();

        JPredicate<?> complexFilter = JPredicate.gt("price", 50)
                .and(JPredicate.lt("price", 100))
                .and(JPredicate.eq("category", "fiction"));

        JSONPath.of(jsonData)
                .path("books")
                .at(JSubscripts.filter(complexFilter))
                .execute();
    }
    @Test
    public void object4() throws IOException {
        String jsonData="";

        JSONPath.of(jsonData)
                .path("store")
                .at(
                        JSubscripts.property("books"),
                        JSubscripts.filter(JPredicate.eq("category", "sci-fi")),
                        JSubscripts.slice(2, 5),
                        JSubscripts.slice(0, null, 2)
                )
                .execute();
    }
}
