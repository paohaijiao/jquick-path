package com.github.paohaijiao.query;

import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.query.impl.JFilterBuilder;
import com.github.paohaijiao.query.impl.JProjectionBuilder;
import com.github.paohaijiao.query.impl.JSortBuilder;

public interface JSONPathQuery <T>{

    JSONPathQuery<T> root();

    JSONPathQuery<T> current();




    JSONPathResult execute();

    JSONPathQuery<T> filter(JFilterBuilder<T> filter);


    JSONPathQuery<T> select(JProjectionBuilder<T> projection);

    JSONPathQuery<T> sort(JSortBuilder<T> sort);

    JSONPathQuery<T> limit(int limit);

    JSONPathQuery<T> skip(int skip);
}
