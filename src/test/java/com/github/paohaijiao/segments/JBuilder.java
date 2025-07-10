package com.github.paohaijiao.segments;

import com.github.paohaijiao.core.JSONPath;
import com.github.paohaijiao.function.JPredicate;
import com.github.paohaijiao.selector.subscript.JSubscripts;
import com.github.paohaijiao.util.JSegments;
import org.junit.Test;

import java.awt.print.Book;
import java.io.IOException;

public class JBuilder {
    @Test
    public void string() throws IOException {
        String jsonData="";
        JSONPath.of(jsonData)
                .segment(JSegments.id("store"))
                .segment(JSegments.id("books"))
                .execute();

        JSONPath.of(jsonData)
                .segment(JSegments.wildcard())
                .execute();
    }

    @Test
    public void object2() throws IOException {
        String jsonData="";
        JSONPath.of(jsonData)
                .segment(JSegments.id("books"))
                .segment(JSegments.subscript(JSubscripts.index(0)))
    .execute();

        JSONPath.of(jsonData)
                .segment(JSegments.id("books"))
                .segment(JSegments.subscript(
                        JSubscripts.filter(JPredicate.gt("price", 100))))
                .execute();
    }
    @Test
    public void object3() throws IOException {
        String jsonData="";
        JSONPath.of(jsonData)
                .segment(JSegments.recursiveId("name"))
                .execute();

        JSONPath.of(jsonData)
                .segment(JSegments.recursiveSubscript(JSubscripts.index(0)))
                .execute();
    }
    @Test
    public void object4() throws IOException {
        String jsonData="";
        JSONPath.of(jsonData)
                .segment(JSegments.id("store"))
                .segment(JSegments.recursiveId("books"))
                .segment(JSegments.subscript(
                        JSubscripts.filter(JPredicate.gt("price", 50))))
                .segment(JSegments.id("author"))
                .execute();
    }
}
