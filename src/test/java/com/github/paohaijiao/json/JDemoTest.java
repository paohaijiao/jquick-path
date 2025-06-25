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
        executor.addErrorListener(error -> {
        });
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
}
