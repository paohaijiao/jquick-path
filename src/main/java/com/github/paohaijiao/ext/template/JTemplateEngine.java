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
package com.github.paohaijiao.ext.template;

import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.param.JContext;

public class JTemplateEngine {
    private final JContext context=new JContext();

    public JTemplateEngine(JSONObject rootObject) {
         rootObject.keySet().forEach(key -> {
             context.put(key, rootObject.get(key));
         });
    }

    public JTemplateEngine addVariable(String name, Object value) {
        context.put(name, value);
        return this;
    }

    public Object evaluate(String template) {
        JExpression expression = JTemplateParser.parse(template, context);
        return expression.evaluate(context);
    }

    public String evaluateAsString(String template) {
        Object result = evaluate(template);
        return result != null ? result.toString() : null;
    }

    public JSONObject evaluateAsJson(String template) {
        Object result = evaluate(template);
        if (result instanceof JSONObject) {
            return (JSONObject) result;
        }
        return null;
    }

    public JSONArray evaluateAsArray(String template) {
        Object result = evaluate(template);
        if (result instanceof JSONArray) {
            return (JSONArray) result;
        }
        return null;
    }
}
