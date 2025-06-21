package com.paohaijiao.javelin.visitor;

import com.paohaijiao.javelin.model.JSlice;
import com.paohaijiao.javelin.exception.JAssert;
import com.paohaijiao.javelin.parser.JQuickJSONPathParser;
import com.paohaijiao.javelin.util.JStringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JValueVisitor extends JSONPathCoreVisitor{

    @Override
    public Object visitVariable(JQuickJSONPathParser.VariableContext ctx) {
        if(null!=ctx.STRING()){
         return null;
        }
        return null;
    }
    @Override
    public BigDecimal visitNumber(JQuickJSONPathParser.NumberContext ctx) {
        if(null==ctx||null==ctx.NUMBER()){
            return null;
        }else{
            return new BigDecimal(ctx.NUMBER().getText());
        }
    }
    @Override
    public String visitStringLiteral(JQuickJSONPathParser.StringLiteralContext ctx) {
        String string= ctx.STRING().getText();
        return JStringUtils.trim(string);
    }
    @Override
    public Object visitLiteral(JQuickJSONPathParser.LiteralContext ctx) {
        if(null!=ctx.stringLiteral()){
            return visitStringLiteral(ctx.stringLiteral());
        }else if(null!=ctx.number()){
            BigDecimal bigDecimal= visitNumber(ctx.number());
            return bigDecimal;
        }else if("null".equals(ctx.getText())){
          return null;
        }else if("true".equals(ctx.getText())){
            return Boolean.TRUE;
        }else if("false".equals(ctx.getText())){
            return Boolean.FALSE;
        }
        JAssert.throwNewException("the literal not support this type");
        return null;
    }
    @Override
    public String visitIdentifier(JQuickJSONPathParser.IdentifierContext ctx) {
        String identifier = ctx.getText();
        //Object object=this.getValue(this.getCurrent(), identifier);
        return identifier;
    }
    @Override
    public JSlice visitSlice(JQuickJSONPathParser.SliceContext ctx) {
        JSlice slice = new JSlice();
        if(null!=ctx.start()){
            Integer number=visitStart(ctx.start());
            slice.setStart(number);
        }
        if(null!=ctx.end()){
            Integer number=visitEnd(ctx.end());
            slice.setEnd(number);
        }
        if(null!=ctx.step()){
            Integer number=visitStep(ctx.step());
            slice.setStep(number);
        }
        return slice;
    }
    @Override
    public List<?> visitValueList(JQuickJSONPathParser.ValueListContext ctx) {
        List<Object> values = new ArrayList<>();
        for (JQuickJSONPathParser.LiteralContext literalCtx : ctx.literal()) {
            Object value = visitLiteral(literalCtx);
            if (value != null) {
                values.add(value);
            }
        }
        return values;
    }
    @Override
    public List<Object> visitExprList(JQuickJSONPathParser.ExprListContext ctx) {
        List<Object> results = new ArrayList<>();
        for (JQuickJSONPathParser.ExprContext exprCtx : ctx.expr()) {
            Object result = visit(exprCtx);
            results.add(result);
        }
        return results;
    }
    @Override
    public Integer visitStart(JQuickJSONPathParser.StartContext ctx) {
        BigDecimal number=visitNumber(ctx.number());
        return null==number?null:number.intValue();
    }
    @Override
    public Integer visitEnd(JQuickJSONPathParser.EndContext ctx) {
        BigDecimal number=visitNumber(ctx.number());
        return null==number?null:number.intValue();
    }
    @Override
    public Integer visitStep(JQuickJSONPathParser.StepContext ctx) {
        BigDecimal number=visitNumber(ctx.number());
        return null==number?null:number.intValue();
    }




}
