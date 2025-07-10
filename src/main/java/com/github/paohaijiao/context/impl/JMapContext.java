package com.github.paohaijiao.context.impl;

import com.github.paohaijiao.context.JValueContext;
import com.github.paohaijiao.model.JSONPathResult;

import java.util.Map;

public class JMapContext<T> extends JValueContext<T>  {

    public JMapContext(Map<String, ?> map, Class<T> targetType) {
        super(map, targetType);
    }

    @Override
    public <R> JValueContext<R> as(Class<R> targetType) {
        return new JMapContext<R>((Map<String, ?>) this.jsonData, targetType);
    }

    @Override
    public T query(String jsonPath) {
        JSONPathResult result = executeQuery(jsonPath);
        return convertResult(result.getRawData(), targetType);
    }

    @Override
    protected Object normalizeInput(Object input) {
        if (!(input instanceof Map)) {
            throw new IllegalArgumentException("JMapContext only accepts Map input");
        }
        return input;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <R> R convertResult(Object result, Class<R> targetType) {
        if (targetType.isInstance(result)) {
            return (R) result;
        }

        if (result instanceof Map && !Map.class.isAssignableFrom(targetType)) {
            return convertDefault(result, targetType);
        }

        return convertDefault(result, targetType);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <R> R convertDefault(Object result, Class<R> targetType) {
        if (targetType.isInstance(result)) {
            return (R) result;
        }
        throw new IllegalArgumentException("Cannot convert Map result to " + targetType.getName());
    }
}
