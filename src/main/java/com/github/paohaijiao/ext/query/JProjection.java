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
