package com.paohaijiao.javelin.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JScriptContext {
    private final Map<String, Object> variables = new HashMap<>();
    private final Map<String, Function<Object[], Object>> functions = new HashMap<>();

    public void addVariable(String name, Object value) {
        variables.put(name, value);
    }

    public void addFunction(String name, Function<Object[], Object> function) {
        functions.put(name, function);
    }

    public Object getVariable(String name) {
        return variables.get(name);
    }

    public Object callFunction(String name, Object[] args) {
        Function<Object[], Object> function = functions.get(name);
        if (function != null) {
            return function.apply(args);
        }
        throw new RuntimeException("Function not found: " + name);
    }
}
