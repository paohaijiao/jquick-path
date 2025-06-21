package com.paohaijiao.javelin.visitor;

import com.paohaijiao.javelin.model.JSlice;
import com.paohaijiao.javelin.exception.JAssert;
import com.paohaijiao.javelin.parser.JQuickJSONPathParser;
import com.paohaijiao.javelin.util.JObjectConverter;
import com.paohaijiao.javelin.util.JStringUtils;
import java.math.BigDecimal;
import java.util.*;

public class JSubscriptVisitor extends JExprVisitor {

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
        } else if (ctx.scriptExpression() != null) {
            return visitScriptExpression(ctx.scriptExpression());
        } else if (ctx.getText().equals("*")) {//pass
            return visitWildcard(this.currentJsonObject);
        } else if (ctx.subscript() != null && ctx.getChild(0).getText().equals(",")) {
            // mutiple subscript（ like [1,2,3]）
            List<Object> results = new ArrayList<>();
            for (JQuickJSONPathParser.SubscriptContext subCtx : ctx.subscript()) {
                Object result = visitSubscript(subCtx);
                if (result != null) {
                    if (result instanceof List) {
                        results.addAll((List<?>) result);
                    } else {
                        results.add(result);
                    }
                }
            }
            return results;
        }
        return null;
    }



    @Override
    public List<?> visitFilterExpression(JQuickJSONPathParser.FilterExpressionContext ctx) {
        List<Object> list=new ArrayList<>();
        List<?> data=this.getList(this.currentJsonObject);
        for(Object o:data){
            this.currentJsonObject=o;
            if(ctx.expr()!=null){
              Object obj=  visit((ctx.expr()));
              if(obj instanceof Boolean){
                  Boolean bool=  JObjectConverter.assign(obj,Boolean.class);
                  if(bool){
                      list.add(o);
                  }
              }else {
                  JAssert.throwNewException("FilterExpression only accept boolean express");
              }
            }
        }
        return list;
    }

    @Override
    public Object visitScriptExpression(JQuickJSONPathParser.ScriptExpressionContext ctx) {
        return visit(ctx.expr());
    }




}
