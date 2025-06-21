package com.paohaijiao.javelin.visitor;

import com.paohaijiao.javelin.model.JSONObject;
import com.paohaijiao.javelin.param.JContext;
import com.paohaijiao.javelin.parser.JQuickJSONPathBaseVisitor;
import com.paohaijiao.javelin.util.JReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

public class JSONPathCoreVisitor extends JQuickJSONPathBaseVisitor<Object> {

    protected  Object rootJsonObject;
    protected Object currentJsonObject;
    protected JContext context;

    private Deque<Map<String, Object>> variableStack = new ArrayDeque<>();
    protected void setRoot(Object object){
        this.rootJsonObject = object;
    }
    protected void setCurrent(Object object){
        this.currentJsonObject = object;
    }
    protected Object getCurrent(){
        return this.currentJsonObject;
    }
    protected Object getRoot(){
        return this.rootJsonObject;
    }
    protected   List<?> getList(Object current){
        List<?> list = (List<?>) current;
        return list;
    }
    protected static <T> List<T> slice(List<T> list, Integer start, Integer end, Integer step) {
        if (list == null) return Collections.emptyList();
        int size = list.size();
        int actualStart = start != null ? (start >= 0 ? start : size + start) : 0;
        int actualEnd = end != null ? (end >= 0 ? end : size + end) : size-1;
        int actualStep = step != null ? step : 1;
        if (actualStep == 0) throw new IllegalArgumentException("Step cannot be zero");
        if (actualStart < 0) actualStart = 0;
        if (actualEnd > size) actualEnd = size;
        List<T> result = new ArrayList<>();
        if (actualStep > 0) {
            for (int i = actualStart; i <= actualEnd; i += actualStep) {
                result.add(list.get(i));
            }
        } else {
            for (int i = actualStart; i > actualEnd; i += actualStep) {
                result.add(list.get(i));
            }
        }
        return result;
    }
    public Object getValue(Object target, Object obj){
        if(target == null){
            return null;
        }
        if(obj instanceof String){
            Object value= getValueByKey(target, (String)obj);
            return value;
        }else if(obj instanceof Integer){
            Object value= getValueByIndex(target, (Integer)obj);
            return value;
        }
        return null;

    }
    public Object getValueByKey(Object obj, String fieldName){
        if (obj instanceof JSONObject) {
            JSONObject jsonObj = (JSONObject) obj;
            return jsonObj.has(fieldName) ? jsonObj.get(fieldName) : null;
        } else if (obj instanceof List) {
            List<Object> results = new ArrayList<>();
            for (Object item : (List<?>) obj) {
                if (item instanceof JSONObject) {
                    JSONObject jsonObj = (JSONObject) item;
                    if (jsonObj.has(fieldName)) {
                        results.add(jsonObj.get(fieldName));
                    }
                }
            }
            return results.isEmpty() ? null : results;
        }else if(obj instanceof Map){
            return ((Map<?, ?>) obj).get(fieldName);
        }else if (obj instanceof Object) {
            Object target= JReflectionUtils.getFieldValue(obj,fieldName);
            return target;
        }
        return null;
    }
    public Object getValueByIndex(Object obj, Integer index){
        if (obj instanceof JSONObject) {
          //  JSONObject jsonObj = (JSONObject) obj;
           // return jsonObj.has(fieldName) ? jsonObj.get(fieldName) : null;
        } else if (obj instanceof List) {
            List<?> list = (List<?>) obj;
            return list.get(index);
        }
        return null;
    }

    protected boolean isTruthy(Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue() != 0;
        }
        if (value instanceof String) {
            return !((String) value).isEmpty();
        }
        if (value instanceof Collection) {
            return !((Collection<?>) value).isEmpty();
        }
        if (value instanceof Map) {
            return !((Map<?, ?>) value).isEmpty();
        }
        return true;
    }
    protected Object evaluateBinaryOperation(Object left, Object right, String operator) {
        if (left == null || right == null) {
            return handleNullComparison(left, right, operator);
        }
        if (left instanceof Number && right instanceof Number) {
            double leftNum = ((Number) left).doubleValue();
            double rightNum = ((Number) right).doubleValue();
            switch (operator) {
                case "<": return leftNum < rightNum;
                case ">": return leftNum > rightNum;
                case "<=": return leftNum <= rightNum;
                case ">=": return leftNum >= rightNum;
                case "==": return leftNum == rightNum;
                case "!=": return leftNum != rightNum;
            }
        }
        if (left instanceof String && right instanceof String) {
            int comparison = ((String) left).compareTo((String) right);
            switch (operator) {
                case "<": return comparison < 0;
                case ">": return comparison > 0;
                case "<=": return comparison <= 0;
                case ">=": return comparison >= 0;
                case "==": return comparison == 0;
                case "!=": return comparison != 0;
            }
        }
        if (operator.equals("&&")) {
            return isTruthy(left) && isTruthy(right);
        }
        if (operator.equals("||")) {
            return isTruthy(left) || isTruthy(right);
        }
        //regex match
        if (operator.equals("=~") && left instanceof String && right instanceof String) {
            String pattern = ((String) right).replaceAll("^/|/$", "");
            return ((String) left).matches(pattern);
        }
        if (operator.equals("in") && right instanceof List) {
            return ((List<?>) right).contains(left);
        }
        if (left instanceof Number && right instanceof Number) {
            double leftNum = ((Number) left).doubleValue();
            double rightNum = ((Number) right).doubleValue();
            switch (operator) {
                case "+": return leftNum + rightNum;
                case "-": return leftNum - rightNum;
                case "*": return leftNum * rightNum;
                case "/": return leftNum / rightNum;
                case "%": return leftNum % rightNum;
            }
        }
        return false;
    }
    private Object handleNullComparison(Object left, Object right, String operator) {
        if (operator.equals("==")) {
            return left == null && right == null;
        }
        if (operator.equals("!=")) {
            return left != null || right != null;
        }
        return false;
    }
    protected Object evaluateUnaryOperation(Object operand, String operator) {
        if (operand == null) {
            return false;
        }

        if (operator.equals("!")) {
            return !isTruthy(operand);
        }
        if (operator.equals("-") && operand instanceof Number) {
            return -((Number) operand).doubleValue();
        }

        return false;
    }




    protected Object visitWildcard(Object current) {
        if (current == null) {
            return null;
        }
        if (current instanceof JSONObject) {
            JSONObject jsonObj = (JSONObject) current;
            return new ArrayList<>(jsonObj.toMap().values());
        }
        else if (current instanceof List) {
            return current;
        }
        else if (current instanceof Map) {
            return new ArrayList<>(((Map<?, ?>) current).values());
        }else if( current instanceof Object) {
            return current;
        }
        return Collections.emptyList();
    }
    protected Object getProperty(Object obj, String property) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).get(property);
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).get(property);
        }
        try {
            Field field = obj.getClass().getField(property);
            return field.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

}
