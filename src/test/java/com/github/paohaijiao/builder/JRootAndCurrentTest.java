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
package com.github.paohaijiao.builder;

import com.github.paohaijiao.enums.JRoot;
import com.github.paohaijiao.factory.JSONSerializerFactory;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.selector.root.JPath;
import com.github.paohaijiao.serializer.JSONSerializer;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class JRootAndCurrentTest {

    public static JSONObject getData() {
        String json = "{ \"store\": { \"books\": [ " +
                "{ \"title\": \"Book 1\", \"price\": 10, \"author\": \"Author 1\" }, " +
                "{ \"title\": \"Book 2\", \"price\": 15, \"author\": \"Author 2\" }, " +
                "{ \"title\": \"Book 3\", \"price\": 20, \"author\": \"Author 1\" } " +
                "] } }";
        JSONSerializer jsonSerializer= JSONSerializerFactory.getDefaultSerializer();
        JSONObject jsonObject= jsonSerializer.deserialize(json, JSONObject.class);
        return  jsonObject;
    }


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
        System.out.println(store.toString());
        return  store;
    }
    @Test
    public void string() throws IOException {
        JSONObject jsonObject = getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonObject)
                .document(JPath.fromRoot(JRoot.ROOT).property("store").property("books"))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }

    @Test
    public void object2() throws IOException {
        JSONObject jsonObject = getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonObject)
                .document(JPath.fromRoot(JRoot.CURRENT).property("store").property("books"))
                .limit(10)
                .execute();
        System.out.println(result.getRawData());
    }
}
