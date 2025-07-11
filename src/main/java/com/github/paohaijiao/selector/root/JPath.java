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
package com.github.paohaijiao.selector.root;

import com.github.paohaijiao.enums.JRoot;
import com.github.paohaijiao.function.JPredicate;
import com.github.paohaijiao.selector.segment.JSegment;
import com.github.paohaijiao.selector.subscript.JSubscripts;
import com.github.paohaijiao.util.JSegments;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.selector.root
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/10
 */
public class JPath {

    private final JRoot root;

    private final List<JSegment> segments;

    private JPath(JRoot root) {
        this.root = root;
        this.segments = new ArrayList<>();
    }

    public static JPath fromRoot(JRoot root) {
        return new JPath(root);
    }

    public static JPath documentRoot() {
        return new JPath(JRoot.ROOT);
    }

    public static JPath currentRoot() {
        return new JPath(JRoot.CURRENT);
    }

    public JPath segment(JSegment segment) {
        this.segments.add(segment);
        return this;
    }

    public JPath segments(JSegment... segments) {
        for (JSegment segment : segments) {
            this.segments.add(segment);
        }
        return this;
    }

    public String toJSONPathExpression() {
        StringBuilder sb = new StringBuilder(root.getSymbol());
        for (JSegment segment : segments) {
            sb.append(segment.toJSONPathExpression());
        }
        return sb.toString();
    }


    @Override
    public String toString() {
        return toJSONPathExpression();
    }

    public JPath property(String name) {
        return segment(JSegments.id(name));
    }

    public JPath wildcard() {
        return segment(JSegments.wildcard());
    }

    public JPath index(int index) {
        return segment(JSegments.subscript(JSubscripts.index(index)));
    }

    public JPath filter(JPredicate<?> predicate) {
        return segment(JSegments.subscript(JSubscripts.filter(predicate)));
    }

    public JPath recursiveProperty(String name) {
        return segment(JSegments.recursiveId(name));
    }

    public JPath recursiveWildcard() {
        return segment(JSegments.recursiveWildcard());
    }
}
