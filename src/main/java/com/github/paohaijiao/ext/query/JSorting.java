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
package com.github.paohaijiao.ext.query;

import com.github.paohaijiao.model.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JSorting {

    private final List<SortCriteria> criteria;

    private JSorting(List<SortCriteria> criteria) {
        this.criteria = criteria;
    }

    public void apply(List<JSONObject> list) {
        if (criteria.isEmpty()) return;

        Comparator<JSONObject> comparator = null;
        for (SortCriteria sc : criteria) {
            Comparator<JSONObject> current = Comparator.comparing(
                    obj -> {
                        Object value = obj.get(sc.getField());
                        if (value instanceof Comparable) {
                            return (Comparable) value;
                        }
                        return null;
                    },
                    Comparator.nullsLast(Comparator.naturalOrder())
            );

            if (!sc.isAscending()) {
                current = current.reversed();
            }
            comparator = comparator == null ? current : comparator.thenComparing(current);
        }

        list.sort(comparator);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<SortCriteria> criteria = new ArrayList<>();

        public Builder by(String field) {
            return by(field, true);
        }

        public Builder by(String field, boolean ascending) {
            criteria.add(new SortCriteria(field, ascending));
            return this;
        }

        public JSorting build() {
            return new JSorting(criteria);
        }
    }

    private static class SortCriteria {
        private final String field;
        private final boolean ascending;

        public SortCriteria(String field, boolean ascending) {
            this.field = field;
            this.ascending = ascending;
        }

        public String getField() {
            return field;
        }

        public boolean isAscending() {
            return ascending;
        }
    }
}
