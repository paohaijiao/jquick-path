# jquick Path 文档
简体中文 | [英文](./README-EN.md)
```string
      Jquick Path 是一种用于从JSON文档中提取数据的查询语言，类似于XPath在XML中的作用。  
它通过路径表达式提供了一种简洁的方式来定位和提取JSON结构中的特定部分。 
```
## 项目状态
[![License](https://img.shields.io/badge/License-Apache%202.0-5470c6.svg)](https://github.com/paohaijiao/jquick-path/blob/master/LICENSE)
[![Latest Version](https://img.shields.io/badge/Version-1.0.0-91cc75.svg)](https://github.com/paohaijiao/jquick-path/releases)
[![Monthly Downloads](https://img.shields.io/badge/Downloads-Coming%20Soon-fac858.svg)](https://github.com/paohaijiao/jquick-path)
[![Contributors](https://img.shields.io/badge/Contributors-1-3ba272.svg)](https://github.com/paohaijiao/jquick-path/graphs/contributors)
[![Build Status](https://img.shields.io/badge/Build-Passing-ee6666.svg)](https://github.com/paohaijiao/jquick-path/actions)
[![Test Coverage](https://img.shields.io/badge/Coverage-Coming%20Soon-73c0de.svg)](https://github.com/paohaijiao/jquick-path)
[![Open Issues](https://img.shields.io/badge/Issues-Coming%20Soon-9a60b4.svg)](https://github.com/paohaijiao/jquick-path/issues)
# 基础语法
| expression           | description     |
|----------------------|-----------------|
| **S**                | 根对象             |
| **. or [ ]**         | 子操作符，用于访问对象属性   |
| **...**              | 递归下降，搜索所有子元素    |
| **\***               | 通配符，匹配所有对象或数组元素 |
| **[ ]**              | 下标操作符，用于数组索引或过滤 |
| **[start:end:step]** | 数组切片操作          |
| **?()**              | 过滤表达式           |
| **@**                | 当前节点，用于过滤表达式    |
# 目录
- [jquick Path 文档](#jquick-path-文档)
- [基础语法](#基础语法)
  - [表达式描述](#表达式描述)
- [简介](#简介)
- [根节点](#根节点)
  - [根段](#1-根段)
  - [当前段](#2-当前段)
- [段](#段)
  - [标识符段](#1-标识符段)
  - [通配符段](#2-通配符段)
  - [数字下标段](#3-数字下标段)
  - [下标段](#4-下标段)
  - [子标识符段](#5-子标识符段)
  - [子下标段](#6-子下标段)
- [下标](#下标)
  - [数字下标](#1-数字下标)
  - [通配符下标](#2-通配符下标)
  - [字符串字面量下标](#3-字符串字面量下标)
  - [切片下标](#4-切片下标)
  - [过滤表达式下标](#5-过滤表达式下标)
  - [表达式下标](#6-表达式下标)
- [表达式](#表达式)
  - [取反表达式](#1-取反表达式)
  - [括号表达式](#2-括号表达式)
  - [加法表达式](#3-加法表达式)
  - [减法表达式](#4-减法表达式)
  - [嵌套点表达式](#5-嵌套点表达式)
  - [非表达式](#6-非表达式)
  - [乘法表达式](#7-乘法表达式)
  - [除法表达式](#8-除法表达式)
  - [取模表达式](#9-取模表达式)
  - [大于比较表达式](#10-大于比较表达式)
  - [大于等于比较表达式](#11-大于等于比较表达式)
  - [小于比较表达式](#12-小于比较表达式)
  - [小于等于比较表达式](#13-小于等于比较表达式)
  - [等于表达式](#14-等于表达式)
  - [不等于表达式](#15-不等于表达式)
  - [包含表达式](#16-包含表达式)
  - [逻辑与表达式](#17-逻辑与表达式)
  - [逻辑或表达式](#18-逻辑或表达式)
  - [路径表达式](#19-路径表达式)
## Introduction
```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
     <artifactId>jquick-path</artifactId>
</dependency>
```
**[函数支持](https://github.com/paohaijiao/javelin?tab=readme-ov-file#jevaluator-function-reference "support function")**
## root
### 1. 根节点表达式 输入数据

```json
{
	"store": {
		"books": [
			{
				"title": "Book 1",
				"author": "Author 1",
				"price": 10
			},
			{
				"title": "Book 2",
				"author": "Author 2",
				"price": 15
			},
			{
				"title": "Book 3",
				"author": "Author 3",
				"price": 20
			}
		]
	}
}
```
### java 代码
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonObject)
                .document(JPath.fromRoot(JRoot.ROOT).property("store").property("books"))
                .limit(10)
                .execute();
```
### 输出结果
```json 
[
	{
		"title": "Book 1",
		"author": "Author 1",
		"price": 10
	},
	{
		"title": "Book 2",
		"author": "Author 2",
		"price": 15
	},
	{
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
]
```
2. 当前节点表达式
```json
{
	"store": {
		"books": [
			{
				"title": "Book 1",
				"author": "Author 1",
				"price": 10
			},
			{
				"title": "Book 2",
				"author": "Author 2",
				"price": 15
			},
			{
				"title": "Book 3",
				"author": "Author 3",
				"price": 20
			}
		]
	}
}
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonObject)
        .document(JPath.fromRoot(JRoot.CURRENT).property("store").property("books"))
        .limit(10)
        .execute();
```
```json  result
[
	{
		"title": "Book 1",
		"author": "Author 1",
		"price": 10
	},
	{
		"title": "Book 2",
		"author": "Author 2",
		"price": 15
	},
	{
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
]
```
## segment
1. 属性取值器
```json data
{
	"store": {
		"books": [
			{
				"title": "Book 1",
				"author": "Author 1",
				"price": 10
			},
			{
				"title": "Book 2",
				"author": "Author 2",
				"price": 15
			},
			{
				"title": "Book 3",
				"author": "Author 3",
				"price": 20
			}
		]
	}
}

```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("*"))
                .limit(10)
                .execute();
```
```json result
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20
		}
	]
}
```
2. 通配符取值器
```json
{
	"store": {
		"books": [
			{
				"title": "Book 1",
				"author": "Author 1",
				"price": 10
			},
			{
				"title": "Book 2",
				"author": "Author 2",
				"price": 15
			},
			{
				"title": "Book 3",
				"author": "Author 3",
				"price": 20
			}
		]
	}
}
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                .property("store").property("books").property("*"))
                .limit(10)
                .execute();
```
```json result
[
	{
		"title": "Book 1",
		"author": "Author 1",
		"price": 10
	},
	{
		"title": "Book 2",
		"author": "Author 2",
		"price": 15
	},
	{
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
]
```
3. 数值下标取值器
```json
{
	"store": {
		"books": [
			{
				"title": "Book 1",
				"author": "Author 1",
				"price": 10
			},
			{
				"title": "Book 2",
				"author": "Author 2",
				"price": 15
			},
			{
				"title": "Book 3",
				"author": "Author 3",
				"price": 20
			}
		]
	}
}
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                        .property("store").property("books")
                        .segment(JSegments.subscript(JIndexSubscript.of(2))))
                .limit(10)
                .execute();
```
```json result
{
	"title": "Book 3",
	"author": "Author 3",
	"price": 20
}
```
4. 下标提取器
```json data
{
	"store": {
		"books": [
			{
				"title": "Book 1",
				"author": "Author 1",
				"price": 10
			},
			{
				"title": "Book 2",
				"author": "Author 2",
				"price": 15
			},
			{
				"title": "Book 3",
				"author": "Author 3",
				"price": 20
			}
		]
	}
}
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
```json result
20
```
5. 子属性提取器
```json data
{
	"store": {
		"books": [
			{
				"title": "Book 1",
				"author": "Author 1",
				"price": 10
			},
			{
				"title": "Book 2",
				"author": "Author 2",
				"price": 15
			},
			{
				"title": "Book 3",
				"author": "Author 3",
				"price": 20
			}
		]
	}
}
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
```json result
[10, 15, 20]
```
6. 子元素下标提取器
```json data
{
	"store": {
		"books": [
			{
				"title": "Book 1",
				"author": "Author 1",
				"price": 10
			},
			{
				"title": "Book 2",
				"author": "Author 2",
				"price": 15
			},
			{
				"title": "Book 3",
				"author": "Author 3",
				"price": 20
			}
		]
	}
}
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
[
	{
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
]
```

## subscript
1. 数字
```json data
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[0]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books").segment(JSubscriptSegment.of(JSubscripts.index(0))))
                .limit(10)
                .execute();
```
```json result
{
	"title": "Book 1",
	"author": "Author 1",
	"price": 10
}
```
2. 通配符
```json data
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[*]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books").segment(JSubscriptSegment.of(JSubscripts.wildcard())))
                .limit(10)
                .execute();
```
```json result
[{"title":"Book 1","author":"Author 1","price":10}, {"title":"Book 2","author":"Author 2","price":15}, {"title":"Book 3","author":"Author 3","price":20}]
```
3. 字面量
```json data
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.extract['title']
```
```java
       JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("extract")
                        .segment(JSubscriptSegment.of(JSubscripts.property("title"))))
                .limit(10)
                .execute();
```
```json
Book 3
```
4. 列表切片
```json data
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[0:1:2]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books").segment(JSubscriptSegment.of(JSubscripts.slice(0,1,2))))
                .limit(10)
                .execute();
```
```json result
[
	{
		"title": "Book 1",
		"author": "Author 1",
		"price": 10
	}
]
```
5. 列表过滤
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[?(@.title == 'Book 1')]
```
```java
       JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                 .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.eq("title", "Book 1")))))
                .limit(10)
                .execute();
```
```json result
[
	{
		"title": "Book 1",
		"author": "Author 1",
		"price": 10
	}
]
```
6. 表达式提取
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[0*1]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("0*1"))))
                .limit(10)
                .execute();
```
```json
{
	"title": "Book 1",
	"author": "Author 1",
	"price": 10
}
```
## 表达式
1. 负数表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[-2]
```
```java
JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
.document(JPath.fromRoot(JRoot.ROOT).property("books")
.segment(JSubscriptSegment.of(JSubscripts.expr("-2"))))
.limit(10)
.execute();
```
```json
{
	"title": "Book 1",
	"author": "Author 1",
	"price": 10,
	"isbn": true
}
```
2. expression of negation bracket
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[2]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("2"))))
                .limit(10)
                .execute();
```
```json
{
	"title": "Book 3",
	"author": "Author 3",
	"price": 20,
	"isbn": true
}
```
3. 加法表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[1+1]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("1+1"))))
                .limit(10)
                .execute();
```
```json
{
	"title": "Book 3",
	"author": "Auhor 3",
	"price": 20,
	"isbn": true
}
```
4. 减法表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[1-1]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("1-1"))))
                .limit(10)
                .execute();
```
```json
{
	"title": "Book 1",
	"author": "Author 1",
	"price": 10,
	"isbn": true
}
```
5. expression of  netest
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
**[支持函数](https://github.com/paohaijiao/javelin?tab=readme-ov-file#jevaluator-function-reference "support function")**
```String
$.books[(@.length())-1]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("(@.length())-1"))))
                .limit(10)
                .execute();
```
```json
{
	"title": "Book 3",
	"author": "Author 3",
	"price": 20,
	"isbn": true
}
```
6. 非表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[?(!@.isbn)]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("!@.isbn")))))
                .limit(10);
```
```json
[
	{
		"title": "Book 2",
		"author": "Author 2",
		"price": 15,
		"isbn": false
	}
]
```
7. 乘法表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[1*1]
```
```java
JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
.document(JPath.fromRoot(JRoot.ROOT).property("books")
.segment(JSubscriptSegment.of(JSubscripts.expr("1*1"))))
.limit(10)
.execute();
```
```json
{
	"title": "Book 2",
	"author": "Author 2",
	"price": 15,
	"isbn": false
}
```
8. 除法表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[1/1]
```
```java
    JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("1/1"))))
                .limit(10)
                .execute();
```
```json
{
	"title": "Book 2",
	"author": "Author 2",
	"price": 15,
	"isbn": false
}
```
9. 取模表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[1%1]
```
```java
     JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.expr("1%1"))))
                .limit(10)
                .execute();
```
```json
{
	"title": "Book 1",
	"author": "Author 1",
	"price": 10,
	"isbn": true
}
```
10. 大于比较表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[?(@.price>15)]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price>15")))))
                .limit(10)
                .execute();
```
```json
[
	{
		"title": "Book 3",
		"author": "Author 3",
		"price": 20,
		"isbn": true
	}
]
```
11. 大于等于比较表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[?(@.price>=15)]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price>=15")))))
                .limit(10)
                .execute();
```
```json
[
	{
		"title": "Book 2",
		"author": "Author 2",
		"price": 15,
		"isbn": false
	},
	{
		"title": "Book 3",
		"author": "Author 3",
		"price": 20,
		"isbn": true
	}
]
```
12. 小于比较表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[?(@.price<15)]
```
```java
        JSONObject jsonData=getData1();
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price<15")))))
                .limit(10)
                .execute();
```
```json
[
	{
		"title": "Book 1",
		"author": "Author 1",
		"price": 10,
		"isbn": true
	}
]
```
13. 小于等于比较表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[?(@.price<=15)]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price<=15")))))
                .limit(10)
                .execute();
```
```json
[
	{
		"title": "Book 1",
		"author": "Author 1",
		"price": 10,
		"isbn": true
	},
	{
		"title": "Book 2",
		"author": "Author 2",
		"price": 15,
		"isbn": false
	}
]
```
14. 等于表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[?(@.price==15)]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price==15")))))
                .limit(10)
                .execute();
```
```json
[
	{
		"title": "Book 2",
		"author": "Author 2",
		"price": 15,
		"isbn": false
	}
]
```
15. 不等于表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[?(@.price!=15)]
```
```java
       JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price!=15")))))
                .limit(10)
                .execute();
```
```json
[
	{
		"title": "Book 1",
		"author": "Author 1",
		"price": 10,
		"isbn": true
	},
	{
		"title": "Book 3",
		"author": "Author 3",
		"price": 20,
		"isbn": true
	}
]
```
16. in 表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[?(@.title in ('Book 3','Book 2'))]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2')")))))
                .limit(10)
                .execute();
```
```json
[
	{
		"title": "Book 2",
		"author": "Author 2",
		"price": 15,
		"isbn": false
	},
	{
		"title": "Book 3",
		"author": "Author 3",
		"price": 20,
		"isbn": true
	}
]
```
17. 与表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[?(@.title in ('Book 3','Book 2') &&@.isbn)]
```
```java
JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
.document(JPath.fromRoot(JRoot.ROOT).property("books")
.segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2') &&@.isbn")))))
.limit(10)
.execute();
```
```json
[
	{
		"title": "Book 3",
		"author": "Author 3",
		"price": 20,
		"isbn": true
	}
]
```
18. 或表达式
```json
{
	"books": [
		{
			"title": "Book 1",
			"author": "Author 1",
			"price": 10,
			"isbn": true
		},
		{
			"title": "Book 2",
			"author": "Author 2",
			"price": 15,
			"isbn": false
		},
		{
			"title": "Book 3",
			"author": "Author 3",
			"price": 20,
			"isbn": true
		}
	],
	"extract": {
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
}
```
```String
$.books[?(@.title in ('Book 3','Book 2') ||@.isbn)]
```
```java
        JSONPathResult result = JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                        .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2') ||@.isbn")))))
                .limit(10)
                .execute();
```
```json
[
	{
		"title": "Book 1",
		"author": "Author 1",
		"price": 10,
		"isbn": true
	},
	{
		"title": "Book 2",
		"author": "Author 2",
		"price": 15,
		"isbn": false
	},
	{
		"title": "Book 3",
		"author": "Author 3",
		"price": 20,
		"isbn": true
	}
]
```
19. 路径表达式
```json
{
	"store": {
		"books": [
			{
				"title": "Book 1",
				"author": "Author 1",
				"price": 10
			},
			{
				"title": "Book 2",
				"author": "Author 2",
				"price": 15
			},
			{
				"title": "Book 3",
				"author": "Author 3",
				"price": 20
			}
		]
	}
}
```
```java
      JSONPathResult result = JSONPathQueryBuilder.from(jsonData).path("$.store.books..[2]").limit(10)
                .execute();
```
```json
[
	{
		"title": "Book 3",
		"author": "Author 3",
		"price": 20
	}
]
```
# **捐献 ☕**

感谢您使用这个开源项目！它完全免费并将持续维护，但开发者确实需要您的支持。

---

## **如何支持我们**

1. **请我喝杯咖啡**  
   果这个项目为您节省了时间或金钱，请考虑通过小额捐赠支持我。

2. **您的捐赠用途**
- 维持项目运行的服务器成本.
- 开发新功能以提供更多价值.
- 优化文档以提升用户体验.

3. **每一分都很重要**  
   即使是1分钱的捐赠也能激励我熬夜调试！


## **为什么捐赠?**
✔️ 保持项目永远免费且无广告.  
✔️ 支持及时响应问题和社区咨询.  
✔️ 实现计划中的未来功能.

感谢您成为让开源世界更美好的伙伴！

--- 

### **补充说明**
- 本项目和产品维护.
- 您的支持确保其可持续性和成长 .
---

## **🌟 立即支持**
赞助时欢迎通过 [email](mailto:goudingcheng@gmail.com) 留言。您的名字将被列入项目README文件的 **"特别感谢"** 名单中！
![Ali Pay](./src/main/resources/pay/alipay.jpg)
![Wechat Pay](./src/main/resources/pay/wechat.jpg)

---