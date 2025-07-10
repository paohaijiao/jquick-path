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
