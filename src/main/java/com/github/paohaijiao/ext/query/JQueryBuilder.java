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
