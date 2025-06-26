package com.github.paohaijiao.ext.template;

import com.github.paohaijiao.param.JContext;

public class JPlaceholderResolver {
    private static final String PLACEHOLDER_PREFIX = "${";
    private static final String PLACEHOLDER_SUFFIX = "}";

    public static boolean isPlaceholder(String text) {
        return text.startsWith(PLACEHOLDER_PREFIX) && text.endsWith(PLACEHOLDER_SUFFIX);
    }

    public static JExpression resolve(String placeholder, JContext context) {
        if (!isPlaceholder(placeholder)) {
            return null;
        }

        String variableName = placeholder.substring(PLACEHOLDER_PREFIX.length(), placeholder.length() - PLACEHOLDER_SUFFIX.length()).trim();
        return new JVariableExpression(variableName);
    }
}
