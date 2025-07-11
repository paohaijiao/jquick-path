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

import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JQueryBuilder {
    private String path;
    private Predicate<Object> filter;
    private JProjection projection;
    private JSorting sorting;
    private int page = -1;
    private int size = -1;

    public JQueryBuilder path(String path) {
        this.path = path;
        return this;
    }

    public JQueryBuilder filter(Predicate<Object> filter) {
        this.filter = filter;
        return this;
    }

    public JQueryBuilder project(JProjection projection) {
        this.projection = projection;
        return this;
    }

    public JQueryBuilder sort(JSorting sorting) {
        this.sorting = sorting;
        return this;
    }

    public JQueryBuilder paginate(int page, int size) {
        this.page = page;
        this.size = size;
        return this;
    }

    public JSONArray execute(JSONObject root) {
        Object target = root.get(path);
        if (!(target instanceof JSONArray)) {
            throw new IllegalArgumentException("Path does not point to a JSONArray");
        }
        JSONArray array = (JSONArray) target;
        List<Object> result = new ArrayList<>();
        for (Object item : array) {
            if (filter == null || filter.test(item)) {
                result.add(item);
            }
        }
        if (projection != null) {
            result = result.stream()
                    .filter(obj -> obj instanceof JSONObject)
                    .map(obj -> projection.apply((JSONObject) obj))
                    .collect(Collectors.toList());
        }
        if (sorting != null) {
            List<JSONObject> sortable = result.stream()
                    .filter(obj -> obj instanceof JSONObject)
                    .map(obj -> (JSONObject) obj)
                    .collect(Collectors.toList());

            sorting.apply(sortable);
            result = new ArrayList<>(sortable);
        }

        if (page >= 0 && size > 0) {
            int fromIndex = page * size;
            if (fromIndex < result.size()) {
                int toIndex = Math.min(fromIndex + size, result.size());
                result = result.subList(fromIndex, toIndex);
            } else {
                result = new ArrayList<>();
            }
        }

        return new JSONArray(result);
    }
}
