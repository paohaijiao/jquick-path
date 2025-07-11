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
package com.github.paohaijiao.ext.template;

import com.github.paohaijiao.param.JContext;

public class JPlaceholderResolver {
    private static final String PLACEHOLDER_PREFIX = "${";
    private static final String PLACEHOLDER_SUFFIX = "}";

    public static boolean isPlaceholder(String text) {
        return text.startsWith(PLACEHOLDER_PREFIX) && text.endsWith(PLACEHOLDER_SUFFIX);
    }

    public static JExpression resolve(String placeholder, JContext context) {
        if (!isPlaceholder(placeholder)) {
            return null;
        }

        String variableName = placeholder.substring(PLACEHOLDER_PREFIX.length(), placeholder.length() - PLACEHOLDER_SUFFIX.length()).trim();
        return new JVariableExpression(variableName);
    }
}
