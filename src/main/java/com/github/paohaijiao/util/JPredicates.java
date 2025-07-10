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
package com.github.paohaijiao.util;

import com.github.paohaijiao.function.JPredicate;
import com.github.paohaijiao.selector.predicate.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * packageName com.github.paohaijiao.util
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/10
 */
public class JPredicates {

    private JPredicates() {

    }
    public static <T> JPredicate<T> eq(String property, Object value) {
        return new JSimplePredicate<>(property, "==", value);
    }

    public static <T> JPredicate<T> ne(String property, Object value) {
        return new JSimplePredicate<>(property, "!=", value);
    }

    public static <T> JPredicate<T> gt(String property, Object value) {
        return new JSimplePredicate<>(property, ">", value);
    }

    public static <T> JPredicate<T> ge(String property, Object value) {
        return new JSimplePredicate<>(property, ">=", value);
    }

    public static <T> JPredicate<T> lt(String property, Object value) {
        return new JSimplePredicate<>(property, "<", value);
    }

    public static <T> JPredicate<T> le(String property, Object value) {
        return new JSimplePredicate<>(property, "<=", value);
    }

    public static <T> JPredicate<T> like(String property, String pattern) {
        return new JSimplePredicate<>(property, "=~",
                "/" + pattern.replace("*", ".*").replace("?", ".") + "/");
    }

    public static <T> JPredicate<T> regex(String property, String regex) {
        return new JSimplePredicate<>(property, "=~", "/" + regex + "/");
    }

    public static <T> JPredicate<T> startsWith(String property, String prefix) {
        return regex(property, "^" + Pattern.quote(prefix));
    }

    public static <T> JPredicate<T> endsWith(String property, String suffix) {
        return regex(property, Pattern.quote(suffix) + "$");
    }

    public static <T> JPredicate<T> contains(String property, String substring) {
        return regex(property, ".*" + Pattern.quote(substring) + ".*");
    }
    public static <T> JPredicate<T> in(String property, Object... values) {
        return new JInPredicate<>(property, Arrays.asList(values));
    }

    public static <T> JPredicate<T> in(String property, List<?> values) {
        return new JInPredicate<>(property, values);
    }
    public static <T> JPredicate<T> exists(String property) {
        return new JExistsPredicate<>(property, true);
    }

    public static <T> JPredicate<T> notExists(String property) {
        return new JExistsPredicate<>(property, false);
    }
    public static <T> JPredicate<T> and(JPredicate<T> left, JPredicate<T> right) {
        return new JCompositePredicate<>(left, right, "&&");
    }

    public static <T> JPredicate<T> or(JPredicate<T> left, JPredicate<T> right) {
        return new JCompositePredicate<>(left, right, "||");
    }

    public static <T> JPredicate<T> not(JPredicate<T> predicate) {
        return new JNegatePredicate<>(predicate);
    }
    public static <T> JPredicate<T> custom(String expression) {
        return new JCustomPredicate<>(expression);
    }

    public static <T> JPredicate<T> of(String property, String operator, Object value) {
        return new JSimplePredicate<>(property, operator, value);
    }
}
