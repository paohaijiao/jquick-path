package com.github.paohaijiao.query.impl;

import java.util.ArrayList;
import java.util.List;

public class JSortBuilder <T> {

    private final List<String> sortExpressions = new ArrayList<>();

    private final Class<T> type;

    public JSortBuilder(Class<T> type) {
        this.type = type;
    }

    public JSortBuilder<T> asc(String property) {
        sortExpressions.add(property);
        return this;
    }

    public JSortBuilder<T> desc(String property) {
        sortExpressions.add("-" + property);
        return this;
    }

    public String build() {
        return String.join(",", sortExpressions);
    }
}
