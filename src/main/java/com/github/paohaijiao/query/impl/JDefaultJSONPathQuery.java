package com.github.paohaijiao.query.impl;

import com.github.paohaijiao.console.JConsole;
import com.github.paohaijiao.executor.JSONPathExecutor;
import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.query.JSONPathQuery;
import com.github.paohaijiao.support.JSONArraySorter;

import java.util.List;
import java.util.stream.Collectors;

public class JDefaultJSONPathQuery<T> implements JSONPathQuery<T> {

    JConsole console = new JConsole();

    private final Object jsonData;

    private final Class<T> type;

    private String filter;

    private String projection;

    private String sort;

    private Integer limit;

    private Integer skip;

    public JDefaultJSONPathQuery(Object jsonData, Class<T> type) {
        this.jsonData = jsonData;
        this.type = type;
    }

    @Override
    public JSONPathResult execute() {
        StringBuilder pathBuilder = new StringBuilder("$");
        if (filter != null && !filter.isEmpty()) {
            pathBuilder.append("[?(").append(filter).append(")]");
        }

        if (projection != null && !projection.isEmpty()) {
            pathBuilder.append(".").append(projection);
        }
        JSONPathExecutor executor = new JSONPathExecutor(jsonData);
        executor.addErrorListener(error -> {
            String errorMessage =String.format("error: line %d:%d - %s%n",error.getLine(), error.getCharPosition(), error.getMessage());
            console.error(errorMessage);
            console.error(error.getMessage());
        });
        JSONPathResult result = executor.execute(pathBuilder.toString());
        if (sort != null && !sort.isEmpty()) {
            sortResult(result);
        }
        if (skip != null || limit != null) {
            paginateResult(result);
        }

        return result;
    }

    @Override
    public JSONPathQuery<T> filter(JFilterBuilder<T> filterBuilder) {
        this.filter = filterBuilder.build();
        return this;
    }

    @Override
    public JSONPathQuery<T> select(JProjectionBuilder<T> projectionBuilder) {
        this.projection = projectionBuilder.build();
        return this;
    }

    @Override
    public JSONPathQuery<T> sort(JSortBuilder<T> sortBuilder) {
        this.sort = sortBuilder.build();
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

    private void sortResult(JSONPathResult result) {
        if(result.isList()) {
            List<Object> list= result.getAsList();
            JSONArray jsonArray = new JSONArray(list);
            JSONArray jsonArraySorted=  JSONArraySorter.sortJSONArray(jsonArray,sort);
            result=new JSONPathResult(jsonArraySorted);
        }
    }

    private void paginateResult(JSONPathResult result) {
        if(result.isList()) {
            List<Object> list= result.getAsList();
            List<Object> data=list.stream().skip(skip).limit(limit).collect(Collectors.toList());
            result=new JSONPathResult(data);
        }
    }

}
