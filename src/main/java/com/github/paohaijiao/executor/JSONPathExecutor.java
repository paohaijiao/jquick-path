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
        return visitor.visitPath(tree);
    }
}
