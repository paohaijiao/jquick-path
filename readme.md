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
# Table of Contents

- [jquick Path Document](#jquick-path-document)
- [Basic Grammar](#basic-grammar)
  - [Expression Descriptions](#expression-descriptions)
- [Introduction](#introduction)
- [Root](#root)
  - [Root Segment](#1-root-segment)
  - [Current Segment](#2-current-segment)
- [Segment](#segment)
  - [Identifier Segment](#1-identifier-segment)
  - [Wildcard Segment](#2-wildcard-segment)
  - [Number Subscript Segment](#3-number-subscript-segment)
  - [Subscript Segment](#4-subscript-segment)
  - [Child Identifier Segment](#5-child-identifier-segment)
  - [Child Subscript Segment](#6-child-subscript-segment)
- [Subscript](#subscript)
  - [Number Subscript](#1-number-subscript)
  - [Wildcard Subscript](#2-wildcard-subscript)
  - [String Literal Subscript](#3-string-literal-subscript)
  - [Slice Subscript](#4-slice-subscript)
  - [Filter Expression Subscript](#5-filter-expression-subscript)
  - [Expression Subscript](#6-expression-subscript)
- [Expression](#expr)
  - [Negation Expression](#1-negation-expression)
  - [Bracket Expression](#2-bracket-expression)
  - [Additive Expression](#3-additive-expression)
  - [Additive Expression (Subtraction)](#4-additive-expression-subtraction)
  - [Nested Dot Expression](#5-nested-dot-expression)
  - [Not Expression](#6-not-expression)
  - [Multiplicative Expression](#7-multiplicative-expression)
  - [Multiplicative Expression (Division)](#8-multiplicative-expression-division)
  - [Multiplicative Expression (Modulo)](#9-multiplicative-expression-modulo)
  - [Comparison Expression (Greater Than)](#10-comparison-expression-greater-than)
  - [Comparison Expression (Greater Than or Equal)](#11-comparison-expression-greater-than-or-equal)
  - [Comparison Expression (Less Than)](#12-comparison-expression-less-than)
  - [Comparison Expression (Less Than or Equal)](#13-comparison-expression-less-than-or-equal)
  - [Equality Expression (Equal)](#14-equality-expression-equal)
  - [Equality Expression (Not Equal)](#15-equality-expression-not-equal)
  - [In Expression](#16-in-expression)
  - [Logical AND Expression](#17-logical-and-expression)
  - [Logical OR Expression](#18-logical-or-expression)
  - [Path Expression](#19-path-expression)
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