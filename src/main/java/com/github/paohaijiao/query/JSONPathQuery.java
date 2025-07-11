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
package com.github.paohaijiao.query;

import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.query.impl.JSortBuilder;
import com.github.paohaijiao.selector.root.JPath;

public interface JSONPathQuery <T>{

    JSONPathQuery<T> document(JPath jSubscript);


    JSONPathResult execute();

    JSONPathQuery<T> limit(int limit);

    public JSONPathQuery<T> sort(JSortBuilder<T> sortBuilder);

    public JSONPathQuery<T> as( Class<T> type);

    public JSONPathQuery<T> asList( Class<T> clazz);


    JSONPathQuery<T> skip(int skip);
}
