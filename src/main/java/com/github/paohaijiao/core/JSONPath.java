package com.github.paohaijiao.core;

import com.github.paohaijiao.console.JConsole;
import com.github.paohaijiao.executor.JSONPathExecutor;
import com.github.paohaijiao.function.JPredicate;
import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.query.JSONPathQuery;
import com.github.paohaijiao.query.impl.JFilterBuilder;
import com.github.paohaijiao.query.impl.JProjectionBuilder;
import com.github.paohaijiao.query.impl.JSortBuilder;
import com.github.paohaijiao.selector.filterExpression.JFilterExpression;
import com.github.paohaijiao.selector.segment.JSegment;
import com.github.paohaijiao.selector.subscript.JSubscript;
import com.github.paohaijiao.support.JSONArraySorter;

import java.util.List;
import java.util.stream.Collectors;

public class JSONPath<T> implements JSONPathQuery<T> {

    JConsole console = new JConsole();

    private final Object jsonData;

    private  Class<T> type;
    private String pathExpression;

    private String filter;

    private String projection;


    private JFilterExpression filterExpression;


    private String sort;


    private Integer limit;

    private Integer skip;

    public JSONPath(Object jsonData, Class<T> type) {
        this.jsonData = jsonData;
        this.type = type;
    }
    private JSONPath(Object jsonData) {
        this.jsonData = jsonData;
    }
    public static JSONPath of(Object jsonData) {
        return new JSONPath(jsonData);
    }
    public JSONPath path(String path) {
        this.pathExpression = path;
        return this;
    }
    public <T> JSONPath filter(JPredicate<T> predicate) {
        this.filterExpression = JFilterExpression.of(predicate);
        return this;
    }
    public JSONPath filter(String expression) {
        this.filterExpression = JFilterExpression.of(expression);
        return this;
    }
    public JSONPath at(JSubscript subscript) {
        this.pathExpression = (pathExpression != null ? pathExpression : "") +
                "[" + subscript.toJSONPathExpression() + "]";
        return this;
    }
    public JSONPath at(JSubscript... subscripts) {
        StringBuilder sb = new StringBuilder(pathExpression != null ? pathExpression : "");
        for (JSubscript subscript : subscripts) {
            sb.append("[").append(subscript.toJSONPathExpression()).append("]");
        }
        this.pathExpression = sb.toString();
        return this;
    }
    public JSONPath segment(JSegment segment) {
        this.pathExpression = (pathExpression != null ? pathExpression : "") +
                segment.toJSONPathExpression();
        return this;
    }
    public JSONPath segments(JSegment... segments) {
        StringBuilder sb = new StringBuilder(pathExpression != null ? pathExpression : "");
        for (JSegment segment : segments) {
            sb.append(segment.toJSONPathExpression());
        }
        this.pathExpression = sb.toString();
        return this;
    }

    @Override
    public JSONPathResult execute() {
        StringBuilder fullPath = new StringBuilder("$");
        if (pathExpression != null) {
            fullPath.append(pathExpression.startsWith("[") ? "" : ".")
                    .append(pathExpression);
        }
        if (filterExpression != null) {
            fullPath.append(filterExpression);
        }
        StringBuilder pathBuilder = new StringBuilder("$");
        if (filter != null && !filter.isEmpty()) {
            pathBuilder.append("[?(").append(filter).append(")]");
        }
//        if (projectionBuilder != null) {
//            fullPath.append(".").append(projectionBuilder.build());
//        }

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
