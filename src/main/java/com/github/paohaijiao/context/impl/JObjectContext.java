package com.github.paohaijiao.context.impl;


import com.github.paohaijiao.context.JValueContext;
import com.github.paohaijiao.factory.JSONSerializerFactory;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.serializer.JSONSerializer;

import java.util.Map;

public class  JObjectContext<T> extends JValueContext<T>  {

    public JObjectContext(Object object, Class<T> targetType) {
        super(object, targetType);
    }

    @Override
    public <R> JValueContext<R> as(Class<R> targetType) {
        return new JObjectContext<R>(this.jsonData, targetType);
    }

    @Override
    public T query(String jsonPath) {
        JSONPathResult result = executeQuery(jsonPath);
        return convertResult(result.getRawData(), targetType);
    }

    @Override
    protected Object normalizeInput(Object input) {
        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
        String string =serializer.serialize(input);
        return serializer.deserialize(string, targetType);
    }

    @Override
    protected <R> R convertResult(Object result, Class<R> targetType) {
        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
        String string =serializer.serialize(result);
        return serializer.deserialize(string, targetType);
    }

    @Override
    protected <R> R convertDefault(Object result, Class<R> targetType) {
        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
        String string =serializer.serialize(result);
        return serializer.deserialize(string, targetType);
    }
}
