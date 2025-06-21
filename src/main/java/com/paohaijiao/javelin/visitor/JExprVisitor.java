package com.paohaijiao.javelin.visitor;

import com.paohaijiao.javelin.evalue.JEvaluator;
import com.paohaijiao.javelin.exception.JAssert;
import com.paohaijiao.javelin.parser.JQuickJSONPathParser;
import com.paohaijiao.javelin.util.JObjectConverter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JExprVisitor extends JValueVisitor {
    @Override
    public Object visitNetestDotExpr(JQuickJSONPathParser.NetestDotExprContext ctx) {
        if(ctx.dotExpr() != null) {
            return visit(ctx.dotExpr());
        }
        return null;
    }
    @Override
    public Object visitBracketExpression(JQuickJSONPathParser.BracketExpressionContext ctx) {
        //obj['property'], array[0], obj[*] obj[1,2,3] (@.length-1)) ,?(@.price > 10),
        //multiple subscripts separated by commas
        Object target = visit(ctx.expr());
        Object subscript = null;
        if(null!=ctx.subscript()){
            subscript=visitSubscript(ctx.subscript());
        }
        return getValue(target, subscript);
    }
    @Override
    public Object visitFunctionCallExpression(JQuickJSONPathParser.FunctionCallExpressionContext ctx) {
        Object function=null;
        if(null!=ctx.expr()){
            function=visit(ctx.expr());
        }
        JAssert.notNull(function,"方法不存在");
        List<Object> arguments = new ArrayList<>();
        if (ctx.exprList() != null) {
            for (JQuickJSONPathParser.ExprContext argCtx : ctx.exprList().expr()) {
                arguments.add(visit(argCtx));
            }
        }
        String functionName=(String)function;
        Object object=JEvaluator.evaluateFunction(functionName,arguments);
        return object;
    }

    @Override
    public Object visitMultiplicativeExpression(JQuickJSONPathParser.MultiplicativeExpressionContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        String operator = ctx.getChild(1).getText();
        Object object=evaluateBinaryOperation(left,right,operator);
        return object;
    }

    @Override
    public Object visitAdditiveExpression(JQuickJSONPathParser.AdditiveExpressionContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        String operator = ctx.getChild(1).getText();
        Object object=evaluateBinaryOperation(left,right,operator);
        return object;
    }

    @Override
    public Object visitComparisonExpression(JQuickJSONPathParser.ComparisonExpressionContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        String operator = ctx.getChild(1).getText();
        Object object=evaluateBinaryOperation(left,right,operator);
        return object;
    }
    @Override
    public Object visitEqualityExpression(JQuickJSONPathParser.EqualityExpressionContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        String operator = ctx.getChild(1).getText();
        Object object=evaluateBinaryOperation(left,right,operator);
        return object;
    }
    @Override
    public Object visitRegexExpression(JQuickJSONPathParser.RegexExpressionContext ctx) {
        Object left = visit(ctx.expr());
        if (left == null) {
            return false;
        }
        String regexPattern = ctx.regexLiteral().getText();
        String patternWithFlags = regexPattern.substring(1, regexPattern.length() - 1);
        int lastSlash = patternWithFlags.lastIndexOf('/');
        String pattern;
        int flags = 0;
        if (lastSlash >= 0) {
            pattern = patternWithFlags.substring(0, lastSlash);
            String flagsStr = patternWithFlags.substring(lastSlash + 1);
            flags = parseRegexFlags(flagsStr);
        } else {
            pattern = patternWithFlags;
        }

        try {
            Pattern compiledPattern = Pattern.compile(pattern, flags);
            Matcher matcher = compiledPattern.matcher(left.toString());
            return matcher.matches();
        } catch (Exception e) {
            throw new RuntimeException("Invalid regular expression pattern: " + pattern, e);
        }
    }
    @Override
    public List<Object>  visitInExpression(JQuickJSONPathParser.InExpressionContext ctx) {
        Object value = visit(ctx.expr());
        List<Object> valueList = parseValueList(ctx.valueList());
        JAssert.notNull(value, "The  value for 'in' operator cannot be null.");
        List<?> list= JObjectConverter.assign(value,List.class);
        List<Object> newList=new ArrayList<>();
        for (Object item : list) {
            if(valueList.contains(item)){
                newList.add(item);
            }
        }
        return newList;
    }
    @Override
    public Object visitLogicalAndExpression(JQuickJSONPathParser.LogicalAndExpressionContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        String operator = ctx.getChild(1).getText();
        Object object=evaluateBinaryOperation(left,right,operator);
        return object;
    }
    @Override
    public Object visitLogicalOrExpression(JQuickJSONPathParser.LogicalOrExpressionContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        String operator = ctx.getChild(1).getText();
        Object object=evaluateBinaryOperation(left,right,operator);
        return object;
    }
    @Override
    public Object visitLiteralExpression(JQuickJSONPathParser.LiteralExpressionContext ctx) {
        if(null!=ctx.literal()){
            return visitLiteral(ctx.literal());
        }
        JAssert.throwNewException("Invalid literal expression");
        return null;
    }
    @Override
    public Object visitIdentifierExpression(JQuickJSONPathParser.IdentifierExpressionContext ctx) {
        if(null!=ctx.identifier()){
            return visitIdentifier(ctx.identifier());
        }
        JAssert.throwNewException("Invalid identifier expression");
        return null;
    }
    @Override
    public Object visitRootExpression(JQuickJSONPathParser.RootExpressionContext ctx) {
        return this.rootJsonObject;
    }
    @Override
    public Object visitCurrentExpression(JQuickJSONPathParser.CurrentExpressionContext ctx) {
        return this.currentJsonObject;
    }

    @Override
    public Object visitParenthesizedExpression(JQuickJSONPathParser.ParenthesizedExpressionContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Object visitNegationExpression(JQuickJSONPathParser.NegationExpressionContext ctx) {
        Object value=visit(ctx.expr());
        if (value instanceof Number) {
            if (value instanceof Integer) {
                return -((Integer) value);
            }
            if (value instanceof Long) {
                return -((Long) value);
            }
            if (value instanceof Float) {
                return -((Float) value);
            }
            if (value instanceof Double) {
                return -((Double) value);
            }
            if (value instanceof BigDecimal) {
                return ((BigDecimal) value).negate();
            }
            if (value instanceof BigInteger) {
                return ((BigInteger) value).negate();
            }
            return -((Number) value).doubleValue();
        }
        JAssert.throwNewException("Cannot apply negation to non-numeric value: " +
                (value != null ? value.getClass().getSimpleName() : "null"));
        return null;
    }
    @Override
    public Object visitNotExpression(JQuickJSONPathParser.NotExpressionContext ctx) {
        Object value = visit(ctx.expr());
        if (value instanceof Boolean) {
            return !((Boolean) value);
        }
        JAssert.throwNewException("not supported for non-boolean value: " + value);
        return false;
    }
    @Override
    public Object visitDirectDotExpression(JQuickJSONPathParser.DirectDotExpressionContext ctx) {
       // e.g., obj.property, $.*, @.field
        Object leftValue=new Object();
        String rightValue="";
        if (ctx.leftDotExpr() != null) {
            leftValue = visitLeftDotExpr(ctx.leftDotExpr());
        }
        if (ctx.rightDotExpr() != null) {
            rightValue = visitRightDotExpr(ctx.rightDotExpr());
        }
        if (rightValue.equals("*")) {
            return visitWildcard(leftValue);
        }
        return getValueByKey(leftValue, rightValue);
    }
    @Override
    public Object visitLeftDotExpr(JQuickJSONPathParser.LeftDotExprContext ctx) {
        if(null!=ctx.identifier()){
            String  field=visitIdentifier(ctx.identifier());
            Object object= this.getValueByKey(this.currentJsonObject,field);
            return object;
        }  else if("$".equals(ctx.getText())){
            return super.getRoot();
        }else if("@".equals(ctx.getText())){
            return super.getCurrent();
        }else if(null!=ctx.literal()){
            return visitLiteral(ctx.literal());
        }else if(null!=ctx.expr()){
            return visit(ctx.expr());
        }
        JAssert.throwNewException("Invalid expression");
        return null;
    }
    @Override
    public String visitRightDotExpr(JQuickJSONPathParser.RightDotExprContext ctx) {
        if(null!=ctx.identifier()) {
          return visitIdentifier(ctx.identifier());
        }else if("*".equals(ctx.getText())){
           return "*";
        }
        JAssert.throwNewException("Invalid expression");
        return null;
    }

    @Override
    public Object visitChainedDotExpression(JQuickJSONPathParser.ChainedDotExpressionContext ctx) {
        //e.g., obj.property.subproperty
        Object currentValue = visit(ctx.dotExpr());
        String field = visitRightDotExpr(ctx.rightDotExpr());
        if (field.equals("*")) {
            return visitWildcard(this.currentJsonObject);
        }
        return getValueByKey(currentValue, field);
    }
    private int parseRegexFlags(String flagsStr) {
        int flags = 0;
        for (char flag : flagsStr.toCharArray()) {
            switch (flag) {
                case 'i': flags |= Pattern.CASE_INSENSITIVE; break;
                case 'm': flags |= Pattern.MULTILINE; break;
                case 's': flags |= Pattern.DOTALL; break;
                case 'u': flags |= Pattern.UNICODE_CASE; break;
                case 'x': flags |= Pattern.COMMENTS; break;
                case 'd': flags |= Pattern.UNIX_LINES; break;
            }
        }
        return flags;
    }
    private List<Object> parseValueList(JQuickJSONPathParser.ValueListContext ctx) {
        List<Object> values = new ArrayList<>();
        for (JQuickJSONPathParser.LiteralContext literal : ctx.literal()) {
            values.add(visit(literal));
        }
        return values;
    }


}
