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
package com.github.paohaijiao.builder;

import com.github.paohaijiao.core.JSONPath;
import com.github.paohaijiao.function.JPredicate;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.query.JSONPathQuery;
import com.github.paohaijiao.query.impl.JFilterBuilder;
import com.github.paohaijiao.query.impl.JProjectionBuilder;
import com.github.paohaijiao.query.impl.JSortBuilder;
import com.github.paohaijiao.selector.filterExpression.JFilterExpression;
import com.github.paohaijiao.selector.subscript.JSubscripts;

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

    public static <T> JSONPathQuery<T> root(JSubscripts subscripts) {
        return new JSONPath<>(subscripts);
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
        return null;
    }
}
