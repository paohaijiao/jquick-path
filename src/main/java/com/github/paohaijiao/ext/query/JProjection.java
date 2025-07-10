package com.github.paohaijiao.ext.query;

import com.github.paohaijiao.model.JSONObject;

import java.util.*;

public class JProjection {

    private final List<String> includes;
    private final List<String> excludes;
    private final Map<String, String> aliases;

    private JProjection(List<String> includes, List<String> excludes, Map<String, String> aliases) {
        this.includes = includes;
        this.excludes = excludes;
        this.aliases = aliases;
    }

    public JSONObject apply(JSONObject obj) {
        if (obj == null) return null;

        JSONObject result = new JSONObject();

        // Handle includes
        if (!includes.isEmpty()) {
            for (String field : includes) {
                if (obj.containsKey(field)) {
                    String alias = aliases.getOrDefault(field, field);
                    result.put(alias, obj.get(field));
                }
            }
        } else {
            // Include all fields except excluded ones
            for (Map.Entry<String, Object> entry : obj.entrySet()) {
                if (!excludes.contains(entry.getKey())) {
                    String alias = aliases.getOrDefault(entry.getKey(), entry.getKey());
                    result.put(alias, entry.getValue());
                }
            }
        }

        return result;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<String> includes = new ArrayList<>();
        private List<String> excludes = new ArrayList<>();
        private Map<String, String> aliases = new HashMap<>();

        public Builder include(String... fields) {
            includes.addAll(Arrays.asList(fields));
            return this;
        }

        public Builder exclude(String... fields) {
            excludes.addAll(Arrays.asList(fields));
            return this;
        }

        public Builder alias(String original, String alias) {
            aliases.put(original, alias);
            return this;
        }

        public JProjection build() {
            return new JProjection(includes, excludes, aliases);
        }
    }
}
