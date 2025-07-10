package com.github.paohaijiao.query;

import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.selector.root.JPath;

public interface JSONPathQuery <T>{

    JSONPathQuery<T> document(JPath jSubscript);


    JSONPathResult execute();

    JSONPathQuery<T> limit(int limit);

    JSONPathQuery<T> skip(int skip);
}
