package com.github.paohaijiao.ext.template;

import com.github.paohaijiao.param.JContext;

public interface JExpression {
    Object evaluate(JContext context);

}
