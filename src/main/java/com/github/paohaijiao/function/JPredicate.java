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
package com.github.paohaijiao.function;

import com.github.paohaijiao.util.JPredicates;

@FunctionalInterface
public interface JPredicate<T> {


    String toJSONPathExpression();


    default JPredicate<T> and(JPredicate<T> other) {
        return JPredicates.and(this, other);
    }

    default JPredicate<T> or(JPredicate<T> other) {
        return JPredicates.or(this, other);
    }

    default JPredicate<T> negate() {
        return JPredicates.not(this);
    }

    static <T> JPredicate<T> eq(String property, Object value) {
        return JPredicates.eq(property, value);
    }

    static <T> JPredicate<T> ne(String property, Object value) {
        return JPredicates.ne(property, value);
    }

    static <T> JPredicate<T> gt(String property, Object value) {
        return JPredicates.gt(property, value);
    }

    static <T> JPredicate<T> ge(String property, Object value) {
        return JPredicates.ge(property, value);
    }

    static <T> JPredicate<T> lt(String property, Object value) {
        return JPredicates.lt(property, value);
    }

    static <T> JPredicate<T> le(String property, Object value) {
        return JPredicates.le(property, value);
    }

    static <T> JPredicate<T> like(String property, String pattern) {
        return JPredicates.like(property, pattern);
    }

    static <T> JPredicate<T> regex(String property, String regex) {
        return JPredicates.regex(property, regex);
    }

    static <T> JPredicate<T> in(String property, Object... values) {
        return JPredicates.in(property, values);
    }

    static <T> JPredicate<T> exists(String property) {
        return JPredicates.exists(property);
    }

    static <T> JPredicate<T> notExists(String property) {
        return JPredicates.notExists(property);
    }

    static <T> JPredicate<T> custom(String expression) {
        return JPredicates.custom(expression);
    }

    static <T> JPredicate<T> of(String property, String operator, Object value) {
        return JPredicates.of(property, operator, value);
    }

}
