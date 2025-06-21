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
public class Jpath05Test {

    @Test
    public void test51() throws IOException {
        JSONObject b=new JSONObject();
        b.put("b","2");
        JSONObject a=new JSONObject();
        a.put("a",b);
        a.put("b",3);
        JSONPathExecutor executor = new JSONPathExecutor(a);
        executor.addErrorListener(error -> {});
        JSONPathResult jsonObject = executor.execute("$..b");
        System.out.println("结果: " + jsonObject.getRawData());
    }
    @Test
    public void test52() throws IOException {
        JSONObject b1=new JSONObject();
        b1.put("b","2");

        JSONObject b2=new JSONObject();
        b2.put("b","3");
        JSONObject a=new JSONObject();
        a.put("a", Arrays.asList(b1,b2));
        JSONObject d=new JSONObject();
        d.put("d","3");
        a.put("c", Arrays.asList(d));

        JSONPathExecutor executor = new JSONPathExecutor(a);
        executor.addErrorListener(error -> {});
        JSONPathResult jsonObject = executor.execute("$..[0]");
        System.out.println("结果: " + jsonObject.getRawData());
    }




}
