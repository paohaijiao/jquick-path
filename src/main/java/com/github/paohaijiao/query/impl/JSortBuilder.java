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
