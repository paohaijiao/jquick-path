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

import com.paohaijiao.javelin.model.JSONObject;
import com.paohaijiao.javelin.parser.JQuickJSONPathLexer;
import com.paohaijiao.javelin.parser.JQuickJSONPathParser;
import com.paohaijiao.javelin.visitor.JSONPathCommonVisitor;
import org.antlr.v4.runtime.*;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * packageName PACKAGE_NAME
 *
 * @author Martin
 * @version 1.0.0
 * @className JpathTest
 * @date 2025/6/15
 * @description
 */
public class JpathValueTest {

    @Test
    public void testValue01() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("1"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.NumberContext tree = parser.number();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(1);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void testValue02() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("\"csdcds\""));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.StringLiteralContext tree = parser.stringLiteral();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(1);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void testLiteral01() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("\"csdcds\""));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LiteralContext tree = parser.literal();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(1);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void testLiteral02() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("3"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LiteralContext tree = parser.literal();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(1);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void testLiteral03() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("true"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LiteralContext tree = parser.literal();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(1);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void testLiteral04() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("false"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LiteralContext tree = parser.literal();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(1);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void testLiteral05() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("null"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.LiteralContext tree = parser.literal();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(1);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void testLiteral06() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("name"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.IdentifierContext tree = parser.identifier();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","test");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(jsonObject);
        Object object= tv.visit(tree);
        System.out.println(object);
        JSONObject b=new JSONObject();
        b.put("name","test1");
        b.put("city","chengdu");
        JSONPathCommonVisitor tv1 = new JSONPathCommonVisitor(Arrays.asList(jsonObject,b));
        Object object1= tv1.visit(tree);
        System.out.println(object1);
    }
    @Test
    public void testLiteralexprList07() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("1,2,3"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.ExprListContext tree = parser.exprList();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(null);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void testValue08() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("(1,2,3)"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.ValueListContext tree = parser.valueList();
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(null);
        Object object= tv.visit(tree);
        System.out.println(object);
    }





}
