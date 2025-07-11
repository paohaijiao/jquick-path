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
