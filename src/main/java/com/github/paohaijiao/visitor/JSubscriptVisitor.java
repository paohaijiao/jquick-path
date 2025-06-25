package com.github.paohaijiao.visitor;

import com.github.paohaijiao.model.JSlice;
import com.github.paohaijiao.parser.JQuickJSONPathParser;
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.util.JObjectConverter;
import com.github.paohaijiao.util.JStringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JSubscriptVisitor extends JValueVisitor {

    @Override
    public Object visitSubscript(JQuickJSONPathParser.SubscriptContext ctx) {
        if (ctx.number() != null) {//pass
            BigDecimal numberDecimal = visitNumber(ctx.number());
            Integer index = numberDecimal.intValue();
            return index;
        } else if (ctx.stringLiteral() != null) {//pass
            String fields = visitStringLiteral(ctx.stringLiteral());
            String fieldName = JStringUtils.trim(fields);
            return fieldName;
        } else if (ctx.slice() != null) {//pass
            JSlice slice = visitSlice(ctx.slice());
            return slice;
        } else if (ctx.filterExpression() != null) {//pass
            return visitFilterExpression(ctx.filterExpression());
        } else if (ctx.getText().equals("*")) {//pass
            return visitWildcard(this.currentJsonObject);
        }
        return null;
    }


    @Override
    public List<?> visitFilterExpression(JQuickJSONPathParser.FilterExpressionContext ctx) {
        List<Object> list = new ArrayList<>();
        List<?> data = this.getList(this.currentJsonObject);
        for (Object o : data) {
            this.currentJsonObject = o;
            if (ctx.expr() != null) {
                Object obj = visit((ctx.expr()));
                if (obj instanceof Boolean) {
                    Boolean bool = JObjectConverter.assign(obj, Boolean.class);
                    if (bool) {
                        list.add(o);
                    }
                } else {
                    JAssert.throwNewException("FilterExpression only accept boolean express");
                }
            }
        }
        return list;
    }




}
