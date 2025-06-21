package com.github.paohaijiao.visitor;

import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.parser.JQuickJSONPathParser;

public class JRootAndPathVisitor extends JSegmentVisitor {



    @Override
    public JSONPathResult visitPath(JQuickJSONPathParser.PathContext ctx) {
        Object obj=null;
        if (null != ctx.root()) {
            obj = visitRoot(ctx.root());
        }
       
        for (JQuickJSONPathParser.SegmentContext segment : ctx.segment()) {
            obj=visit(segment);
        }
        JSONPathResult jsonPathResult = new JSONPathResult(obj);
        return jsonPathResult;
    }

    @Override
    public Object visitRoot(JQuickJSONPathParser.RootContext ctx) {
        if (ctx.getText().equals("$")) {
            return this.rootJsonObject;
        } else {
            return currentJsonObject;
        }
    }


}
