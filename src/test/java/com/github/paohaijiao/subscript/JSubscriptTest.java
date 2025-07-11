package com.github.paohaijiao.subscript;

import com.github.paohaijiao.builder.JSONPathQueryBuilder;
import com.github.paohaijiao.core.JSONPath;
import com.github.paohaijiao.enums.JRoot;
import com.github.paohaijiao.function.JPredicate;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.selector.root.JPath;
import com.github.paohaijiao.selector.segment.JSubscriptSegment;
import com.github.paohaijiao.selector.subscript.JSubscripts;
import org.junit.Test;
import java.io.IOException;
import java.util.Arrays;

public class JSubscriptTest {

    public static JSONObject getData1() {
        JSONObject book1=new JSONObject();
        book1.put("title", "Book 1");
        book1.put("author", "Author 1");
        book1.put("price", 10);

        JSONObject book2=new JSONObject();
        book2.put("title", "Book 2");
        book2.put("author", "Author 2");
        book2.put("price", 15);

        JSONObject book3=new JSONObject();
        book3.put("title", "Book 3");
        book3.put("author", "Author 3");
        book3.put("price", 20);

        JSONObject books = new JSONObject();
        books.put("books", Arrays.asList(book1, book2, book3));
        JSONObject extract=new JSONObject();
        extract.put("title", "Book 3");
        extract.put("author", "Author 3");
        extract.put("price", 20);
        books.put("extract", extract);
        return  books;
    }
    @Test
    public void slice() throws IOException {
        JSONObject jsonData=getData1();
        ;
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books").segment(JSubscriptSegment.of(JSubscripts.slice(0,1,2))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());

    }

    @Test
    public void number() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books").segment(JSubscriptSegment.of(JSubscripts.index(0))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void wildcard() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books").segment(JSubscriptSegment.of(JSubscripts.wildcard())))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void stringLiteral() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("extract")
                        .segment(JSubscriptSegment.of(JSubscripts.property("title"))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void filterExpression() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                 .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.eq("title", "Book 1")))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void expr() throws IOException {
        //0*1的索引使0
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("0*1"))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }

}
