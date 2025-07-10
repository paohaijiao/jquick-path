package com.github.paohaijiao.query;

import com.github.paohaijiao.model.JSONPathResult;
import com.github.paohaijiao.query.impl.JFilterBuilder;
import com.github.paohaijiao.query.impl.JProjectionBuilder;
import com.github.paohaijiao.query.impl.JSortBuilder;
import com.github.paohaijiao.selector.root.JPath;
import com.github.paohaijiao.selector.subscript.JSubscript;
import com.github.paohaijiao.selector.subscript.JSubscripts;

public interface JSONPathQuery <T>{

    JSONPathQuery<T> document(JPath jSubscript);


    JSONPathResult execute();

    JSONPathQuery<T> limit(int limit);

    JSONPathQuery<T> skip(int skip);
}
