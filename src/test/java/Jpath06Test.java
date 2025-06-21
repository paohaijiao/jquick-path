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
import com.paohaijiao.javelin.model.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * packageName PACKAGE_NAME
 *
 * @author Martin
 * @version 1.0.0
 * @className JpathTest
 * @date 2025/6/15
 * @description
 */
public class Jpath06Test {

    @Test
    public void test61() throws IOException {
        JSONObject b=new JSONObject();
        b.put("price",5);
        JSONObject a=new JSONObject();
        a.put("price",15);
        JSONPathExecutor executor = new JSONPathExecutor(Arrays.asList(a,b));
        executor.addErrorListener(error -> {});
        JSONPathResult jsonObject = executor.execute("$[?(@.price > 10)]");
        System.out.println("结果: " + jsonObject.getRawData());
    }
    @Test
    public void test62() throws IOException {
        JSONObject b=new JSONObject();
        b.put("price",5);
        b.put("inStock",true);
        JSONObject a=new JSONObject();
        a.put("price",8);
        a.put("inStock",true);
        JSONPathExecutor executor = new JSONPathExecutor(Arrays.asList(a,b));
        executor.addErrorListener(error -> {});
        JSONPathResult jsonObject = executor.execute("$[?(@.price < 10 && @.inStock)]");
        System.out.println("结果: " + jsonObject.getRawData());
    }




}
