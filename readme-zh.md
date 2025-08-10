# jquick Path 文档
```string
      Jquick Path 是一种用于从JSON文档中提取数据的查询语言，类似于XPath在XML中的作用。  
它通过路径表达式提供了一种简洁的方式来定位和提取JSON结构中的特定部分。 
```
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
**[support function](https://github.com/paohaijiao/javelin?tab=readme-ov-file#jevaluator-function-reference "support function")**
## root
1. root
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
                .document(JPath.fromRoot(JRoot.ROOT).property("store").property("books"))
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
2. current
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
1. identifier
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
2. wildcard
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
3. number subscript
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
4. subscript
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
5. childIdentifier
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
6. childSubscript
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
1. number
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
2. wildcard
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
3. stringLiteral
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
4. slice
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
5. filter
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
6. expression
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
## expr
1. expression of negation
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
3. expression of  additive
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
4. expression of  additive
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
**[support function](https://github.com/paohaijiao/javelin?tab=readme-ov-file#jevaluator-function-reference "support function")**
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
6. expression of   not
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
7. expression of   multiplicative
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
8. expression of   multiplicative
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
9. expression of   multiplicative
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
10. expression of comparison
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
11. expression of comparison
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
12. expression of  comparison
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
13. expression of  comparison
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
14. expression of  equality
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
15. expression of equality
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
16. expression of in 
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
17. expression of and logical
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
18. expression of or logical
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
19. expression of path 
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