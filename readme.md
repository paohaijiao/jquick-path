# jquick Path Document
```string
        Jquick Path is a query language used to extract data from JSON documents, similar to the role of XPath in XML.
It provides a concise way to locate and extract specific parts of JSON structures through path expressions.
```
# Basic Grammmer
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
- [jquick Path Document](#jquick-path-document)
- [Basic Grammar](#basic-grammmer)
    - [Expression Descriptions](#expression-descriptions)
- [Introduction](#introduction)
- [root](#root)
    - [rootSegment](#1-rootsegement)
    - [currentSegment](#2-currentsegement)
- [segment](#segment)
    - [identifierSegment](#1-identifiersegment)
    - [conditionIdentifierSegmentStar](#2-conditionidentifiersegmentstar)
    - [subscriptSegment](#3-subscriptsegment)
    - [subscript](#4-subscript)
    - [childIdentifierSegment](#5-childidentifiersegment)
    - [childSubscriptSegment](#6-childsubscriptsegment)
- [subscript](#subscript)
    - [number](#1-number)
    - [wildcard](#2-wildcard)
    - [stringLiteral](#3-stringliteral)
    - [slice](#4-slice)
    - [filterExpression](#5-filterexpression)
    - [expr](#6-expr)
- [expr](#expr)
    - [negationExpression](#1-negationexpression)
    - [bracketExpression](#2-bracketexpression)
    - [additiveExpression](#3-additiveexpression)
    - [additiveExpression1](#4-additiveexpression1)
    - [netestDotExpr](#5-netestdotexpr)
    - [notExpression](#6-notexpression)
    - [multiplicativeExpression](#7-multiplicativeexpression)
    - [multiplicativeExpression1](#8-multiplicativeexpression1)
    - [multiplicativeExpression2](#9-multiplicativeexpression2)
    - [comparisonExpression](#10-comparisonexpression)
    - [comparisonExpression1](#11-comparisonexpression1)
    - [comparisonExpression2](#12-comparisonexpression2)
    - [comparisonExpression3](#13-comparisonexpression3)
    - [equalityExpression](#14-equalityexpression)
    - [equalityExpression1](#15-equalityexpression1)
    - [inExpression](#16-inexpression)
    - [logicalAndExpression](#17-logicalandexpression)
    - [logicalOrExpression](#18-logicalorexpression)
    - [path](#19-path)
## Introduction
```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
     <artifactId>jquick-path</artifactId>
</dependency>
```
**[support function](https://github.com/paohaijiao/javelin?tab=readme-ov-file#jevaluator-function-reference "support function")**
## root
1. rootSegement
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
2. currentSegement
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
1. identifierSegment
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
2. conditionIdentifierSegmentStar
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
3. subscriptSegment
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
5. childIdentifierSegment
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
6. childSubscriptSegment
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
5. filterExpression
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
6. expr
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
1. negationExpression
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
2. bracketExpression
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
3. additiveExpression
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
4. additiveExpression1
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
5. netestDotExpr
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
6. notExpression
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
7. multiplicativeExpression
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
8. multiplicativeExpression1
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
9. multiplicativeExpression2
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
10. comparisonExpression
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
11. comparisonExpression1
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
12. comparisonExpression2
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
13. comparisonExpression3
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
14. equalityExpression
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
15. equalityExpression1
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
16. inExpression
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
17. logicalAndExpression
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
18. logicalOrExpression
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
19. path 
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