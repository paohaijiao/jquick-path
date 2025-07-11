/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
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
