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
package com.github.paohaijiao.selector.subscript;

/**
 * packageName com.github.paohaijiao.selector.subscript
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/10
 */
public class JSliceSubscript implements JSubscript {
        private final Integer start;

        private final Integer end;

        private final Integer step;

        public JSliceSubscript(Integer start, Integer end, Integer step) {
            this.start = start;
            this.end = end;
            this.step = step;
        }

        public static JSliceSubscript of(Integer start, Integer end) {
            return new JSliceSubscript(start, end, null);
        }

        public static JSliceSubscript of(Integer start, Integer end, Integer step) {
            return new JSliceSubscript(start, end, step);
        }

        @Override
        public String toJSONPathExpression() {
            StringBuilder sb = new StringBuilder();
            sb.append(start != null ? start : "");
            sb.append(":");
            sb.append(end != null ? end : "");
            if (step != null) {
                sb.append(":").append(step);
            }
            return sb.toString();
        }
}
