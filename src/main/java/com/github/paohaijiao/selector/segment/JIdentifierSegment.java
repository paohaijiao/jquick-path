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
package com.github.paohaijiao.selector.segment;

/**
 * packageName com.github.paohaijiao.selector.segment
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/10
 */
public class JIdentifierSegment implements JSegment{

    private final String identifier;
    private final boolean wildcard;

    private JIdentifierSegment(String identifier, boolean wildcard) {
        this.identifier = identifier;
        this.wildcard = wildcard;
    }

    public static JIdentifierSegment of(String identifier) {
        return new JIdentifierSegment(identifier, false);
    }

    public static JIdentifierSegment wildcard() {
        return new JIdentifierSegment(null, true);
    }

    @Override
    public String toJSONPathExpression() {
        return "." + (wildcard ? "*" : identifier);
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean isWildcard() {
        return wildcard;
    }

}
