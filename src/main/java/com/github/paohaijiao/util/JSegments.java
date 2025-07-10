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
package com.github.paohaijiao.util;

import com.github.paohaijiao.selector.segment.JIdentifierSegment;
import com.github.paohaijiao.selector.segment.JRecursiveIdentifierSegment;
import com.github.paohaijiao.selector.segment.JRecursiveSubscriptSegment;
import com.github.paohaijiao.selector.segment.JSubscriptSegment;
import com.github.paohaijiao.selector.subscript.JSubscript;

/**
 * packageName com.github.paohaijiao.util
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/10
 */
public class JSegments {
    private JSegments() {}


    public static JIdentifierSegment id(String identifier) {
        return JIdentifierSegment.of(identifier);
    }

    public static JIdentifierSegment wildcard() {
        return JIdentifierSegment.wildcard();
    }

    public static JSubscriptSegment subscript(JSubscript subscript) {
        return JSubscriptSegment.of(subscript);
    }

    public static JRecursiveIdentifierSegment recursiveId(String identifier) {
        return JRecursiveIdentifierSegment.of(identifier);
    }

    public static JRecursiveIdentifierSegment recursiveWildcard() {
        return JRecursiveIdentifierSegment.wildcard();
    }


    public static JRecursiveSubscriptSegment recursiveSubscript(JSubscript subscript) {
        return JRecursiveSubscriptSegment.of(subscript);
    }
}
