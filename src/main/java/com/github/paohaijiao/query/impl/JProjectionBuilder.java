package com.github.paohaijiao.query.impl;

import java.util.ArrayList;
import java.util.List;

public class JProjectionBuilder <T>{

    private final List<String> projections = new ArrayList<>();

    private final Class<T> type;

    public JProjectionBuilder(Class<T> type) {
        this.type = type;
    }

    public JProjectionBuilder<T> field(String fieldName) {
        projections.add(fieldName);
        return this;
    }

    public JProjectionBuilder<T> fields(String... fieldNames) {
        for (String fieldName : fieldNames) {
            projections.add(fieldName);
        }
        return this;
    }

    public JProjectionBuilder<T> exclude(String... fieldNames) {

        return this;
    }

    public String build() {
        if (projections.isEmpty()) {
            return "*";
        }
        return String.join(",", projections);
    }
}
