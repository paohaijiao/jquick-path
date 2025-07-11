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
