package com.github.paohaijiao.ext.template;

import com.github.paohaijiao.param.JContext;

public class JTemplateParser {

    public static JExpression parse(String template, JContext context) {
        if (JPlaceholderResolver.isPlaceholder(template)) {
            return JPlaceholderResolver.resolve(template, context);
        }
        return new JLiteralExpression(template);
    }
}
