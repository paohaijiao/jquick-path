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

import com.paohaijiao.javelin.model.JTypeModel;
import com.paohaijiao.javelin.model.JSONObject;
import com.paohaijiao.javelin.parser.JQuickJSONPathLexer;
import com.paohaijiao.javelin.parser.JQuickJSONPathParser;
import com.paohaijiao.javelin.visitor.JSONPathCommonVisitor;
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
    public void testValue01() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("*"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.RightDotExprContext tree = parser.rightDotExpr();
        JSONObject obj = new JSONObject();
        obj.put("value", "1");
        obj.put("type", "string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void testValue02() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("value"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LeftDotExprContext tree = parser.leftDotExpr();
        JSONObject obj = new JSONObject();
        obj.put("value", "1");
        obj.put("type", "string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void testValue03() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("value"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LeftDotExprContext tree = parser.leftDotExpr();
        JTypeModel obj = new JTypeModel();
        obj.setValue("1");
        obj.setType("string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void testValue04() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("@"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LeftDotExprContext tree = parser.leftDotExpr();
        JTypeModel obj = new JTypeModel();
        obj.setValue("1");
        obj.setType("string");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void testValue05() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("1"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LeftDotExprContext tree = parser.leftDotExpr();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(null);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void testValue06() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("\"dssdas\""));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LeftDotExprContext tree = parser.leftDotExpr();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(null);
        Object object= tv.visit(tree);
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
        Object object= tv.visit(tree);
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
        Object object= tv.visit(tree);
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
        obj.put("key","1");
        obj.put("value",a);
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object= tv.visit(tree);
        System.out.println(object);
    }





}
