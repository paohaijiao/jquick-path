package com.github.paohaijiao.convert;

import com.github.paohaijiao.context.JValueContext;
import com.github.paohaijiao.function.JTypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements JTypeConverter<LocalDate> {

    //JValueContext.registerConverter(LocalDate.class, new LocalDateConverter());

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    @Override
    public LocalDate convert(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof LocalDate) {
            return (LocalDate) value;
        }
        if (value instanceof CharSequence) {
            return LocalDate.parse(value.toString(), formatter);
        }
        throw new IllegalArgumentException("cannot convert " + value.getClass() + " to localdate");
    }
}
