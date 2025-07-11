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

import com.github.paohaijiao.model.JSortConditionModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class JSortBuilder <T> {

    private final List<JSortConditionModel<T, ?>> sortExpressions = new ArrayList<>();


    public JSortBuilder<T> asc(String property) {
        JSortConditionModel<T, ?> sortExpression = createSortExpression(property, true);
        sortExpressions.add(sortExpression);
        return this;
    }

    public JSortBuilder<T> desc(String property) {
        JSortConditionModel<T, ?> sortExpression = createSortExpression(property, false);
        sortExpressions.add(sortExpression);
        return this;
    }


    public <U extends Comparable<? super U>> JSortBuilder<T> asc(Function<T, U> keyExtractor) {
        sortExpressions.add(new JSortConditionModel<>(keyExtractor, true));
        return this;
    }

    public <U extends Comparable<? super U>> JSortBuilder<T> desc(Function<T, U> keyExtractor) {
        sortExpressions.add(new JSortConditionModel<>(keyExtractor, false));
        return this;
    }

    public List<JSortConditionModel<T, ?>> build() {
        return new ArrayList<>(sortExpressions);
    }

    private <U extends Comparable<? super U>> JSortConditionModel<T, U> createSortExpression(
            String property, boolean ascending) {
        Function<T, U> keyExtractor = obj -> {
            try {
                Field field = getField(obj.getClass(), property);
                field.setAccessible(true);
                @SuppressWarnings("unchecked")
                U value = (U) field.get(obj);
                if (value == null) {
                    throw new IllegalArgumentException("Field " + property + " is null");
                }
                return value;
            } catch (Exception e) {
                throw new RuntimeException("Failed to access property: " + property, e);
            }
        };
        return new JSortConditionModel<>(keyExtractor, ascending);
    }
    private Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass == null) {
                throw e;
            }
            return getField(superClass, fieldName);
        }
    }
}
