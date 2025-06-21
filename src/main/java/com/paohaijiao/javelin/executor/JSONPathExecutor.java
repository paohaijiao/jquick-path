package com.paohaijiao.javelin.executor;

import com.paohaijiao.javelin.antlr.impl.JAbstractAntlrExecutor;
import com.paohaijiao.javelin.model.JSONPathResult;
import com.paohaijiao.javelin.exception.JAntlrExecutionException;
import com.paohaijiao.javelin.parser.JQuickJSONPathLexer;
import com.paohaijiao.javelin.parser.JQuickJSONPathParser;
import com.paohaijiao.javelin.visitor.JSONPathCommonVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;

public class JSONPathExecutor extends JAbstractAntlrExecutor<String, JSONPathResult> {
    private Object json=null;
    public JSONPathExecutor(Object json) {
        this.json=json;
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
        return  visitor.visitPath(tree);
    }
}
