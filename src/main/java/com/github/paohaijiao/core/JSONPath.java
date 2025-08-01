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
package com.github.paohaijiao.core;

import com.github.paohaijiao.console.JConsole;
import com.github.paohaijiao.enums.JLogLevel;
import com.github.paohaijiao.executor.JSONPathExecutor;
import com.github.paohaijiao.factory.JSONSerializerFactory;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.model.JSortConditionModel;
import com.github.paohaijiao.query.JSONPathQuery;
import com.github.paohaijiao.query.impl.JSortBuilder;
import com.github.paohaijiao.selector.root.JPath;
import com.github.paohaijiao.serializer.JSONSerializer;
import com.github.paohaijiao.support.JSONArraySorter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JSONPath<T> implements JSONPathQuery<T> {

    JConsole console = new JConsole();

    private final JSONObject jsonObject;

    private  Class<T> type;

    private  boolean isList;

    private JPath path;

    private String pathString;

    private List<JSortConditionModel<T, ?>> sort=new ArrayList<>();


    private Integer limit;

    private Integer skip;

    public JSONPath(String jsonData) {
        JSONSerializer serializer=JSONSerializerFactory.getDefaultSerializer();
        this.jsonObject = serializer.deserialize(jsonData,JSONObject.class);
    }
    public JSONPath(Map map) {
        this.jsonObject = new JSONObject(map);
    }
    public JSONPath(Object object) {
        JSONSerializer serializer=JSONSerializerFactory.getDefaultSerializer();
        String jsonString= serializer.serialize(object);
        this.jsonObject = serializer.deserialize(jsonString,JSONObject.class);
    }
    private JSONPath(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }




    @Override
    public JSONPathQuery<T> document(JPath path) {
        this.path=path;
        return this;
    }

    @Override
    public JSONPathQuery<T> path(String pathString) {
        this.pathString=pathString;
        return this;
    }

    @Override
    public JSONPathResult execute() {
        String fullPath = "";
        if(StringUtils.isNotEmpty(pathString)){
                fullPath=pathString;
        }else{
            if(null!=this.path){
                fullPath=fullPath+this.path.toJSONPathExpression();
            }else{
                fullPath="$";
            }
        }

        JSONPathExecutor executor = new JSONPathExecutor(jsonObject);
        executor.addErrorListener(error -> {
            String errorMessage =String.format("error: line %d:%d - %s%n",error.getLine(), error.getCharPosition(), error.getMessage());
            console.error(errorMessage);
            console.error(error.getMessage());
        });
        console.log(JLogLevel.INFO,"the exec content is :"+fullPath.toString());
        JSONPathResult result = executor.execute(fullPath.toString());
        if(this.isList){//only list support
            if (sort != null && !sort.isEmpty()) {
                sortResult(result,this.type);
            }
        }

        if (skip != null && limit != null) {
            paginateResult(result);
        }

        return result;
    }

    @Override
    public JSONPathQuery<T> sort(JSortBuilder<T> sortBuilder) {
        this.sort = sortBuilder.build();
        return this;
    }

    @Override
    public JSONPathQuery<T> as(Class<T> type) {
        this.type=type;
        return this;
    }

    @Override
    public JSONPathQuery<T> asList(Class<T> clazz) {
        this.type=clazz;
        this.isList=true;
        return this;
    }

    @Override
    public JSONPathQuery<T> limit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public JSONPathQuery<T> skip(int skip) {
        this.skip = skip;
        return this;
    }

    private <T, U extends Comparable<? super U>> void sortResult(JSONPathResult result, Class<T> type) {
        if (result == null || !result.isList()) {
            return;
        }
        List<Object> rawList = result.getAsList();
        List<T> typedList = new ArrayList<>(rawList.size());
        for (Object o : rawList) {
             JSONObject obj = (JSONObject) o;
             T t=obj.toBean(type);
            typedList.add(t);
        }

        if (sort == null || sort.isEmpty()) {
            return;
        }
        List<JSortConditionModel<T, U>> convertedConditions = new ArrayList<>();
        for (JSortConditionModel<?, ?> condition : sort) {
            @SuppressWarnings("unchecked")
            JSortConditionModel<T, U> convertedCondition = (JSortConditionModel<T, U>) condition;
            convertedConditions.add(convertedCondition);
        }
        List<T> sortedList = JSONArraySorter.<T, U>dynamicSort(typedList, convertedConditions);
        result=new JSONPathResult(sortedList);
    }

    private void paginateResult(JSONPathResult result) {
        if(result.isList()) {
            List<Object> list= result.getAsList();
            List<Object> data=list.stream().skip(skip).limit(limit).collect(Collectors.toList());
            result=new JSONPathResult(data);
        }
    }

}
