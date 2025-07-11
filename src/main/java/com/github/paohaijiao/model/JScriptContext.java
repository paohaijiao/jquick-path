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
package com.github.paohaijiao.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JScriptContext {
    private final Map<String, Object> variables = new HashMap<>();
    private final Map<String, Function<Object[], Object>> functions = new HashMap<>();

    public void addVariable(String name, Object value) {
        variables.put(name, value);
    }

    public void addFunction(String name, Function<Object[], Object> function) {
        functions.put(name, function);
    }

    public Object getVariable(String name) {
        return variables.get(name);
    }

    public Object callFunction(String name, Object[] args) {
        Function<Object[], Object> function = functions.get(name);
        if (function != null) {
            return function.apply(args);
        }
        throw new RuntimeException("Function not found: " + name);
    }
}
