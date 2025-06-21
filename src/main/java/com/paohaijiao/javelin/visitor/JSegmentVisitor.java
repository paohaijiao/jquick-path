package com.paohaijiao.javelin.visitor;

import com.paohaijiao.javelin.model.JSlice;
import com.paohaijiao.javelin.exception.JAssert;
import com.paohaijiao.javelin.model.JSONObject;
import com.paohaijiao.javelin.parser.JQuickJSONPathParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JSegmentVisitor extends JSubscriptVisitor {

    @Override
    public Object visitIdentifierSegment(JQuickJSONPathParser.IdentifierSegmentContext ctx) {
        if (ctx.identifier() != null) {//pass
            String fieldName = ctx.identifier().getText();
            Object result = getValueByKey(this.currentJsonObject, fieldName);
            this.currentJsonObject = result;
        } else if (ctx.getChild(1).getText().equals("*")) {
            Object result = visitDotWildcard(this.currentJsonObject);
            this.currentJsonObject = result;
        }
        return this.currentJsonObject;
    }

    @Override
    public Object visitSubscriptSegment(JQuickJSONPathParser.SubscriptSegmentContext ctx) {
        if (null != ctx.subscript()) {
            Object value = visitSubscript(ctx.subscript());
            if (value instanceof String) {//pass
                Object result = getProperty(this.currentJsonObject, value.toString());
                return result;
            } else if (value instanceof Integer) {//pass
                Integer index = (Integer) value;
                if (index >= 0) {
                    Object result = getValueByIndex(this.currentJsonObject, (Integer) index);
                    return result;
                } else {
                    index = Math.abs(index);
                    List<?> list = this.getList(this.currentJsonObject);
                    Collections.reverse(list);
                    Object result = getValueByIndex(list, index);
                    return result;
                }
            } else if (value instanceof JSlice) {
                JSlice slice = (JSlice) value;
                List<?> list = this.getList(this.currentJsonObject);
                List<?> data = slice(list, slice.getStart(), slice.getEnd(), slice.getStep());
                return data;
            }
            return value;
        }
        //Assert.throwNewException("visitSubscriptSegment exception");
        return null;
    }

    @Override
    public Object visitChildIdentifierSegment(JQuickJSONPathParser.ChildIdentifierSegmentContext ctx) {
        if (null != ctx.identifier()) {//pass
            Object result = visitRecursiveField(ctx.identifier(), this.currentJsonObject);
            this.currentJsonObject = result;
            return this.currentJsonObject;
        }
        JAssert.throwNewException("visitSubscriptSegment exception");
        return null;
    }

    @Override
    public Object visitChildSubscriptSegment(JQuickJSONPathParser.ChildSubscriptSegmentContext ctx) {
        if (null != ctx.subscript()) {//pass
            Object result = visitSubscript(ctx.subscript());
            List<Object> results = new ArrayList<>();
            collectArrayElements(this.currentJsonObject, result, results);
            this.currentJsonObject = results;
            return this.currentJsonObject;
        }
        JAssert.throwNewException("visitSubscriptSegment exception");
        return null;
    }


    private void collectArrayElements(Object node, Object subscript, List<Object> results) {
        if (node instanceof List) {
            List<?> array = (List<?>) node;
            if (subscript instanceof Integer) {
                int index = (Integer) subscript;
                if (index >= 0 && index < array.size()) {
                    results.add(array.get(index));
                }
            } else if ("*".equals(subscript)) {
                results.addAll(array);
            } else if (subscript instanceof List) {
                for (Object sub : (List<?>) subscript) {
                    if (sub instanceof Integer) {
                        int index = (Integer) sub;
                        if (index >= 0 && index < array.size()) {
                            results.add(array.get(index));
                        }
                    }
                }
            }
        }
        if (node instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) node;
            for (Object value : map.values()) {
                collectArrayElements(value, subscript, results);
            }
        } else if (node instanceof List) {
            List<?> list = (List<?>) node;
            for (Object item : list) {
                collectArrayElements(item, subscript, results);
            }
        }
    }

    private Object visitDotWildcard(Object current) {
        if (current instanceof JSONObject) {
            JSONObject jsonObj = (JSONObject) current;
            return new ArrayList<>(jsonObj.toMap().values());
        } else if (current instanceof List) {
            return ((List<?>) current);
        }
        return null;
    }

    private Object visitRecursiveField(JQuickJSONPathParser.IdentifierContext ctx, Object current) {
        String fieldName = ctx.getText();
        List<Object> results = new ArrayList<>();
        collectFieldValuesRecursively(current, fieldName, results);
        return results.isEmpty() ? null : results;
    }

    private void collectFieldValuesRecursively(Object node, String fieldName, List<Object> results) {
        if (node == null) {
            return;
        }
        if (node instanceof JSONObject) {
            JSONObject jsonObj = (JSONObject) node;
            if (jsonObj.has(fieldName)) {
                results.add(jsonObj.get(fieldName));
            }
            for (Object value : jsonObj.keySet()) {
                collectFieldValuesRecursively(jsonObj.get(value), fieldName, results);
            }
        } else if (node instanceof List) {
            for (Object item : (List<?>) node) {
                collectFieldValuesRecursively(item, fieldName, results);
            }
        }
    }

}
