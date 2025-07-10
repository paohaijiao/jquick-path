package com.github.paohaijiao.jsonpath;

import com.github.paohaijiao.evelue.JsonPathBuilder;
import com.github.paohaijiao.executor.JSONPathExecutor;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.model.JSONPathResult;
import org.junit.Test;

import java.io.IOException;

public class JSonPathTest {

    @Test
    public void test11() throws IOException {
        JsonPath jsonPath = JsonPathBuilder.builder()
                .withJsonProvider(jacksonProvider)
                .withFunctionRegistry(registry)
                .build();
    }
}
