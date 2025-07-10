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
package com.github.paohaijiao.value;

import com.github.paohaijiao.builder.JSONPathQueryBuilder;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.obj.Department;
import com.github.paohaijiao.obj.Employee;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * packageName com.github.paohaijiao.value
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/10
 */
public class JValueBuilder {

    @Test
    public void string() throws IOException {
        String json = "{ \"store\": { \"books\": [ " +
                "{ \"title\": \"Book 1\", \"price\": 10, \"author\": \"Author 1\" }, " +
                "{ \"title\": \"Book 2\", \"price\": 15, \"author\": \"Author 2\" }, " +
                "{ \"title\": \"Book 3\", \"price\": 20, \"author\": \"Author 1\" } " +
                "] } }";

        JSONPathResult result = JSONPathQueryBuilder.from(json).execute();
        System.out.println(result);
    }
    @Test
    public void map() throws IOException {
        Map<String, Object> addressMap = new HashMap<>();
        addressMap.put("street", "123 Main St");
        addressMap.put("city", "New York");
        addressMap.put("zip", "10001");
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("address", addressMap);
        JSONPathResult result = JSONPathQueryBuilder.from(userMap).execute();
        System.out.println(result);
    }
    @Test
    public void object() throws IOException {
        Employee manager = new Employee();
        manager.setEmployeeId("EMP1001");
        manager.setFullName("张伟");
        Department department = new Department();
        department.setDeptId("DEPT2001");
        department.setDeptName("研发部");
        department.setManager(manager);
        JSONPathResult result = JSONPathQueryBuilder.from(department).execute();
        System.out.println(result);
    }
    @Test
    public void jsonobject() throws IOException {
        JSONObject employeeJson = new JSONObject();
        employeeJson.put("employeeId", "EMP1001");
        employeeJson.put("fullName", "张伟");
        JSONObject departmentJson = new JSONObject();
        departmentJson.put("deptId", "DEPT2001");
        departmentJson.put("deptName", "研发部");
        departmentJson.put("manager", employeeJson);
        JSONPathResult result = JSONPathQueryBuilder.from(departmentJson).execute();
        System.out.println(result);
    }
}
