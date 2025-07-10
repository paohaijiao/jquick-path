package com.github.paohaijiao.builder;

import com.github.paohaijiao.function.JPredicate;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.query.JSONPathQuery;
import com.github.paohaijiao.core.JSONPath;
import com.github.paohaijiao.query.impl.JFilterBuilder;
import com.github.paohaijiao.query.impl.JProjectionBuilder;
import com.github.paohaijiao.query.impl.JSortBuilder;
import com.github.paohaijiao.selector.filterExpression.JFilterExpression;
import com.github.paohaijiao.selector.subscript.JSubscript;

import java.util.Map;

public class JSONPathQueryBuilder {

    public static <T> JSONPathQuery<T> from(Object object) {
        return new JSONPath<>(object);
    }
    public static <T> JSONPathQuery<T> from(String string) {
        return new JSONPath<>(string);
    }
    public static <T> JSONPathQuery<T> from(Map map) {
        return new JSONPath<>(map);
    }
    public static <T> JSONPathQuery<T> from(JSONObject jsonObject) {
        return new JSONPath<>(jsonObject);
    }
    public static <T> JSONPathQuery<T> root(JSONObject jsonObject) {
        return new JSONPath<>(jsonObject);
    }



    public static <T> JFilterBuilder<T> filter(Class<T> type) {
        return new JFilterBuilder<>(type);
    }
    public static <T> JFilterExpression of(JPredicate<T> predicate) {
        return JFilterExpression.of(predicate);
    }
    public static JFilterExpression of(String expression) {
        return JFilterExpression.of(expression);
    }

    public static <T> JProjectionBuilder<T> select(Class<T> type) {
        return new JProjectionBuilder<>(type);
    }

    public static <T> JSortBuilder<T> sort(Class<T> type) {
        return new JSortBuilder<>(type);
    }
}
