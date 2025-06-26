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
