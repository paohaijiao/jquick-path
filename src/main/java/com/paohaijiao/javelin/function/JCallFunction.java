package com.paohaijiao.javelin.function;

import com.paohaijiao.javelin.exception.JAssert;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class JCallFunction {
    public Object callFunction(String functionName, Object[] args) {
        switch (functionName.toLowerCase()) {
            case "length":
                return handleLengthFunction(args);
            case "sum":
                return handleSumFunction(args);
            case "avg":
//            case "average":
//                return handleAverageFunction(args);
//            case "min":
//                return handleMinFunction(args);
//            case "max":
//                return handleMaxFunction(args);
//            case "concat":
//                return handleConcatFunction(args);
//            case "substring":
//                return handleSubstringFunction(args);
//            case "tolower":
//                return handleToLowerFunction(args);
//            case "toupper":
//                return handleToUpperFunction(args);
            case "matches":
                return handleMatchesFunction(args);
        }

//        if (scriptContext != null) {
//            try {
//                return scriptContext.invokeFunction(functionName, args);
//            } catch (RuntimeException e) {
//                throw new EvaluationException("Function call failed: " + functionName, e);
//            }
//        }
        JAssert.throwNewException("Unknown function: " + functionName);
        return null;
    }
    private Object handleLengthFunction(Object[] args) {
        if (args.length != 1) {
            JAssert.throwNewException("length() expects exactly 1 argument");
        }

        Object arg = args[0];
        if (arg == null) return 0;

        if (arg instanceof Collection) {
            return ((Collection<?>) arg).size();
        } else if (arg instanceof Map) {
            return ((Map<?, ?>) arg).size();
        } else if (arg instanceof String) {
            return ((String) arg).length();
        } else if (arg.getClass().isArray()) {
            return Array.getLength(arg);
        }
        JAssert.throwNewException("length() cannot be applied to " + arg.getClass());
        return null;
    }
    private Object handleSumFunction(Object[] args) {
        if (args.length == 0) {
            JAssert.throwNewException("sum() requires at least 1 argument");
        }
        double sum = 0;
        for (Object arg : args) {
            if (arg instanceof Number) {
                sum += ((Number) arg).doubleValue();
            } else if (arg instanceof Collection) {
                for (Object item : (Collection<?>) arg) {
                    if (item instanceof Number) {
                        sum += ((Number) item).doubleValue();
                    }
                }
            }
        }
        return sum;
    }
    private Object handleMatchesFunction(Object[] args) {
        if (args.length != 2) {
            JAssert.throwNewException("matches() requires exactly 2 arguments");
        }
        String input = args[0].toString();
        String regex = args[1].toString();
        try {
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(input).matches();
        } catch (PatternSyntaxException e) {
            JAssert.throwNewException("Invalid regex pattern: " + regex);
        }
        return null;
    }
//    private Object callFunction(String functionName, Object[] args) {
//        if (args.length > 0 && args[0] != null) {
//            try {// try with java invoke
//                Class<?> clazz = args[0].getClass();
//                Method method = findMethod(clazz, functionName, args);
//                if (method != null) {
//                    return method.invoke(args[0], Arrays.copyOfRange(args, 1, args.length));
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return null;
//    }
    private Method findMethod(Class<?> clazz, String name, Object[] args) {
        for (Method method : clazz.getMethods()) {
            if (method.getName().equalsIgnoreCase(name) &&
                    isArgsCompatible(method, args)) {
                return method;
            }
        }
        return null;
    }

    private boolean isArgsCompatible(Method method, Object[] args) {
        return method.getParameterCount() == (args.length - 1);
    }
}
