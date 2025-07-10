package com.github.paohaijiao.support;

import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JSONArraySorter {

    public static JSONArray sort(JSONArray jsonArray, String key, boolean ascending) {
        List<JSONObject> jsonList = IntStream.range(0, jsonArray.size()).mapToObj(jsonArray::getJSONObject).collect(Collectors.toList());
        Comparator<JSONObject> comparator = (o1, o2) -> {
            Object val1 = o1.get(key);
            Object val2 = o2.get(key);
            if (val1 instanceof Number && val2 instanceof Number) {
                return Double.compare(((Number) val1).doubleValue(), ((Number) val2).doubleValue());
            } else if (val1 instanceof String && val2 instanceof String) {
                return ((String) val1).compareTo((String) val2);
            } else if (val1 instanceof Boolean && val2 instanceof Boolean) {
                return ((Boolean) val1).compareTo((Boolean) val2);
            } else {
                return val1.toString().compareTo(val2.toString());
            }
        };
        if (!ascending) {
            comparator = comparator.reversed();
        }
        jsonList.sort(comparator);
        JSONArray sortedArray = new JSONArray();
        jsonList.forEach(sortedArray::add);
        return sortedArray;
    }

    public static JSONArray sortJSONArray(JSONArray jsonArray, String key) {
        return sort(jsonArray, key, true);
    }

    public static JSONArray sortJSONArray(JSONArray jsonArray, String key, boolean ascending) {
        return sort(jsonArray, key, ascending);
    }
}
