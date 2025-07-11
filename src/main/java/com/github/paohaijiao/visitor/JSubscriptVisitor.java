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
        }else if (ctx.expr() != null) {
            Object obj= visit(ctx.expr());
            if(obj instanceof BigDecimal||obj instanceof Double||obj instanceof Float||obj instanceof Integer||obj instanceof Long){
                BigDecimal numberDecimal = new BigDecimal(obj.toString());
                Integer index = numberDecimal.intValue();
                return index;
            }else if(obj instanceof String){
                return (String)obj;
            }else{
                JAssert.throwNewException("[] express only support Integer or String");
                return null;
            }
        }
        JAssert.throwNewException("invalid expression , only support  Object[String] or Object[Number] or slice [?:?:?] or filter expression");
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
