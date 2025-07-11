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
package com.github.paohaijiao.support;

import com.github.paohaijiao.ext.template.JTemplateEngine;
import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;

public class JSONObjectSupport {
    public Object evaluateTemplate(String template,JSONObject object) {
        JTemplateEngine engine = new JTemplateEngine(object);
        return engine.evaluate(template);
    }

    public String evaluateTemplateAsString(String template,JSONObject object) {
        JTemplateEngine engine = new JTemplateEngine(object);
        return engine.evaluateAsString(template);
    }

    public JSONObject evaluateTemplateAsJson(String template,JSONObject object) {
        JTemplateEngine engine = new JTemplateEngine(object);
        return engine.evaluateAsJson(template);
    }

    public JSONArray evaluateTemplateAsArray(String template,JSONObject object) {
        JTemplateEngine engine = new JTemplateEngine(object);
        return engine.evaluateAsArray(template);
    }
}
