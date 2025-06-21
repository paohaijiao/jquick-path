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

import com.paohaijiao.javelin.model.JSONPathResult;
import com.paohaijiao.javelin.executor.JSONPathExecutor;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
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
public class Jpath04Test {

    @Test
    public void test41() throws IOException {
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        JSONPathExecutor executor = new JSONPathExecutor(list);
        executor.addErrorListener(error -> {});
        JSONPathResult jsonObject = executor.execute("$[1:3]");
        System.out.println("结果: " + jsonObject.getRawData());
    }

    @Test
    public void test42() throws IOException {
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        JSONPathExecutor executor = new JSONPathExecutor(list);
        executor.addErrorListener(error -> {});
        JSONPathResult jsonObject = executor.execute("$[:2]");
        System.out.println("结果: " + jsonObject.getRawData());
    }
    @Test
    public void test43() throws IOException {
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        JSONPathExecutor executor = new JSONPathExecutor(list);
        executor.addErrorListener(error -> {});
        JSONPathResult jsonObject = executor.execute("$[1:]");
        System.out.println("结果: " + jsonObject.getRawData());
    }
    @Test
    public void test44() throws IOException {
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        JSONPathExecutor executor = new JSONPathExecutor(list);
        executor.addErrorListener(error -> {});
        JSONPathResult jsonObject = executor.execute("$[::2]");
        System.out.println("结果: " + jsonObject.getRawData());
    }



}
