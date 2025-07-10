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
package com.github.paohaijiao.selector.predicate;

import com.github.paohaijiao.function.JPredicate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName com.github.paohaijiao.predicate
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/10
 */
public class JInPredicate <T> implements JPredicate<T> {

    private final String property;

    private final List<?> values;

    public JInPredicate(String property, List<?> values) {
        this.property = property;
        this.values = values;
    }

    @Override
    public String toJSONPathExpression() {
        String valueList = values.stream()
                .map(this::formatValue)
                .collect(Collectors.joining(","));
        return "@." + property + " in [" + valueList + "]";
    }

    private String formatValue(Object val) {
        if (val == null) return "null";
        if (val instanceof String) return "'" + escapeString((String) val) + "'";
        if (val instanceof Number) return val.toString();
        if (val instanceof Boolean) return val.toString();
        return "'" + escapeString(val.toString()) + "'";
    }

    private String escapeString(String str) {
        return str.replace("'", "\\'").replace("\"", "\\\"");
    }
}
