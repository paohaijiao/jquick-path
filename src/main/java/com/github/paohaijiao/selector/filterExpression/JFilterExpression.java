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
package com.github.paohaijiao.selector.filterExpression;

import com.github.paohaijiao.function.JPredicate;

/**
 * packageName com.github.paohaijiao.selector
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/10
 */
public class JFilterExpression {
    private final String expression;

    private JFilterExpression(String expression) {
        this.expression = expression;
    }
    public static <T> JFilterExpression of(JPredicate<T> predicate) {
        return new JFilterExpression("?(" + predicate.toJSONPathExpression() + ")");
    }
    public static JFilterExpression of(String expression) {
        if (!expression.startsWith("?(") || !expression.endsWith(")")) {
            throw new IllegalArgumentException("Invalid filter expression format");
        }
        return new JFilterExpression(expression);
    }
    public String getExpression() {
        return expression;
    }
    public String getInnerExpression() {

        return expression.substring(2, expression.length() - 1);
    }

    @Override
    public String toString() {
        return expression;
    }
}
