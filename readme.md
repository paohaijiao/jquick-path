# jquick Path Document
Jquick Path is a query language used to extract data from JSON documents, similar to the role of XPath in XML.
It provides a concise way to locate and extract specific parts of JSON structures through path expressions.
# 基本语法
| expression      | description                                    |
|-----------------|------------------------------------------------|
| **S**           | root object                                    |
| **. or [ ]**    | sub-operator, used to access object properties |
| **...**         | recursion descent, search all child elements                                   |
| **\***          |  wildcard, matching all objects or array elements                                |
| **[ ]**         | subscript operator, used for array indexing or filtering                                |
| **[start:end]** | array slicing operation                                         |
| **?()**         | filter expression                                       |
| **@**           | The current node is used for filtering in expressions                                  |
## Table of Contents
- [Introduction](#introduction)
- [Basic Requests](#basic-requests)
    
- [Appendix](#appendix)
## Introduction
```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
     <artifactId>jquick-path</artifactId>
</dependency>
```
## Basic usage
### 1.rootSegement
```json
{"store":{"books":[{"title":"Book 1","author":"Author 1","price":10}, {"title":"Book 2","author":"Author 2","price":15}, {"title":"Book 3","author":"Author 3","price":20}]}}
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonObject)
                .document(JPath.fromRoot(JRoot.ROOT).property("store").property("books"))
                .limit(10)
                .execute();
```
```json
[{"title":"Book 1","author":"Author 1","price":10}, {"title":"Book 2","author":"Author 2","price":15}, {"title":"Book 3","author":"Author 3","price":20}]
```
### 1.currentSegement
```json
{"store":{"books":[{"title":"Book 1","author":"Author 1","price":10}, {"title":"Book 2","author":"Author 2","price":15}, {"title":"Book 3","author":"Author 3","price":20}]}}
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonObject)
        .document(JPath.fromRoot(JRoot.CURRENT).property("store").property("books"))
        .limit(10)
        .execute();
```
```json
[{"title":"Book 1","author":"Author 1","price":10}, {"title":"Book 2","author":"Author 2","price":15}, {"title":"Book 3","author":"Author 3","price":20}]
```