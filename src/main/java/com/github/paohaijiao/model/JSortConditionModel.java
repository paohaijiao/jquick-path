package com.github.paohaijiao.model;


import java.util.function.Function;
public class JSortConditionModel<T, U extends Comparable<? super U>> {

    private Function<T, U> keyExtractor;
    private boolean ascending;
    /**
     * 构造函数
     * @param keyExtractor 字段提取函数
     * @param ascending 是否升序
     */
    public JSortConditionModel(Function<T, U> keyExtractor, boolean ascending) {
        this.keyExtractor = keyExtractor;
        this.ascending = ascending;
    }

    /**
     * 获取字段提取函数
     */
    public Function<T, U> getKeyExtractor() {
        return keyExtractor;
    }

    /**
     * 设置字段提取函数
     */
    public void setKeyExtractor(Function<T, U> keyExtractor) {
        this.keyExtractor = keyExtractor;
    }

    /**
     * 是否升序排序
     */
    public boolean isAscending() {
        return ascending;
    }

    /**
     * 设置排序方向
     */
    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
}
