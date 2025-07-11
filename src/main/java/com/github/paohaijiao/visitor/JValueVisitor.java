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
package com.github.paohaijiao.visitor;

import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.model.JSlice;
import com.github.paohaijiao.parser.JQuickJSONPathParser;
import com.github.paohaijiao.util.JStringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JValueVisitor extends JSONPathCoreVisitor {

    @Override
    public Object visitVariable(JQuickJSONPathParser.VariableContext ctx) {
        if (null != ctx.IDENTIFIER()) {
            String identifier = ctx.IDENTIFIER().getText();
            String key=JStringUtils.trim(identifier);
            JAssert.notNull(key, "identifier is null");
            return this.context.get(key);
        }
        JAssert.throwNewException("invalid variable");
        return null;
    }

    @Override
    public BigDecimal visitNumber(JQuickJSONPathParser.NumberContext ctx) {
        if (null == ctx || null == ctx.NUMBER()) {
            return null;
        } else {
            return new BigDecimal(ctx.NUMBER().getText());
        }
    }

    @Override
    public String visitStringLiteral(JQuickJSONPathParser.StringLiteralContext ctx) {
        String string = ctx.STRING().getText();
        return JStringUtils.trim(string);
    }

    @Override
    public Object visitLiteral(JQuickJSONPathParser.LiteralContext ctx) {
        if (null != ctx.stringLiteral()) {
            return visitStringLiteral(ctx.stringLiteral());
        } else if (null != ctx.number()) {
            BigDecimal bigDecimal = visitNumber(ctx.number());
            return bigDecimal;
        } else if ("null".equals(ctx.getText())) {
            return null;
        } else if ("true".equals(ctx.getText())) {
            return Boolean.TRUE;
        } else if ("false".equals(ctx.getText())) {
            return Boolean.FALSE;
        }
        JAssert.throwNewException("the literal not support this type");
        return null;
    }

    @Override
    public String visitIdentifier(JQuickJSONPathParser.IdentifierContext ctx) {
        String identifier = ctx.getText();
        return identifier;
    }

    @Override
    public JSlice visitSlice(JQuickJSONPathParser.SliceContext ctx) {
        JSlice slice = new JSlice();
        if (null != ctx.start()) {
            Integer number = visitStart(ctx.start());
            slice.setStart(number);
        }
        if (null != ctx.end()) {
            Integer number = visitEnd(ctx.end());
            slice.setEnd(number);
        }
        if (null != ctx.step()) {
            Integer number = visitStep(ctx.step());
            slice.setStep(number);
        }
        return slice;
    }

    @Override
    public List<Object> visitValueList(JQuickJSONPathParser.ValueListContext ctx) {
        List<Object> values = new ArrayList<>();
        for (JQuickJSONPathParser.LiteralContext literalCtx : ctx.literal()) {
            Object value = visitLiteral(literalCtx);
            values.add(value);
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
        BigDecimal number = visitNumber(ctx.number());
        return null == number ? null : number.intValue();
    }

    @Override
    public Integer visitEnd(JQuickJSONPathParser.EndContext ctx) {
        BigDecimal number = visitNumber(ctx.number());
        return null == number ? null : number.intValue();
    }

    @Override
    public Integer visitStep(JQuickJSONPathParser.StepContext ctx) {
        BigDecimal number = visitNumber(ctx.number());
        return null == number ? null : number.intValue();
    }


}
