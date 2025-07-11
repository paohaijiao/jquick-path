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
package com.github.paohaijiao.sort;

import com.github.paohaijiao.builder.JSONPathQueryBuilder;
import com.github.paohaijiao.enums.JRoot;
import com.github.paohaijiao.model.JBookModel;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.model.Storre;
import com.github.paohaijiao.query.impl.JSortBuilder;
import com.github.paohaijiao.selector.root.JPath;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class JSortTest {

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
    public void asc() throws IOException {
        JSONObject jsonData = getData1();
        JSONPathResult result = JSONPathQueryBuilder.<JBookModel>from(jsonData)  // 明确指定泛型类型
                .document(JPath.fromRoot(JRoot.ROOT).property("books"))
                .sort(new JSortBuilder<JBookModel>().asc("price"))
                .asList(JBookModel.class)
                .limit(10)
                .execute();

        System.out.println(result);
    }
    @Test
    public void desc() throws IOException {
        JSONObject jsonData = getData1();
        JSONPathResult result = JSONPathQueryBuilder.<JBookModel>from(jsonData)  // 明确指定泛型类型
                .document(JPath.fromRoot(JRoot.ROOT).property("books"))
                .sort(new JSortBuilder<JBookModel>().desc("price"))
                .asList(JBookModel.class)
                .limit(10)
                .execute();

        System.out.println(result);
    }
    @Test
    public void clazz() throws IOException {
        JSONObject jsonData = getData1();
        JSONPathResult result = JSONPathQueryBuilder.<Storre>from(jsonData)  // 明确指定泛型类型
                .document(JPath.fromRoot(JRoot.ROOT).property("books"))
                .as(Storre.class)
                .limit(10)
                .execute();

        System.out.println(result);
    }




}
