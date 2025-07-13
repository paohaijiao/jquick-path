# jquick Path Document
Jquick Path is a query language used to extract data from JSON documents, similar to the role of XPath in XML.
It provides a concise way to locate and extract specific parts of JSON structures through path expressions.
# 基本语法
| expression           | description                                    |
|----------------------|------------------------------------------------|
| **S**                | root object                                    |
| **. or [ ]**         | sub-operator, used to access object properties |
| **...**              | recursion descent, search all child elements                                   |
| **\***               |  wildcard, matching all objects or array elements                                |
| **[ ]**              | subscript operator, used for array indexing or filtering                                |
| **[start:end:step]** | array slicing operation                                         |
| **?()**              | filter expression                                       |
| **@**                | The current node is used for filtering in expressions                                  |
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
## root
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
### 2.currentSegement
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
## segment
### 1.identifierSegment
```json
{"store":{"books":[{"title":"Book 1","author":"Author 1","price":10}, {"title":"Book 2","author":"Author 2","price":15}, {"title":"Book 3","author":"Author 3","price":20}]}}
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("*"))
                .limit(10)
                .execute();
```
```json
{"books":[{"title":"Book 1","author":"Author 1","price":10}, {"title":"Book 2","author":"Author 2","price":15}, {"title":"Book 3","author":"Author 3","price":20}]}
```
### 2.conditionIdentifierSegmentStar
```json
{"store":{"books":[{"title":"Book 1","author":"Author 1","price":10}, {"title":"Book 2","author":"Author 2","price":15}, {"title":"Book 3","author":"Author 3","price":20}]}}
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                .property("store").property("books").property("*"))
                .limit(10)
                .execute();
```
```json
[{"title":"Book 1","author":"Author 1","price":10}, {"title":"Book 2","author":"Author 2","price":15}, {"title":"Book 3","author":"Author 3","price":20}]
```
### 3.subscriptSegment
```json
{"store":{"books":[{"title":"Book 1","author":"Author 1","price":10}, {"title":"Book 2","author":"Author 2","price":15}, {"title":"Book 3","author":"Author 3","price":20}]}}
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                        .property("store").property("books")
                        .segment(JSegments.subscript(JIndexSubscript.of(2))))
                .limit(10)
                .execute();
```
```json
{"title":"Book 3","author":"Author 3","price":20}
```
### 4.subscript
```json
{"store":{"books":[{"title":"Book 1","author":"Author 1","price":10}, {"title":"Book 2","author":"Author 2","price":15}, {"title":"Book 3","author":"Author 3","price":20}]}}
```
```String
path:$.store.books[2].price 
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                        .property("store").property("books")
                        .segment(JSegments.subscript(JIndexSubscript.of(2))).property("price"))
                .limit(10)
                .execute();
```
```json
20
```
### 5.childIdentifierSegment
```json
{"store":{"books":[{"title":"Book 1","author":"Author 1","price":10}, {"title":"Book 2","author":"Author 2","price":15}, {"title":"Book 3","author":"Author 3","price":20}]}}
```
```String
path:$.store.books..price
```
```java
      JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                        .property("store").property("books")
                        .segment(JSegments.recursiveId("price")))
                .limit(10)
                .execute();
```
```json
[10, 15, 20]
```
### 6.childSubscriptSegment
```json
{"store":{"books":[{"title":"Book 1","author":"Author 1","price":10}, {"title":"Book 2","author":"Author 2","price":15}, {"title":"Book 3","author":"Author 3","price":20}]}}
```
```String
$.store.books..[2]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                        .property("store").property("books")
                        .segment(JSegments.recursiveSubscript(JIndexSubscript.of(2)))
                ).limit(10)
                .execute();
```
```json
[{"title":"Book 3","author":"Author 3","price":20}]
```