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
package com.github.paohaijiao.query.impl;

import java.util.ArrayList;
import java.util.List;

public class JProjectionBuilder <T>{

    private final List<String> projections = new ArrayList<>();

    private final Class<T> type;

    public JProjectionBuilder(Class<T> type) {
        this.type = type;
    }

    public JProjectionBuilder<T> field(String fieldName) {
        projections.add(fieldName);
        return this;
    }

    public JProjectionBuilder<T> fields(String... fieldNames) {
        for (String fieldName : fieldNames) {
            projections.add(fieldName);
        }
        return this;
    }

    public JProjectionBuilder<T> exclude(String... fieldNames) {

        return this;
    }

    public String build() {
        if (projections.isEmpty()) {
            return "*";
        }
        return String.join(",", projections);
    }
}
