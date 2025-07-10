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

import com.github.paohaijiao.function.JPredicate;

/**
 * packageName com.github.paohaijiao.selector.subscript
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/10
 */
public class JSubscripts {

    private JSubscripts() {

    }
    public static JIndexSubscript index(int index) {
        return JIndexSubscript.of(index);
    }

    public static JSliceSubscript slice(Integer start, Integer end) {
        return JSliceSubscript.of(start, end);
    }

    public static JSliceSubscript slice(Integer start, Integer end, Integer step) {
        return JSliceSubscript.of(start, end, step);
    }

    public static <T> JFilterSubscript filter(JPredicate<T> predicate) {
        return JFilterSubscript.of(predicate);
    }

    public static JWildcardSubscript wildcard() {
        return JWildcardSubscript.getInstance();
    }

    public static JPropertySubscript property(String property) {
        return JPropertySubscript.of(property);
    }

    public static JSubscript expr(String expression) {
        return new JSubscript() {
            @Override
            public String toJSONPathExpression() {
                return expression;
            }
        };
    }
}
