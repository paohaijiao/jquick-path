package com.github.paohaijiao.support;

import cn.hutool.extra.template.TemplateEngine;
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
