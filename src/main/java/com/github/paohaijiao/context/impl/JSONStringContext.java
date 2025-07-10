package com.github.paohaijiao.context.impl;

import com.github.paohaijiao.context.JValueContext;
import com.github.paohaijiao.factory.JSONSerializerFactory;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.serializer.JSONSerializer;

public class JSONStringContext<T> extends JValueContext<T> {

    private final JSONSerializer serializer;

    public JSONStringContext(String json, Class<T> targetType) {
        super(json, targetType);
        this.serializer = JSONSerializerFactory.createJQuickSerializer();
    }

    public JSONStringContext(String json, Class<T> targetType, JSONSerializer serializer) {
        super(json, targetType);
        this.serializer = serializer;
    }

    @Override
    public <R> JValueContext<R> as(Class<R> targetType) {
        return new JSONStringContext<R>((String) this.jsonData, targetType, this.serializer);
    }

    @Override
    public T query(String jsonPath) {
        JSONPathResult result = executeQuery(jsonPath);
        return convertResult(result.getRawData(), targetType);
    }

    @Override
    protected Object normalizeInput(Object input) {
        if (!(input instanceof String)) {
            throw new IllegalArgumentException("JSONStringContext only accepts String input");
        }

        return serializer.deserialize((String) input, Object.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <R> R convertResult(Object result, Class<R> targetType) {
        if (targetType.isInstance(result)) {
            return (R) result;
        }
        if (result instanceof String && isPrimitiveOrWrapper(targetType)) {
            return deserializePrimitive((String) result, targetType);
        }
        if (result instanceof String) {
            return serializer.deserialize((String) result, targetType);
        }
        return convertDefault(result, targetType);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <R> R convertDefault(Object result, Class<R> targetType) {
        try {
            // 使用 JQuickJSONSerializer 的序列化/反序列化组合进行转换
            String json = serializer.serialize(result);
            return serializer.deserialize(json, targetType);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot convert result to " + targetType.getName(), e);
        }
    }

    @SuppressWarnings("unchecked")
    private <R> R deserializePrimitive(String value, Class<R> targetType) {
        if (targetType == String.class) {
            return (R) value;
        }
        if (targetType == Integer.class || targetType == int.class) {
            return (R) Integer.valueOf(value);
        }
        if (targetType == Long.class || targetType == long.class) {
            return (R) Long.valueOf(value);
        }
        if (targetType == Double.class || targetType == double.class) {
            return (R) Double.valueOf(value);
        }
        if (targetType == Float.class || targetType == float.class) {
            return (R) Float.valueOf(value);
        }
        if (targetType == Boolean.class || targetType == boolean.class) {
            return (R) Boolean.valueOf(value);
        }
        throw new IllegalArgumentException("Unsupported primitive type: " + targetType.getName());
    }

    private boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive() ||
                type == String.class ||
                type == Integer.class ||
                type == Long.class ||
                type == Double.class ||
                type == Float.class ||
                type == Boolean.class;
    }
}
