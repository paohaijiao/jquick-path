package com.github.paohaijiao.fatory;

import com.github.paohaijiao.context.JValueContext;
import com.github.paohaijiao.context.impl.JMapContext;
import com.github.paohaijiao.context.impl.JObjectContext;
import com.github.paohaijiao.context.impl.JSONStringContext;

import java.util.Map;

public class JValueContextFactory {

    public static JValueContext createContext(Object input) {
        return createContext(input, Object.class);
    }

    public static <T> JValueContext createContext(Object input, Class<T> targetType) {
        if (input instanceof String) {
            return new JSONStringContext((String) input, targetType);
        } else if (input instanceof Map) {
            return new JMapContext((Map<String, ?>) input, targetType);
        } else if (input != null) {
            return new JObjectContext(input, targetType);
        }
        throw new IllegalArgumentException("Unsupported input type: " +
                (input == null ? "null" : input.getClass().getName()));
    }

    public static JValueContext fromJson(String json) {
        return createContext(json);
    }

    public static JValueContext fromMap(Map<String, ?> map) {
        return createContext(map);
    }

    public static <T> JValueContext fromObject(T object) {
        return createContext(object);
    }

    public static <T> JValueContext fromObject(Object object, Class<T> targetType) {
        return createContext(object, targetType);
    }
}
