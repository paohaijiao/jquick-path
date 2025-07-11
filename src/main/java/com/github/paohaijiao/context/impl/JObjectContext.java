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
package com.github.paohaijiao.context.impl;


import com.github.paohaijiao.context.JValueContext;
import com.github.paohaijiao.factory.JSONSerializerFactory;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.serializer.JSONSerializer;

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
