package com.github.paohaijiao.visitor;

import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickJSONPathParser;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class JSONPathCommonVisitor extends JRootAndPathVisitor {

    public JSONPathCommonVisitor(Object root, JContext context) {
        this.rootJsonObject = root;
        this.currentJsonObject = rootJsonObject;
        this.context = context;
    }

    public JSONPathCommonVisitor(Object root) {
        this.rootJsonObject = root;
        this.currentJsonObject = rootJsonObject;
        this.context = new JContext();
    }




    @Override
    public Pattern visitRegexLiteral(JQuickJSONPathParser.RegexLiteralContext ctx) {
        String regexText = ctx.getText();
        int lastSlash = regexText.lastIndexOf('/');
        String pattern = regexText.substring(1, lastSlash);
        String flags = regexText.substring(lastSlash + 1);
        int javaFlags = 0;
        if (!flags.isEmpty()) {
            for (char flag : flags.toCharArray()) {
                switch (flag) {
                    case 'i':
                        javaFlags |= Pattern.CASE_INSENSITIVE;
                        break;
                    case 'm':
                        javaFlags |= Pattern.MULTILINE;
                        break;
                    case 's':
                        javaFlags |= Pattern.DOTALL;
                        break;
                }
            }
        }

        try {
            return Pattern.compile(pattern, javaFlags);
        } catch (PatternSyntaxException e) {
            throw new RuntimeException("Invalid regular expression: " + pattern, e);
        }
    }


}
