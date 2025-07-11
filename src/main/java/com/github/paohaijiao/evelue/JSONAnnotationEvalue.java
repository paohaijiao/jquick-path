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
package com.github.paohaijiao.evelue;

import com.github.paohaijiao.anno.JSONPath;
import com.github.paohaijiao.context.JValueContext;
import com.github.paohaijiao.model.JSONPathResult;

import java.lang.reflect.Field;

public class JSONAnnotationEvalue {

    private final JValueContext context;

    public JSONAnnotationEvalue(JValueContext context) {
        this.context = context;
    }

    public <T> T process(Object source, Class<T> targetClass) {
        try {
            T instance = targetClass.newInstance();
            for (Field field : targetClass.getDeclaredFields()) {
                processField(source, instance, field);
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to process JSON annotations", e);
        }
    }

    private <T> void processField(Object source, T instance, Field field) throws Exception {
        if (field.isAnnotationPresent(JSONPath.class)) {
            JSONPath jsonPath = field.getAnnotation(JSONPath.class);
            Object value = extractValue(source, jsonPath, field.getType());
            field.setAccessible(true);
            field.set(instance, value);
        }
    }

    private Object extractValue(Object source, JSONPath jsonPath, Class<?> targetType) {
        JSONPathResult result = context.executeQuery(jsonPath.value());
        if (result.getRawData() == null && jsonPath.required()) {
            throw new IllegalArgumentException("Required path not found: " + jsonPath.value());
        }
        Object value = result.getRawData() != null ?
                result.getRawData() :
                parseDefaultValue(jsonPath.defaultValue(), targetType);
        return value;
    }

    private Object parseDefaultValue(String defaultValue, Class<?> targetType) {
        if (defaultValue.isEmpty()) return null;
        if (targetType == String.class) return defaultValue;
        if (targetType == Integer.class || targetType == int.class)
            return Integer.parseInt(defaultValue);

        return defaultValue;
    }
}
