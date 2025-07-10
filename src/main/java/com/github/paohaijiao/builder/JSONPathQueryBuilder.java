package com.github.paohaijiao.builder;

import com.github.paohaijiao.query.JSONPathQuery;
import com.github.paohaijiao.query.impl.JDefaultJSONPathQuery;
import com.github.paohaijiao.query.impl.JFilterBuilder;
import com.github.paohaijiao.query.impl.JProjectionBuilder;
import com.github.paohaijiao.query.impl.JSortBuilder;

public class JSONPathQueryBuilder {

    public static <T> JSONPathQuery<T> from(Object jsonData, Class<T> type) {
        return new JDefaultJSONPathQuery<>(jsonData, type);
    }

    public static <T> JFilterBuilder<T> filter(Class<T> type) {
        return new JFilterBuilder<>(type);
    }

    public static <T> JProjectionBuilder<T> select(Class<T> type) {
        return new JProjectionBuilder<>(type);
    }

    public static <T> JSortBuilder<T> sort(Class<T> type) {
        return new JSortBuilder<>(type);
    }
}
