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
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
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
public class JExprTest {


    @Test
    public void testNetestDotExpr() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("$.value.type"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.ExprContext tree = parser.expr();
        JSONObject a = new JSONObject();
        a.put("type", "string");
        JSONObject obj = new JSONObject();
        obj.put("key","1");
        obj.put("value",a);
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void bracketExpression() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("$.value.list[3]"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.ExprContext tree = parser.expr();
        JSONObject a = new JSONObject();
        a.put("type", "string");
        a.put("list", Arrays.asList(1,"2",3,4,5,6,7));
        JSONObject obj = new JSONObject();
        obj.put("key","1");
        obj.put("value",a);
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(obj);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void functionCallExpression() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("contains(@.name, 'Book')"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.ExprContext tree = parser.expr();
        JSONObject a = new JSONObject();
        a.put("name", "Book");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(a);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void notExpression() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("!true"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.ExprContext tree = parser.expr();
        JSONObject a = new JSONObject();
        a.put("name", "Book");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(a);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void multiplicativeExpression() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("19%7"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.ExprContext tree = parser.expr();
        JSONObject a = new JSONObject();
        a.put("name", "Book");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(a);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void additiveExpression() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("1-2"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.ExprContext tree = parser.expr();
        JSONObject a = new JSONObject();
        a.put("name", "Book");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(a);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void comparisonExpression() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("55<=4"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.ExprContext tree = parser.expr();
        JSONObject a = new JSONObject();
        a.put("name", "Book");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(a);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void equalityExpression() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("55!=55"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.ExprContext tree = parser.expr();
        JSONObject a = new JSONObject();
        a.put("name", "Book");
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(a);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void inExpression() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("$.list in ('cherry','banana')"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.ExprContext tree = parser.expr();
        JSONObject a = new JSONObject();
        a.put("name", "Book");
        a.put("list", Arrays.asList("apple", "banana", "cherry"));
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(a);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void logicalAndExpression() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("true && false"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.ExprContext tree = parser.expr();
        JSONObject a = new JSONObject();
        a.put("name", "Book");
        a.put("list", Arrays.asList("apple", "banana", "cherry"));
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(a);
        Object object= tv.visit(tree);
        System.out.println(object);
    }
    @Test
    public void logicalOrExpression() throws IOException {
        JQuickJSONPathLexer lexer = new JQuickJSONPathLexer(CharStreams.fromString("false || false"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONPathParser parser = new JQuickJSONPathParser(tokens);
        JQuickJSONPathParser.ExprContext tree = parser.expr();
        JSONObject a = new JSONObject();
        a.put("name", "Book");
        a.put("list", Arrays.asList("apple", "banana", "cherry"));
        JSONPathCommonVisitor tv = new JSONPathCommonVisitor(a);
        Object object= tv.visit(tree);
        System.out.println(object);
    }












}
