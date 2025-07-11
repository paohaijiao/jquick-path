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
package com.github.paohaijiao.support;

import com.github.paohaijiao.model.JSortConditionModel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JSONArraySorter {



    public static <T, U extends Comparable<? super U>> List<T> dynamicSort(List<T> list, List<JSortConditionModel<T, U>> conditions) {
        if (conditions == null || conditions.isEmpty()) {
            return list;
        }
        Comparator<T> comparator = buildComparator(conditions);
        return list.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private static <T, U extends Comparable<? super U>> Comparator<T> buildComparator(
            List<JSortConditionModel<T, U>> conditions) {
        JSortConditionModel<T, U> firstCondition = conditions.get(0);
        Comparator<T> comparator = createComparator(firstCondition);
        for (int i = 1; i < conditions.size(); i++) {
            comparator = comparator.thenComparing(createComparator(conditions.get(i)));
        }

        return comparator;
    }

    private static <T, U extends Comparable<? super U>> Comparator<T> createComparator(
            JSortConditionModel<T, U> condition) {
        Comparator<T> cmp = Comparator.comparing(
                condition.getKeyExtractor(),
                Comparator.nullsFirst(Comparator.naturalOrder()));

        return condition.isAscending() ? cmp : cmp.reversed();
    }
}
