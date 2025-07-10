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
