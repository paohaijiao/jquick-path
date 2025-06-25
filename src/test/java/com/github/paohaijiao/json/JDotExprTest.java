package com.github.paohaijiao.json;/*
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

import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.model.JTypeModel;
import com.github.paohaijiao.parser.JQuickJSONPathLexer;
import com.github.paohaijiao.parser.JQuickJSONPathParser;
import com.github.paohaijiao.visitor.JSONPathCommonVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.IOException;

/**
 * packageName PACKAGE_NAME
 *
 * @author Martin
 * @version 1.0.0
 * @className JpathTest
 * @date 2025/6/15
 * @description
 */
public class JDotExprTest {

    @Test
    public void rightDotExpr1() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("*"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.RightDotExprContext tree = parser.rightDotExpr();
        JSONObject obj = new JSONObject();
        obj.put("value", "1");
        obj.put("type", "string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object = tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void rightDotExpr2() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("xsaxasxas"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.RightDotExprContext tree = parser.rightDotExpr();
        JSONObject obj = new JSONObject();
        obj.put("value", "1");
        obj.put("type", "string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object = tv.visit(tree);
        System.out.println(object);
    }

    @Test
    public void leftDotExpr1() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("value"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LeftDotExprContext tree = parser.leftDotExpr();
        JSONObject obj = new JSONObject();
        obj.put("value", "1");
        obj.put("type", "string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object = tv.visit(tree);
        System.out.println(object);
    }

    @Test
    public void leftDotExpr2() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("$"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LeftDotExprContext tree = parser.leftDotExpr();
        JTypeModel obj = new JTypeModel();
        obj.setValue("1");
        obj.setType("string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object = tv.visit(tree);
        System.out.println(object);
    }

    @Test
    public void leftDotExpr3() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("@"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LeftDotExprContext tree = parser.leftDotExpr();
        JTypeModel obj = new JTypeModel();
        obj.setValue("1");
        obj.setType("string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object = tv.visit(tree);
        System.out.println(object);
    }

    @Test
    public void leftDotExpr4() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("1"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LeftDotExprContext tree = parser.leftDotExpr();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(null);
        Object object = tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void leftDotExpr5() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("'xasxsaxas'"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LeftDotExprContext tree = parser.leftDotExpr();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(null);
        Object object = tv.visit(tree);
        System.out.println(object);
    }

    @Test
    public void leftDotExpr6() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("true"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LeftDotExprContext tree = parser.leftDotExpr();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(null);
        Object object = tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void leftDotExpr7() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("null"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LeftDotExprContext tree = parser.leftDotExpr();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(null);
        Object object = tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void identifier() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("xxaxsa"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.IdentifierContext tree = parser.identifier();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(null);
        Object object = tv.visit(tree);
        System.out.println(object);
    }


    @Test
    public void directDotExpression() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("$.type"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.DotExprContext tree = parser.dotExpr();
        JTypeModel obj = new JTypeModel();
        obj.setValue("1");
        obj.setType("string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object = tv.visit(tree);
        System.out.println(object);
    }

    @Test
    public void directDotExpression1() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("$.*"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.DotExprContext tree = parser.dotExpr();
        JTypeModel obj = new JTypeModel();
        obj.setValue("1");
        obj.setType("string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object = tv.visit(tree);
        System.out.println(object);
    }

    @Test
    public void directDotExpression2() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("$.value.type"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.DotExprContext tree = parser.dotExpr();
        JSONObject a = new JSONObject();
        a.put("type", "string");
        JSONObject obj = new JSONObject();
        obj.put("key", "1");
        obj.put("value", a);
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object = tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void directDotExpression3() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("@.type"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.DotExprContext tree = parser.dotExpr();
        JTypeModel obj = new JTypeModel();
        obj.setValue("1");
        obj.setType("string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object = tv.visit(tree);
        System.out.println(object);
    }

    @Test
    public void directDotExpression4() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("@.*"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.DotExprContext tree = parser.dotExpr();
        JTypeModel obj = new JTypeModel();
        obj.setValue("1");
        obj.setType("string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object = tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void directDotExpression5() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("null.a"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.DotExprContext tree = parser.dotExpr();
        JTypeModel obj = new JTypeModel();
        obj.setValue("1");
        obj.setType("string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object = tv.visit(tree);
        System.out.println(object);
    }

}
