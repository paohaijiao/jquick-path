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
public class JFilterSubscript implements JSubscript{

    private final JPredicate<?> predicate;

    public JFilterSubscript(JPredicate<?> predicate) {
        this.predicate = predicate;
    }

    public static <T> JFilterSubscript of(JPredicate<T> predicate) {
        return new JFilterSubscript(predicate);
    }

    @Override
    public String toJSONPathExpression() {
        return "?(" + predicate.toJSONPathExpression() + ")";
    }

    public JPredicate<?> getPredicate() {
        return predicate;
    }
}
