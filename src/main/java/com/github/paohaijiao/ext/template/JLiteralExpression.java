package com.github.paohaijiao.ext.template;

import com.github.paohaijiao.param.JContext;

public class JLiteralExpression implements JExpression{

    private final Object value;

    public JLiteralExpression(Object value) {
        this.value = value;
    }

    @Override
    public Object evaluate(JContext context) {
        return value;
    }
}
