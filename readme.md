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
This document provides comprehensive examples for using JCurlInvoker, a Java-based HTTP client that uses cURL-style annotations to simplify API testing and integration.

## Basic Requests
