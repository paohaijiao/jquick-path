package com.github.paohaijiao.ext.template;

import com.github.paohaijiao.param.JContext;

public class JVariableExpression implements JExpression{
    private final String variableName;

    public JVariableExpression(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public Object evaluate(JContext context) {
        return context.get(variableName);
    }
}
