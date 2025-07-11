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
package com.github.paohaijiao.model;

import java.util.Collections;
import java.util.List;

public class JSONPathResult {

    private Object data;

    private boolean isList;

    public JSONPathResult(Object data) {

        if (data instanceof List) {
            this.data = data;
            this.isList = true;
        } else {
            this.data = data;
            this.isList = false;
        }
    }

    public List<Object> getAsList() {
        return isList ? (List<Object>) data : Collections.singletonList(data);
    }

    public Object getRawData() {
        return data;
    }

    public boolean isList() {
        return isList;
    }
}
