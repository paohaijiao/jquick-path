package com.github.paohaijiao.json;/*
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

import com.github.paohaijiao.executor.JSONPathExecutor;
import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.model.JSONPathResult;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * packageName PACKAGE_NAME
 *
 * @author Martin
 * @version 1.0.0
 * @className JpathTest
 * @date 2025/6/15
 * @description
 */
public class JDemoTest {
    @Test
    public void test11() throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("a", "1");
        JSONPathExecutor executor = new JSONPathExecutor(obj);
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$");
        System.out.println("结果: " + jsonObject.getRawData());
    }

    @Test
    public void test12() throws IOException {
        JSONObject b = new JSONObject();
        b.put("b", "2");
        JSONObject a = new JSONObject();
        a.put("a", b);
        JSONPathExecutor executor = new JSONPathExecutor(a);
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$.a.b");
        System.out.println("结果: " + jsonObject.getRawData());
    }

    @Test
    public void test13() throws IOException {
        JSONObject a = new JSONObject();
        a.put("my-property", 3);
        JSONPathExecutor executor = new JSONPathExecutor(a);
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$['my-property']");
        System.out.println("结果: " + jsonObject.getRawData());
    }
    @Test
    public void test21() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        JSONPathExecutor executor = new JSONPathExecutor(list);
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$[1]");
        System.out.println("结果: " + jsonObject.getRawData());
    }

    @Test
    public void test22() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        JSONPathExecutor executor = new JSONPathExecutor(list);
        executor.addErrorListener(error -> {});
        JSONPathResult jsonObject = executor.execute("$[-2]");
        System.out.println("结果: " + jsonObject.getRawData());
    }

    @Test
    public void test31() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        JSONPathExecutor executor = new JSONPathExecutor(list);
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$[*]");
        System.out.println("结果: " + jsonObject.getRawData());
    }
    @Test
    public void test41() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        JSONPathExecutor executor = new JSONPathExecutor(list);
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$[1:3]");
        System.out.println("结果: " + jsonObject.getRawData());
    }

    @Test
    public void test42() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        JSONPathExecutor executor = new JSONPathExecutor(list);
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$[:2]");
        System.out.println("结果: " + jsonObject.getRawData());
    }

    @Test
    public void test43() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        JSONPathExecutor executor = new JSONPathExecutor(list);
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$[1:]");
        System.out.println("结果: " + jsonObject.getRawData());
    }

    @Test
    public void test44() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        JSONPathExecutor executor = new JSONPathExecutor(list);
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$[::2]");
        System.out.println("结果: " + jsonObject.getRawData());
    }
    @Test
    public void test51() throws IOException {
        JSONObject b = new JSONObject();
        b.put("b", "2");
        JSONObject a = new JSONObject();
        a.put("a", b);
        a.put("b", 3);
        JSONPathExecutor executor = new JSONPathExecutor(a);
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$..b");
        System.out.println("结果: " + jsonObject.getRawData());
    }

    @Test
    public void test52() throws IOException {
        JSONObject b1 = new JSONObject();
        b1.put("b", "2");

        JSONObject b2 = new JSONObject();
        b2.put("b", "3");
        JSONObject a = new JSONObject();
        a.put("a", Arrays.asList(b1, b2));
        JSONObject d = new JSONObject();
        d.put("d", "3");
        a.put("c", Arrays.asList(d));

        JSONPathExecutor executor = new JSONPathExecutor(a);
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$..[0]");
        System.out.println("结果: " + jsonObject.getRawData());
    }
    @Test
    public void test53() throws IOException {
        JSONObject b1 = new JSONObject();
        b1.put("b", "2");

        JSONObject b2 = new JSONObject();
        b2.put("b", "3");
        JSONObject a = new JSONObject();
        a.put("a", Arrays.asList(b1, b2));
        JSONObject d = new JSONObject();
        d.put("d", "3");
        a.put("c", Arrays.asList(d));

        JSONPathExecutor executor = new JSONPathExecutor(a);
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$..b");
        System.out.println("结果: " + jsonObject.getRawData());
    }
    @Test
    public void test54() throws IOException {
        JSONObject b1 = new JSONObject();
        b1.put("b", "2");

        JSONObject b2 = new JSONObject();
        b2.put("b", "3");
        JSONObject a = new JSONObject();
        a.put("a", Arrays.asList(b1, b2));
        JSONObject d = new JSONObject();
        d.put("d", "3");
        a.put("c", Arrays.asList(d));

        JSONPathExecutor executor = new JSONPathExecutor(a);
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$..d");
        System.out.println("结果: " + jsonObject.getRawData());
    }
    @Test
    public void test61() throws IOException {
        JSONObject b = new JSONObject();
        b.put("price", 5);
        JSONObject a = new JSONObject();
        a.put("price", 15);
        JSONPathExecutor executor = new JSONPathExecutor(Arrays.asList(a, b));
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$[?(@.price > 10)]");
        System.out.println("结果: " + jsonObject.getRawData());
    }

    @Test
    public void test62() throws IOException {
        JSONObject b = new JSONObject();
        b.put("price", 5);
        b.put("inStock", true);
        JSONObject a = new JSONObject();
        a.put("price", 8);
        a.put("inStock", true);
        JSONPathExecutor executor = new JSONPathExecutor(Arrays.asList(a, b));
        executor.addErrorListener(error -> {
        });
        JSONPathResult jsonObject = executor.execute("$[?(@.price < 10 && @.inStock)]");
        System.out.println("结果: " + jsonObject.getRawData());
    }
    @Test
    public void test71() throws IOException {
        JSONArray a=JSONArray.parseJSONArray("[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"The Hobbit\",\n" +
                "    \"category\": \"fantasy\",\n" +
                "    \"price\": 12.99,\n" +
                "    \"author\": \"J.R.R. Tolkien\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"1984\",\n" +
                "    \"category\": \"fiction\",\n" +
                "    \"price\": 9.99,\n" +
                "    \"author\": \"George Orwell\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 3,\n" +
                "    \"title\": \"Learn JavaScript\",\n" +
                "    \"category\": \"education\",\n" +
                "    \"price\": 19.99,\n" +
                "    \"author\": \"John Doe\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 4,\n" +
                "    \"title\": \"Harry Potter\",\n" +
                "    \"category\": \"fantasy\",\n" +
                "    \"price\": 7.99,\n" +
                "    \"author\": \"J.K. Rowling\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 5,\n" +
                "    \"title\": \"The Great Gatsby\",\n" +
                "    \"category\": \"fiction\",\n" +
                "    \"price\": 4.99,\n" +
                "    \"author\": \"F. Scott Fitzgerald\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 6,\n" +
                "    \"title\": \"Game of Thrones\",\n" +
                "    \"category\": \"fantasy\",\n" +
                "    \"price\": 15.99,\n" +
                "    \"author\": \"George R.R. Martin\"\n" +
                "  }\n" +
                "]");
        JSONPathExecutor executor = new JSONPathExecutor(a);
        executor.addErrorListener(error -> {});
        JSONPathResult jsonObject = executor.execute("$[?(@.price > 5 && (@.category == 'fiction' || @.category == 'fantasy'))]");
        System.out.println("结果: " + jsonObject.getRawData());
    }

    @Test
    public void test72() throws IOException {
        JSONArray a=JSONArray.parseJSONArray("[\"a\", \"bb\", \"ccc\", \"dddd\"]，返回 [\"dddd\"]");
        JSONPathExecutor executor = new JSONPathExecutor(a);
        executor.addErrorListener(error -> {});
        JSONPathResult jsonObject = executor.execute("$[?(@.length() >= 3)]");
        System.out.println("结果: " + jsonObject.getRawData());
    }
    @Test
    public void test73() throws IOException {
        JSONArray a=JSONArray.parseJSONArray("[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Wireless Earbuds\",\n" +
                "    \"price\": 59.99,\n" +
                "    \"tags\": [\"electronics\", \"audio\", \"sale\", \"new\"]\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Leather Wallet\",\n" +
                "    \"price\": 29.99,\n" +
                "    \"tags\": [\"accessories\", \"leather\"]\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 3,\n" +
                "    \"name\": \"Smart Watch\",\n" +
                "    \"price\": 199.99,\n" +
                "    \"tags\": [\"electronics\", \"wearable\", \"sale\"]\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 4,\n" +
                "    \"name\": \"Coffee Mug\",\n" +
                "    \"price\": 12.99,\n" +
                "    \"tags\": [\"kitchen\", \"home\"]\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 5,\n" +
                "    \"name\": \"Yoga Mat\",\n" +
                "    \"price\": 24.99,\n" +
                "    \"tags\": [\"fitness\", \"sale\", \"clearance\"]\n" +
                "  }\n" +
                "]");
        JSONPathExecutor executor = new JSONPathExecutor(a);
        executor.addErrorListener(error -> {});
        JSONPathResult jsonObject = executor.execute("$[?(@.tags.length()==2)]");
        System.out.println("结果: " + jsonObject.getRawData());
    }
}
