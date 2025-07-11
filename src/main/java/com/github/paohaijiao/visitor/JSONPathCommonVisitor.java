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

import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickJSONPathParser;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class JSONPathCommonVisitor extends JRootAndPathVisitor {

    public JSONPathCommonVisitor(Object root, JContext context) {
        this.rootJsonObject = root;
        this.currentJsonObject = rootJsonObject;
        this.context = context;
    }

    public JSONPathCommonVisitor(Object root) {
        this.rootJsonObject = root;
        this.currentJsonObject = rootJsonObject;
        this.context = new JContext();
    }




    @Override
    public Pattern visitRegexLiteral(JQuickJSONPathParser.RegexLiteralContext ctx) {
        String regexText = ctx.getText();
        int lastSlash = regexText.lastIndexOf('/');
        String pattern = regexText.substring(1, lastSlash);
        String flags = regexText.substring(lastSlash + 1);
        int javaFlags = 0;
        if (!flags.isEmpty()) {
            for (char flag : flags.toCharArray()) {
                switch (flag) {
                    case 'i':
                        javaFlags |= Pattern.CASE_INSENSITIVE;
                        break;
                    case 'm':
                        javaFlags |= Pattern.MULTILINE;
                        break;
                    case 's':
                        javaFlags |= Pattern.DOTALL;
                        break;
                }
            }
        }

        try {
            return Pattern.compile(pattern, javaFlags);
        } catch (PatternSyntaxException e) {
            throw new RuntimeException("Invalid regular expression: " + pattern, e);
        }
    }


}
