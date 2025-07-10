package com.github.paohaijiao.context;


import com.github.paohaijiao.executor.JSONPathExecutor;
import com.github.paohaijiao.model.JSONPathResult;


public abstract  class JValueContext <T> {

    protected Object jsonData;

    protected Class<T> targetType;

    protected JValueContext(Object jsonData, Class<T> targetType) {
        this.jsonData = normalizeInput(jsonData);
        this.targetType = targetType;
    }

    public abstract <R> JValueContext<R> as(Class<R> targetType);

    public abstract T query(String jsonPath);

    protected abstract Object normalizeInput(Object input);

    protected abstract <R> R convertResult(Object result, Class<R> targetType);

    public final JSONPathResult executeQuery(String jsonPath) {
        JSONPathExecutor executor = new JSONPathExecutor(jsonData);
        executor.addErrorListener(error -> {
            System.err.printf("错误: 行%d:%d - %s%n", error.getLine(), error.getCharPosition(), error.getMessage());
            System.err.println("规则栈: " + error.getRuleStack());
        });
        JSONPathResult result = executor.execute(jsonPath);
        return result;

    }

    protected abstract <R> R convertDefault(Object result, Class<R> targetType);
}
