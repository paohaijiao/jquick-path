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
package com.github.paohaijiao.query.impl;

import com.github.paohaijiao.function.JPredicate;

public class JFilterBuilder <T>{

    private final StringBuilder filterExpression = new StringBuilder();

    private final Class<T> type;

    public JFilterBuilder(Class<T> type) {
        this.type = type;
    }

    public JFilterBuilder<T> eq(String property, Object value) {
        appendCondition(property, "==", value);
        return this;
    }

    public JFilterBuilder<T> ne(String property, Object value) {
        appendCondition(property, "!=", value);
        return this;
    }

    public JFilterBuilder<T> gt(String property, Object value) {
        appendCondition(property, ">", value);
        return this;
    }

    public JFilterBuilder<T> ge(String property, Object value) {
        appendCondition(property, ">=", value);
        return this;
    }

    public JFilterBuilder<T> lt(String property, Object value) {
        appendCondition(property, "<", value);
        return this;
    }

    public JFilterBuilder<T> le(String property, Object value) {
        appendCondition(property, "<=", value);
        return this;
    }

    public JFilterBuilder<T> like(String property, String pattern) {
        appendCondition(property, "=~", "/" + pattern + "/");
        return this;
    }

    public JFilterBuilder<T> in(String property, Object... values) {
        filterExpression.append(property).append(" in [");
        for (int i = 0; i < values.length; i++) {
            if (i > 0) filterExpression.append(",");
            appendValue(values[i]);
        }
        filterExpression.append("]");
        return this;
    }

    public JFilterBuilder<T> and() {
        filterExpression.append(" && ");
        return this;
    }

    public JFilterBuilder<T> or() {
        filterExpression.append(" || ");
        return this;
    }

    public JFilterBuilder<T> not() {
        filterExpression.append("!");
        return this;
    }

    public JFilterBuilder<T> predicate(JPredicate<T> predicate) {
        filterExpression.append(predicate.toJSONPathExpression());
        return this;
    }

    public String build() {
        return filterExpression.toString();
    }

    private void appendCondition(String property, String operator, Object value) {
        filterExpression.append(property).append(operator);
        appendValue(value);
    }

    private void appendValue(Object value) {
        if (value instanceof String) {
            filterExpression.append("'").append(value).append("'");
        } else if (value instanceof Number) {
            filterExpression.append(value);
        } else if (value instanceof Boolean) {
            filterExpression.append(value);
        } else {
            filterExpression.append("'").append(value.toString()).append("'");
        }
    }
}
