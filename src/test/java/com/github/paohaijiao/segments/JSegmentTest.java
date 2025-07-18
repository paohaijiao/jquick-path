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
package com.github.paohaijiao.segments;

import com.github.paohaijiao.builder.JSONPathQueryBuilder;
import com.github.paohaijiao.enums.JRoot;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.selector.root.JPath;
import com.github.paohaijiao.selector.subscript.JIndexSubscript;
import com.github.paohaijiao.util.JSegments;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class JSegmentTest {
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

        JSONObject store = new JSONObject();
        store.put("store",books);
        System.out.println(store);
        return  store;
    }
    @Test
    public void identifierSegment() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("store"))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void identifierSegmentStar() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("*"))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void conditionIdentifierSegmentStar() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                .property("store").property("books").property("*"))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }

    @Test
    public void subscriptSegment() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                        .property("store").property("books")
                        .segment(JSegments.subscript(JIndexSubscript.of(2))))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void object3() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                        .property("store").property("books")
                        .segment(JSegments.subscript(JIndexSubscript.of(2))).property("price"))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void childIdentifierSegment() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                        .property("store").property("books")
                        .segment(JSegments.recursiveId("price")))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void childSubscriptSegment() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                        .property("store").property("books")
                        .segment(JSegments.recursiveSubscript(JIndexSubscript.of(2)))
                ).limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
    @Test
    public void path() throws IOException {
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData).path("$.store.books..[2]").limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
}
