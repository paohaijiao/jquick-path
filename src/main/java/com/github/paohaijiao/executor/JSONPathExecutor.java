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
package com.github.paohaijiao.executor;

import com.github.paohaijiao.antlr.impl.JAbstractAntlrExecutor;
import com.github.paohaijiao.exception.JAntlrExecutionException;
import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.parser.JQuickJSONPathLexer;
import com.github.paohaijiao.parser.JQuickJSONPathParser;
import com.github.paohaijiao.visitor.JSONPathCommonVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;

public class JSONPathExecutor extends JAbstractAntlrExecutor<String, JSONPathResult> {
    private Object json = null;

    public JSONPathExecutor(Object json) {
        this.json = json;
    }

    protected Lexer createLexer(CharStream input) {
        return new JQuickJSONPathLexer(input);
    }

    @Override
    protected Parser createParser(TokenStream tokens) {
        return new JQuickJSONPathParser(tokens);
    }

    @Override
    protected JSONPathResult parse(Parser parser) throws JAntlrExecutionException {
        JQuickJSONPathParser calcParser = (JQuickJSONPathParser) parser;
        JQuickJSONPathParser.PathContext tree = calcParser.path();
        JSONPathCommonVisitor visitor = new JSONPathCommonVisitor(json);
        JSONPathResult result= visitor.visitPath(tree);
        return result;
    }
}
