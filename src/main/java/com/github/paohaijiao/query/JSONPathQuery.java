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
