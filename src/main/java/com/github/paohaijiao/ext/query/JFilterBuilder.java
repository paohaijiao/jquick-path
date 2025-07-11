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
package com.github.paohaijiao.ext.query;

import com.github.paohaijiao.model.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class JFilterBuilder {

    public static Predicate<Object> eq(String key, Object value) {
        return obj -> {
            if (obj instanceof JSONObject) {
                return value.equals(((JSONObject) obj).get(key));
            }
            return false;
        };
    }

    public static Predicate<Object> ne(String key, Object value) {
        return obj -> {
            if (obj instanceof JSONObject) {
                return !value.equals(((JSONObject) obj).get(key));
            }
            return false;
        };
    }

    public static Predicate<Object> gt(String key, Comparable<?> value) {
        return obj -> {
            if (obj instanceof JSONObject) {
                Object fieldValue = ((JSONObject) obj).get(key);
                if (fieldValue instanceof Comparable) {
                    return ((Comparable) fieldValue).compareTo(value) > 0;
                }
            }
            return false;
        };
    }

    public static Predicate<Object> lt(String key, Comparable<?> value) {
        return obj -> {
            if (obj instanceof JSONObject) {
                Object fieldValue = ((JSONObject) obj).get(key);
                if (fieldValue instanceof Comparable) {
                    return ((Comparable) fieldValue).compareTo(value) < 0;
                }
            }
            return false;
        };
    }

    public static Predicate<Object> regex(String key, String pattern) {
        return obj -> {
            if (obj instanceof JSONObject) {
                Object value = ((JSONObject) obj).get(key);
                return value != null && Pattern.matches(pattern, value.toString());
            }
            return false;
        };
    }

    public static Predicate<Object> in(String key, List<?> values) {
        return obj -> {
            if (obj instanceof JSONObject) {
                Object value = ((JSONObject) obj).get(key);
                return values.contains(value);
            }
            return false;
        };
    }

    public static Predicate<Object> and(Predicate<Object>... predicates) {
        return Arrays.stream(predicates).reduce(Predicate::and).orElse(obj -> true);
    }

    public static Predicate<Object> or(Predicate<Object>... predicates) {
        return Arrays.stream(predicates).reduce(Predicate::or).orElse(obj -> false);
    }

    public static Predicate<Object> not(Predicate<Object> predicate) {
        return predicate.negate();
    }
}
