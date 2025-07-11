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
package com.github.paohaijiao.visitor;

import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.parser.JQuickJSONPathParser;

public class JRootAndPathVisitor extends JSegmentVisitor {


    @Override
    public JSONPathResult visitPath(JQuickJSONPathParser.PathContext ctx) {
        Object obj = null;
        if (null != ctx.root()) {
            obj = visitRoot(ctx.root());
            this.currentJsonObject=obj;
        }
        for (JQuickJSONPathParser.SegmentContext segment : ctx.segment()) {
            obj = visit(segment);
            this.currentJsonObject=obj;
        }
        JSONPathResult jsonPathResult = new JSONPathResult(this.currentJsonObject);
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
