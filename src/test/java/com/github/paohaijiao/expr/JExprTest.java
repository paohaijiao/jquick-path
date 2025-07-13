/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.expr;

import com.github.paohaijiao.builder.JSONPathQueryBuilder;
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

public class JExprTest {

    public static JSONObject getData1() {
        JSONObject book1=new JSONObject();
        book1.put("title", "Book 1");
        book1.put("author", "Author 1");
        book1.put("price", 10);
        book1.put("isbn", true);

        JSONObject book2=new JSONObject();
        book2.put("title", "Book 2");
        book2.put("author", "Author 2");
        book2.put("price", 15);
        book2.put("isbn", false);

        JSONObject book3=new JSONObject();
        book3.put("title", "Book 3");
        book3.put("author", "Author 3");
        book3.put("price", 20);
        book3.put("isbn", true);

        JSONObject books = new JSONObject();
        books.put("books", Arrays.asList(book1, book2, book3));
        JSONObject extract=new JSONObject();
        extract.put("title", "Book 3");
        extract.put("author", "Author 3");
        extract.put("price", 20);

        books.put("extract", extract);
        System.out.println(books);
        return  books;
    }
    @Test
    public void negationExpression() throws IOException {
        //0*1的索引使0
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("-2"))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void bracketExpression() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("2"))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void additiveExpression() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("1+1"))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void additiveExpression1() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("1-1"))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void dotExpr() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("(@.length())-1"))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void notExpression() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("!@.isbn")))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void multiplicativeExpression() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("1*1"))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void multiplicativeExpression1() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("1/1"))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void multiplicativeExpression2() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("1%1"))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void comparisonExpression() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price>15")))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void comparisonExpression1() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price>=15")))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void comparisonExpression2() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price<15")))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void comparisonExpression3() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price<=15")))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void equalityExpression() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price==15")))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void equalityExpression1() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price!=15")))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void inExpression() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2')")))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void logicalAndExpression() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2') &&@.isbn")))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void logicalOrExpression() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2') ||@.isbn")))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }


}
