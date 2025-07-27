# jquick Path Document
Jquick Path is a query language used to extract data from JSON documents, similar to the role of XPath in XML.
It provides a concise way to locate and extract specific parts of JSON structures through path expressions.
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
# JEvaluator Function Reference
## 📊 Type Conversion Functions
| Function       | Syntax                  | Parameters       | Return Type | Description                     |
|----------------|-------------------------|------------------|-------------|---------------------------------|
| `toInteger`    | `toInteger(value)`      | 1 (string type)     | Integer     | Converts value to Integer       |
| `toDouble`     | `toDouble(value)`       | 1 (string type)     | Double      | Converts value to Double        |
| `toFloat`      | `toFloat(value)`        | 1 (string type)     | Float       | Converts value to Float         |
| `toString`     | `toString(value)`       | 1 (any type)        | String      | Converts value to String        |
| `parseToDate`  | `parseToDate(str,format)` | 2 (String)        | Date        | Parses string to Date           |
## 🔢 Math Functions
| Function | Syntax              | Parameters            | Return Type | Description                     |
|----------|---------------------|-----------------------|-------------|---------------------------------|
| `ceil`   | `ceil(number)`      | 1 (Number)            | Double      | Rounds up to nearest integer    |
| `floor`  | `floor(number)`     | 1 (Number)            | Double      | Rounds down to nearest integer  |
| `round`  | `round(num,digits)` | 2 (Number, Integer)   | Double      | Rounds to specified decimals    |
| `sum`    | `sum(values...)`    | ≥1 (Numbers)          | Number      | Sums all arguments              |
| `max`    | `max(values...)`    | ≥1 (Numbers)          | Number      | Returns maximum value           |
| `min`    | `min(values...)`    | ≥1 (Numbers)          | Number      | Returns minimum value           |
| `avg`    | `avg(values...)`    | ≥1 (Numbers)          | Double      | Calculates average              |
## 🔤 String Functions
| Function     | Syntax                    | Parameters            | Return Type | Description                     |
|--------------|---------------------------|-----------------------|-------------|---------------------------------|
| `toLower`    | `toLower(str)`            | 1 (String)            | String      | Converts to lowercase           |
| `toUpper`    | `toUpper(str)`            | 1 (String)            | String      | Converts to uppercase           |
| `contains`   | `contains(str,substr)`    | 2 (String)            | Boolean     | Checks if contains substring    |
| `join`       | `join(delimiter,items...)`| ≥2 (String, Objects)  | String      | Joins with delimiter            |
| `split`      | `split(str,delimiter)`    | 2 (String)            | String[]    | Splits string by delimiter      |
| `substring`  | `substring(str,start,end)`| 3 (String, int, int)  | String      | Extracts substring              |
| `replace`    | `replace(str,target,rep)` | 3 (String)            | String      | Replaces all occurrences        |
| `startsWith` | `startsWith(str,prefix)`  | 2 (String)            | Boolean     | Checks string prefix            |
| `endsWith`   | `endsWith(str,suffix)`    | 2 (String)            | Boolean     | Checks string suffix            |

## 📅 Date Functions

| Function      | Syntax                  | Parameters       | Return Type | Description                     |
|---------------|-------------------------|------------------|-------------|---------------------------------|
| `dateFormat`  | `dateFormat(date,format)` | 2 (Date, String) | String    | Formats date to string          |

## 📦 Collection Functions

| Function | Syntax            | Parameters | Return Type | Description                     |
|----------|-------------------|------------|-------------|---------------------------------|
| `length` | `length(array)`   | 1 (Array)  | Integer     | Returns array/list length       |
| `trans`  | `trans(src,dest)` | 2 (Objects)| Object      | Transforms between types        |

## root
### 1.rootSegement
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
### 2.currentSegement
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
### 1.identifierSegment
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
### 2.conditionIdentifierSegmentStar
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
### 3.subscriptSegment
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
### 4.subscript
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
### 5.childIdentifierSegment
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
### 6.childSubscriptSegment
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
### 1.number
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
### 2.wildcard
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
### 3.stringLiteral
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
### 4.slice
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
### 5.filterExpression
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
### 6.expr
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
### 1.negationExpression
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
### 2.bracketExpression
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
### 3.additiveExpression
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
### 3.additiveExpression1
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
### 4.netestDotExpr
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
### 5.notExpression
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
### 6.multiplicativeExpression
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
}```
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
### 7.multiplicativeExpression1
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
}```
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
### 8.multiplicativeExpression2
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
}```
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
### 9.comparisonExpression
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
}```
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
### 10.comparisonExpression1
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
]```
### 11.comparisonExpression2
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
}```
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
### 12.comparisonExpression3
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
### 13.equalityExpression
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
### 14.equalityExpression1
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
### 15.inExpression
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
}```
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
]```
### 16.logicalAndExpression
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
}```
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
### 17.logicalOrExpression
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
}```
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
]```
### 18.path 
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
}```
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