package com.paohaijiao.javelin.visitor;

import com.paohaijiao.javelin.param.JContext;
import com.paohaijiao.javelin.parser.JQuickJSONPathParser;
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
    /**
     * ()
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Object visitScriptExpression(JQuickJSONPathParser.ScriptExpressionContext ctx) {
        JQuickJSONPathParser.ExprContext exprCtx = ctx.expr();
        return visit(exprCtx);
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





//    @Override
//    public Object visitStringLiteral(JQuickJSONPathParser.StringLiteralContext ctx) {
//        Object current = currentJsonObject;
//        String key = ctx.getText().replaceAll("^[\"']|[\"']$", "");
//        if (current instanceof JSONObject) {
//            return ((JSONObject) current).has(key) ? ((JSONObject) current).get(key) : null;
//        } else if (current instanceof List) {
//            List<Object> results = new ArrayList<>();
//            for (Object item : (List<?>) current) {
//                if (item instanceof JSONObject && ((JSONObject) item).has(key)) {
//                    results.add(((JSONObject) item).get(key));
//                }
//            }
//            return results.isEmpty() ? null : results;
//        }
//        return null;
//    }


}
