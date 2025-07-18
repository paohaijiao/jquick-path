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
package com.github.paohaijiao.selector.subscript;

/**
 * packageName com.github.paohaijiao.selector.subscript
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/10
 */
public class JIndexSubscript implements JSubscript{

    private final int index;

    public JIndexSubscript(int index) {
        this.index = index;
    }

    public static JIndexSubscript of(int index) {
        return new JIndexSubscript(index);
    }

    @Override
    public String toJSONPathExpression() {
        return String.valueOf(index);
    }

    public int getIndex() {
        return index;
    }
}
