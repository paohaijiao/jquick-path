package com.paohaijiao.javelin.test;

import com.paohaijiao.javelin.adaptor.JQuickAdaptor;
import com.paohaijiao.javelin.model.JSONPathResult;
import com.paohaijiao.javelin.exception.JAntlrExecutionException;
import com.paohaijiao.javelin.executor.JSONPathExecutor;
import com.paohaijiao.javelin.resource.JQuickReader;
import com.paohaijiao.javelin.resource.impl.JQuickReSourceFileReader;

import java.util.ArrayList;
import java.util.List;


public class JSonPathMain {
    public static void main(String[] args) {
        List<String> list = getData();
        JSONPathExecutor executor = new JSONPathExecutor(list);
        executor.addErrorListener(error -> {
            System.err.printf("错误: 行%d:%d - %s%n",
                    error.getLine(), error.getCharPosition(), error.getMessage());
            System.err.println("规则栈: " + error.getRuleStack());
        });
        try {
            JQuickReader fileReader = new JQuickReSourceFileReader("rule.txt");
            JQuickAdaptor context = new JQuickAdaptor(fileReader);
            System.out.println(context.getRuleContent());
            JSONPathResult jsonObject = executor.execute(context.getRuleContent());
            System.out.println("结果: " + jsonObject.getRawData());
        } catch (JAntlrExecutionException e) {
            System.err.println("解析失败: " + e.getMessage());
            e.getErrors().forEach(err ->
                    System.err.println(" - " + err.getMessage()));
        }
    }
    public  static  List<String> getData() {
        List<String> array=new ArrayList<>();
        array.add("a");
        array.add("b");
        array.add("c");
        array.add("d");
        array.add("e");
        array.add("f");
        array.add("g");
        array.add("h");
        array.add("i");
        return array;
    }
}
