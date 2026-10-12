<p align="center">
  <img src="./src/main/resources/static/jquick-logo.svg" width="700" alt="JQuick Path Logo" />
</p>

<h1 align="center">JQuick Path</h1>

<p align="center"><b>A lightweight, high-performance java path util that queries and extracts data from JSON documents with XPath-style path expressions.</b></p>

<p align="center">
  🌐 <a href="https://www.jquick.org">JQuick Website</a> ·
  📖 <a href="https://github.com/paohaijiao">GitHub</a> ·
  📦 <a href="https://central.sonatype.com/artifact/io.github.paohaijiao/javelin">Maven Central</a>
</p>

<p align="center">
  🔗 <a href="https://github.com/paohaijiao/jquick-curl">jquick-curl</a> ·
  📂 <a href="https://github.com/paohaijiao/jquick-path">jquick-path</a> ·
  📊 <a href="https://github.com/paohaijiao/jquick-excel">jquick-excel</a> ·
  📄 <a href="https://github.com/paohaijiao/jquick-pdf">jquick-pdf</a> ·
  ☕ <a href="https://github.com/paohaijiao/jquick-java">jquick-java</a> ·
  🗄️ <a href="https://github.com/paohaijiao/jquick-sql">jquick-sql</a>
</p>

<p align="center">
  <b>English</b> | <a href="./README-zh.md">简体中文</a>
</p>

<p align="center">
  <a href="https://central.sonatype.com/artifact/io.github.paohaijiao/jquick-path"><img src="https://img.shields.io/maven-central/v/io.github.paohaijiao/jquick-path?logo=apachemaven&label=maven" alt="Maven Version"></a>
  <a href="http://www.apache.org/licenses/LICENSE-2.0.txt"><img src="https://img.shields.io/badge/license-Apache%202.0-blue.svg" alt="License"></a>
  <a href="https://www.oracle.com/java/"><img src="https://img.shields.io/badge/JDK-8%2B-orange.svg" alt="JDK"></a>
  <a href="https://github.com/paohaijiao/jquick-path/stargazers"><img src="https://img.shields.io/github/stars/paohaijiao/jquick-path.svg?logo=github" alt="GitHub Stars"></a>
  <a href="https://github.com/paohaijiao/jquick-path/network/members"><img src="https://img.shields.io/github/forks/paohaijiao/jquick-path.svg?logo=github" alt="GitHub Forks"></a>
  <a href="https://github.com/paohaijiao/jquick-path/issues"><img src="https://img.shields.io/github/issues/paohaijiao/jquick-path.svg" alt="GitHub Issues"></a>
</p>

---

## 📖 Table of Contents

- [Project Introduction](#project-introduction)
- [Features](#features)
- [Environment Requirements](#environment-requirements)
- [Quick Start](#quick-start)
- [Usage Examples](#usage-examples)
  - [Root Expressions](#root-expressions)
  - [Segment Expressions](#segment-expressions)
  - [Subscript Expressions](#subscript-expressions)
  - [Expression Queries](#expression-queries)
  - [Direct Path Expressions](#direct-path-expressions)
- [Core API Reference](#core-api-reference)
- [Compatibility & Scenarios](#compatibility--scenarios)
- [Build & Compile](#build--compile)
- [Related Ecosystem](#related-ecosystem)
- [Contributing](#contributing)
- [License](#license)
- [Donation & Support](#donation--support)

---

## Project Introduction

**JQuick Path** is a query language and runtime for extracting data from JSON documents, playing the same role for JSON that XPath plays for XML. A concise **cross-platform path** expression is all you need to locate any node inside a nested JSON structure.

The library is written in pure Java and driven by an ANTLR4 grammar, so it has no native dependencies and behaves identically on every operating system. Under the hood it provides a fluent, type-safe builder (`JSONPathQueryBuilder`) together with a full expression engine: root and current node selectors, child properties, wildcards, recursive descent, array subscripts, slices, filters, arithmetic and logical expressions, plus result mapping into Java beans.

Whether you are reading configuration files, post-processing API responses, transforming data pipelines, or validating untrusted payloads, JQuick Path gives you a **java path util** that stays small, predictable and dependency-free.

> JQuick Path is a query language used to extract data from JSON documents, similar to the role of XPath in XML.
> It provides a concise way to locate and extract specific parts of JSON structures through path expressions.

---

## Features

- **Fluent query builder** — compose a query with `JSONPathQueryBuilder.from(...).document(...).limit(...).execute()`.
- **Programmatic path construction** — build paths with `JPath`, `JSegments` and `JSubscripts` instead of hand-written strings.
- **String path expressions** — pass expressions such as `$.store.books..[2]` straight to `path(String)`.
- **Root and current node selectors** — `$` (`JRoot.ROOT`) and `@` (`JRoot.CURRENT`).
- **Segment selectors** — property access, wildcard `*`, recursive descent `..`, subscript segments and recursive subscripts.
- **Subscript selectors** — numeric index, negative index, wildcard, object property `['title']`, slice `[start:end:step]`, filter `[?(...)]` and arithmetic expression subscripts.
- **Rich predicate library** — `eq`, `ne`, `gt`, `ge`, `lt`, `le`, `like`, `regex`, `startsWith`, `endsWith`, `contains`, `in`, `exists`, `notExists`, `and`, `or`, `not` and raw `custom` expressions.
- **Full filter expression engine** — comparison, logical, arithmetic, `in`, negation, regex and nested function calls such as `@.length()`.
- **Multiple input sources** — JSON string, `Map`, POJO or `JSONObject`.
- **Result handling** — `JSONPathResult` exposes `getRawData()`, `getAsList()` and `isList()`; `as()` / `asList()` map results to Java beans.
- **Sorting and pagination** — `JSortBuilder.asc()/desc()` for ordering, `limit()` / `skip()` for paging.
- **Grammar-driven parser** — ANTLR4 grammar with a dedicated error listener for readable syntax diagnostics.
- **Cross-platform and domestic OS compatible** — pure Java bytecode, no JNI, tested on Windows, Linux and macOS as well as **domestic OS compatible** environments (Kylin, UOS, openEuler) with mainstream domestic JDKs.
- **Bounded traversal** — recursive descent over untrusted JSON is capped by `limit()` / `skip()`, a lightweight **path traversal defense** against unbounded result expansion and resource exhaustion.
- **Declarative annotations** — `@JSONPath`, `@JSONObject`, `@JSONArray`, `@JSONFilter`, `@JSONSort` and `@JSONFormat` for path-based field and result binding.
- **Extensible utilities** — `JValueContextFactory` for context-based value access and `JTemplateEngine` for template evaluation.
- **Apache-2.0 licensed** — free for commercial and personal use.

---

## Environment Requirements

| Item | Requirement |
|------|-------------|
| JDK | 8 or later (JDK 17 recommended) |
| Build tool | Maven 3.6+ or Gradle 7+ |
| Operating system | Windows / Linux / macOS / domestic OS (信创) |
| Dependencies | ANTLR4 runtime, Hutool, Javelin core, JQuick JSON — all pure Java, no native libraries |

---

## Quick Start

### Maven

```xml
<dependency>
  <groupId>io.github.paohaijiao</groupId>
  <artifactId>jquick-path</artifactId>
  <version>${latest.version}</version>
</dependency>
```

### Gradle

```groovy
implementation 'io.github.paohaijiao:jquick-path:${latest.version}'
```

**[Function Support](https://github.com/paohaijiao/javelin?tab=readme-ov-file#jevaluator-function-reference "support function")**

---

## Basic Syntax

| Expression | Description |
|--------|------|
| **$** | Root object |
| **. or [ ]** | Child operator, used to access object properties |
| **...** | Recursive descent, searches all child elements |
| **\*** | Wildcard, matches all objects or array elements |
| **[ ]** | Subscript operator, used for array indexing or filtering |
| **[start:end:step]** | Array slice operation |
| **?()** | Filter expression |
| **@** | Current node, used in filter expressions |

---

## Usage Examples

All examples below share the same reference data unless stated otherwise.

- Reference Data

```string
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

### Root Expressions

#### 1. Root Node Expression

- **Input Data**: Contains a `store` object, under which there is a `books` array, and the array contains book objects with `title`, `author`, and `price` attributes.
- **Path Expression Logic**: Starting from the root node, access the `store` attribute and `books` attribute in sequence.
- **Java Code Logic**: Use `JSONPathQueryBuilder` to build a path from the root node, specify the `store` and `books` attributes, set the `limit` to 10, and then execute the query.

```string
      JSONPathQueryBuilder.from(jsonObject)
         .document(JPath.fromRoot(JRoot.ROOT)
         .property("store").property("books"))
         .limit(10).execute();
```

- Equivalent Path Expression: $.store.books
- **Output Result**: All book objects in the `books` array

#### 2. Current Node Expression

- **Input Data**: Same as the input data of the Root Node Expression
- **Path Expression Logic**: Starting from the current node, access the `store` attribute and `books` attribute in sequence.
- **Java Code Logic**: Similar to the Root Node Expression, the difference is that the path is built starting from the current node (`JRoot.CURRENT`).

```string
    JSONPathQueryBuilder.from(jsonObject)
    .document(JPath.fromRoot(JRoot.CURRENT)
    .property("store").property("books"))
    .limit(10).execute();
```

- Equivalent Path Expression: @.store.books
- **Output Result**: Same as the output result of the Root Node Expression, both are all book objects in the `books` array

### Segment Expressions

#### 3. Property Accessor (Wildcard *)

- **Input Data**: JSON data containing a `store` object with a `books` array underneath it
- **Path Expression Logic**: Starting from the root node, use the wildcard `*` to get all attributes under the root node
- **Java Code Logic**: Build a path starting from the root node, use `property("*")` to get all attributes, and execute the query

```string
   JSONPathQueryBuilder.from(jsonData)
   .document(JPath.fromRoot(JRoot.ROOT)
   .property("*")).limit(10).execute();
```

- Equivalent Path Expression: $.*
- **Output Result**: The `books` array in the `store` object under the root node

#### 4. Wildcard Accessor (Array Elements)

- **Input Data**: Same as the input data of the Property Accessor
- **Path Expression Logic**: Starting from the root node, access the `store` and `books` attributes in sequence, then use the wildcard `*` to get all elements of the `books` array
- **Java Code Logic**: Build a path to specify the `store` and `books` attributes in sequence, then get all elements of the array through `property("*")`, and execute the query

```string
  JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                .property("store").property("books").property("*"))
                .limit(10)
                .execute();
```

- Equivalent Path Expression: $.store.books.*
- **Output Result**: All book objects in the `books` array

#### 5. Numeric Subscript Accessor

- **Input Data**: JSON data containing a `store` object and a `books` array
- **Path Expression Logic**: Access the `books` array under `store` and get the element with index 2
- **Java Code Logic**: After building the path to the `books` array, specify the element with subscript 2 through the `segment` method, and execute the query

```string
- JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                .property("store").property("books")
                .segment(JSegments.subscript(JIndexSubscript.of(2))))
                .limit(10)
                .execute();
```

- Equivalent Path Expression: $.store.books[2]
- **Output Result**: The book object with index 2 in the `books` array (Book 3)

#### 6. Subscript Extractor (Property Value)

- **Input Data**: Same as the input data of the Numeric Subscript Accessor
- **Path Expression Logic**: Get the `price` property value of the element with index 2 in the `books` array
- **Java Code Logic**: After getting the element with index 2, continue to specify `property("price")` to get the price attribute, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                .property("store").property("books")
                .segment(JSegments.subscript(JIndexSubscript.of(2))).property("price"))
                .limit(10)
                .execute();
```

- Equivalent Path Expression: $.store.books[2].price
- **Output Result**: The price of the book with index 2 (20)

#### 7. Subproperty Extractor (Recursive Search)

- **Input Data**: JSON data containing a `store` object and a `books` array
- **Path Expression Logic**: Recursively search for all `price` property values under the `books` array
- **Java Code Logic**: After building the path to the `books` array, use `segment(JSegments.recursiveId("price"))` to recursively get the `price` attribute, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                .property("store").property("books")
                .segment(JSegments.recursiveId("price")))
                .limit(10)
                .execute();
```

- Equivalent Path Expression: $.store.books..price
- **Output Result**: An array composed of the prices of all books ([10, 15, 20])

#### 8. Subelement Subscript Extractor (Recursive Subscript)

- **Input Data**: Same as the input data of the Subproperty Extractor
- **Path Expression Logic**: Recursively search for the element with index 2 under the `books` array
- **Java Code Logic**: After building the path to the `books` array, get the element with the specified subscript through `segment(JSegments.recursiveSubscript(JIndexSubscript.of(2)))`, and execute the query

```string
JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                .property("store").property("books")
                .segment(JSegments.recursiveSubscript(JIndexSubscript.of(2)))
                ).limit(10)
                .execute();
```

- Equivalent Path Expression: $.store.books..[2]
- **Output Result**: The book object with index 2 in the `books` array (Book 3)

### Subscript Expressions

- Reference JSON

```string
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

#### 9. Numeric Subscript

- **Input Data**: JSON data containing a `books` array and an `extract` object, with the `books` array having information for three books
- **Path Expression Logic**: Get the element with index 0 in the `books` array
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.index(0)` to specify the subscript, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                .segment(JSubscriptSegment.of(JSubscripts.index(0))))
                .limit(10)
                .execute();
```

- Equivalent Path Expression: $.books[0]
- **Output Result**: The book object with index 0 in the `books` array (Book 1)

#### 10. Wildcard Subscript

- **Input Data**: Same as the input data of the Numeric Subscript
- **Path Expression Logic**: Get all elements of the `books` array
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.wildcard()` to get all elements, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books").segment(JSubscriptSegment.of(JSubscripts.wildcard())))
                .limit(10)
                .execute();
```

- Equivalent Path Expression: $.books[*]
- **Output Result**: All book objects in the `books` array

#### 11. Property Extraction (Object Property)

- **Input Data**: JSON data containing a `books` array and an `extract` object
- **Path Expression Logic**: Get the `title` property value of the `extract` object
- **Java Code Logic**: After building the path to the `extract` object, use `JSubscripts.property("title")` to get the `title` attribute, and execute the query

```string
   JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("extract")
    .segment(JSubscriptSegment.of(JSubscripts.property("title"))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.extract['title']
- **Output Result**: The `title` property value of the `extract` object (Book 3)

#### 12. List Slicing

- **Input Data**: Same as the input data of the Property Extraction
- **Path Expression Logic**: Slice the `books` array, starting from index 0, ending at index 1, with a step size of 2
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.slice(0,1,2)` to perform slicing, and execute the query

```string
  JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books").segment(JSubscriptSegment.of(JSubscripts.slice(0,1,2))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.books[0:1:2]
- **Output Result**: The sliced array containing the book object with index 0 (Book 1)

#### 13. List Filtering

- **Input Data**: JSON data containing a `books` array and an `extract` object
- **Path Expression Logic**: Filter out the elements in the `books` array where the `title` is 'Book 1'
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.filter(JPredicate.eq("title", "Book 1"))` to perform filtering, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.eq("title", "Book 1")))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.books[?(@.title == 'Book 1')]
- **Output Result**: The book object that meets the criteria (Book 1)

#### 14. Expression Extraction

- **Input Data**: Same as the input data of the List Filtering
- **Path Expression Logic**: Calculate the index through the expression `0*1`, and get the element of the `books` array at the corresponding index
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.expr("0*1")` to specify the index expression, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("0*1"))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.books[0*1]
- **Output Result**: The book object with index 0 in the `books` array (Book 1)

### Expression Queries

- Reference JSON

```string
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

#### 15. Negative Number Expression

- **Input Data**: JSON data containing a `books` array and an `extract` object, with the `books` array having three books including the `isbn` attribute
- **Path Expression Logic**: Get the element with index -2 in the `books` array
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.expr("-2")` to specify the negative index, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("-2"))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.books[-2]
- **Output Result**: The book object with index -2 in the `books` array (Book 1)

#### 16. Numeric Expression (Positive Integer)

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Get the element with index 2 in the `books` array
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.expr("2")` to specify the index, and execute the query

```string
  JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("2"))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.books[2]
- **Output Result**: The book object with index 2 in the `books` array (Book 3)

#### 17. Addition Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Calculate the index through the expression `1+1`, and get the element of the `books` array at the corresponding index
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.expr("1+1")` to specify the addition expression, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("1+1"))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.books[1+1]
- **Output Result**: The book object with index 2 in the `books` array (Book 3)

#### 18. Subtraction Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Calculate the index through the expression `1-1`, and get the element of the `books` array at the corresponding index
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.expr("1-1")` to specify the subtraction expression, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("1-1"))))
    .limit(10)
    .execute();
 ```

- Equivalent Path Expression: $.books[1-1]
- **Output Result**: The book object with index 0 in the `books` array (Book 1)

#### 19. Nested Function Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Get the length of the `books` array through the function `@.length()`, then subtract 1 to get the index and retrieve the corresponding element
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.expr("(@.length())-1")` to specify the expression containing the function, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("(@.length())-1"))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.books[(@.length())-1]
- **Output Result**: The last element of the `books` array (Book 3)

#### 20. NOT Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Filter out the elements in the `books` array where the `isbn` is false
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.filter(JPredicate.custom("!@.isbn"))` to perform NOT logic filtering, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("!@.isbn")))))
    .limit(10);
```

- Equivalent Path Expression: $.books[?(!@.isbn)]
- **Output Result**: The book object where `isbn` is false (Book 2)

#### 21. Multiplication Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Calculate the index through the expression `1*1`, and get the element of the `books` array at the corresponding index
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.expr("1*1")` to specify the multiplication expression, and execute the query

```string
  JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("1*1"))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.books[1*1]
- **Output Result**: The book object with index 1 in the `books` array (Book 2)

#### 22. Division Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Calculate the index through the expression `1/1`, and get the element of the `books` array at the corresponding index
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.expr("1/1")` to specify the division expression, and execute the query

```string
  JSONPathQueryBuilder.from(jsonData)
     .document(JPath.fromRoot(JRoot.ROOT).property("books")
     .segment(JSubscriptSegment.of(JSubscripts.expr("1/1"))))
     .limit(10)
     .execute();
```

- Equivalent Path Expression: $.books[1/1]
- **Output Result**: The book object with index 1 in the `books` array (Book 2)

#### 23. Modulo Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Calculate the index through the expression `1%1`, and get the element of the `books` array at the corresponding index
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.expr("1%1")` to specify the modulo expression, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("1%1"))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.books[1%1]
- **Output Result**: The book object with index 0 in the `books` array (Book 1)

#### 24. Greater Than Comparison Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Filter out the elements in the `books` array where the `price` is greater than 15
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.filter(JPredicate.custom("@.price>15"))` to perform greater than comparison filtering, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price>15")))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.books[?(@.price>15)]
- **Output Result**: The book object where `price` is greater than 15 (Book 3)

#### 25. Greater Than or Equal To Comparison Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Filter out the elements in the `books` array where the `price` is greater than or equal to 15
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.filter(JPredicate.custom("@.price>=15"))` to perform greater than or equal to comparison filtering, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price>=15")))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.books[?(@.price>=15)]
- **Output Result**: The book objects where `price` is greater than or equal to 15 (Book 2, Book 3)

#### 26. Less Than Comparison Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Filter out the elements in the `books` array where the `price` is less than 15
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.filter(JPredicate.custom("@.price<15"))` to perform less than comparison filtering, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price<15")))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.books[?(@.price<15)]
- **Output Result**: The book object where `price` is less than 15 (Book 1)

#### 27. Less Than or Equal To Comparison Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Filter out the elements in the `books` array where the `price` is less than or equal to 15
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.filter(JPredicate.custom("@.price<=15"))` to perform less than or equal to comparison filtering, and execute the query

```string
JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price<=15")))))
    .limit(10)
    .execute();
```

- Equivalent Path Expression: $.books[?(@.price<=15)]
- **Output Result**: The book objects where `price` is less than or equal to 15 (Book 1, Book 2)

#### 28. Equality Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Filter out the elements in the `books` array where the `price` is equal to 15
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.filter(JPredicate.custom("@.price==15"))` to perform equality comparison filtering, and execute the query

```string
  JSONPathQueryBuilder.from(jsonData)
   .document(JPath.fromRoot(JRoot.ROOT).property("books")
   .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price==15")))))
   .limit(10)
   .execute();
```

- Equivalent Path Expression: $.books[?(@.price==15)]
- **Output Result**: The book object where `price` is equal to 15 (Book 2)

#### 29. Inequality Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Filter out the elements in the `books` array where the `price` is not equal to 15
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.filter(JPredicate.custom("@.price!=15"))` to perform inequality comparison filtering, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
  .document(JPath.fromRoot(JRoot.ROOT).property("books")
  .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price!=15")))))
  .limit(10)
  .execute();
```

- Equivalent Path Expression: $.books[?(@.price!=15)]
- **Output Result**: The book objects where `price` is not equal to 15 (Book 1, Book 3)

#### 30. IN Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Filter out the elements in the `books` array where the `title` is in ('Book 3','Book 2')
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2')"))` to perform IN logic filtering, and execute the query

```string
   JSONPathQueryBuilder.from(jsonData)
  .document(JPath.fromRoot(JRoot.ROOT).property("books")
  .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2')")))))
  .limit(10)
  .execute();
```

- Equivalent Path Expression: $.books[?(@.title in ('Book 3','Book 2'))]
- **Output Result**: The book objects where `title` is in the specified range (Book 2, Book 3)

#### 31. AND Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Filter out the elements in the `books` array where the `title` is in ('Book 3','Book 2') and `isbn` is true
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2') &&@.isbn"))` to perform AND logic filtering, and execute the query

```string
 JSONPathQueryBuilder.from(jsonData)
  .document(JPath.fromRoot(JRoot.ROOT).property("books")
  .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2') &&@.isbn")))))
  .limit(10)
  .execute();
```

- Equivalent Path Expression: $.books[?(@.title in ('Book 3','Book 2') &&@.isbn)]
- **Output Result**: The book object that meets the AND logic criteria (Book 3)

#### 32. OR Expression

- **Input Data**: Same as the input data of the Negative Number Expression
- **Path Expression Logic**: Filter out the elements in the `books` array where the `title` is in ('Book 3','Book 2') or `isbn` is true
- **Java Code Logic**: After building the path to the `books` array, use `JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2') ||@.isbn"))` to perform OR logic filtering, and execute the query

```string
  JSONPathQueryBuilder.from(jsonData)
   .document(JPath.fromRoot(JRoot.ROOT).property("books")
   .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2') ||@.isbn")))))
   .limit(10)
   .execute();
```

- Equivalent Path Expression: $.books[?(@.title in ('Book 3','Book 2') ||@.isbn)]
- **Output Result**: The book objects that meet the OR logic criteria (Book 1, Book 2, Book 3)

### Direct Path Expressions

#### 33. Direct Use of Path Expression

- **Input Data**: JSON data containing a `store` object and a `books` array
- **Path Expression Logic**: Directly use the path expression `"$.store.books..[2]"` to retrieve the corresponding element
- **Java Code Logic**: Use the `path` method of `JSONPathQueryBuilder` to directly pass in the path expression and execute the query

```string
JSONPathQueryBuilder.from(jsonData).path("$.store.books..[2]").limit(10).execute();
```

- **Output Result**: The book object with index 2 in the `books` array (Book 3)

---

## Core API Reference

### JSONPathQueryBuilder (entry point)

| Method | Description |
|--------|-------------|
| `from(Object)` | Create a query from a POJO (serialized then parsed) |
| `from(String)` | Create a query from a JSON string |
| `from(Map)` | Create a query from a `Map` |
| `from(JSONObject)` | Create a query from a `JSONObject` |
| `root(JSubscripts)` | Create a query starting from a root subscript |
| `filter(Class<T>)` | Create a typed `JFilterBuilder<T>` |
| `of(JPredicate<T>)` / `of(String)` | Build a `JFilterExpression` from a predicate or raw expression |
| `select(Class<T>)` | Create a typed `JProjectionBuilder<T>` |

### JSONPathQuery&lt;T&gt; (fluent query)

| Method | Description |
|--------|-------------|
| `document(JPath)` | Set the programmatically built path |
| `path(String)` | Set the path from a string expression |
| `limit(int)` | Limit the number of returned elements |
| `skip(int)` | Skip the first N elements (works together with `limit`) |
| `sort(JSortBuilder<T>)` | Sort list results (requires `asList`) |
| `as(Class<T>)` | Map a single result to a Java bean |
| `asList(Class<T>)` | Map list results to Java beans |
| `execute()` | Execute the query and return a `JSONPathResult` |

### JPath / JPaths (path builder)

| Method | Description |
|--------|-------------|
| `JPath.fromRoot(JRoot)` | Build a path from the root (`$`) or current (`@`) node |
| `JPath.documentRoot()` / `JPaths.documentPath()` | Build a path from the root node |
| `JPath.currentRoot()` / `JPaths.currentPath()` | Build a path from the current node |
| `segment(JSegment)` / `segments(JSegment...)` | Append one or more segments |
| `property(String)` | Append a property identifier segment |
| `wildcard()` | Append a wildcard segment |
| `index(int)` | Append an index subscript segment |
| `filter(JPredicate<?>)` | Append a filter subscript segment |
| `recursiveProperty(String)` | Append a recursive identifier segment |
| `recursiveWildcard()` | Append a recursive wildcard segment |
| `toJSONPathExpression()` | Render the path as a JSONPath string |

### JSegments (segment factory)

| Method | Description |
|--------|-------------|
| `id(String)` | Identifier segment (`.property`) |
| `wildcard()` | Wildcard identifier segment (`.*`) |
| `subscript(JSubscript)` | Subscript segment (`[...]`) |
| `recursiveId(String)` | Recursive identifier segment (`..property`) |
| `recursiveWildcard()` | Recursive wildcard segment (`..*`) |
| `recursiveSubscript(JSubscript)` | Recursive subscript segment (`..[...]`) |

### JSubscripts (subscript factory)

| Method | Description |
|--------|-------------|
| `index(int)` | Numeric index, negative values count from the end |
| `wildcard()` | All elements (`[*]`) |
| `property(String)` | Object property (`['title']`) |
| `slice(Integer, Integer)` | Slice with start and end (`[0:1]`) |
| `slice(Integer, Integer, Integer)` | Slice with start, end and step (`[0:1:2]`) |
| `filter(JPredicate<T>)` | Filter expression (`[?(...)]`) |
| `expr(String)` | Raw arithmetic/function expression subscript (`[0*1]`) |

### JPredicate / JPredicates (predicate factory)

| Method | Description |
|--------|-------------|
| `eq` / `ne` | Equal / not equal |
| `gt` / `ge` / `lt` / `le` | Greater than / greater or equal / less than / less or equal |
| `like(prop, pattern)` | Wildcard match (`*`, `?`) |
| `regex(prop, regex)` | Regular expression match |
| `startsWith` / `endsWith` / `contains` | Common string matches built on `regex` |
| `in(prop, values...)` | Membership test |
| `exists` / `notExists` | Property presence test |
| `and` / `or` / `not` | Logical composition |
| `custom(String)` | Raw JSONPath filter expression |
| `of(prop, operator, value)` | Generic predicate with an explicit operator |

### JSONPathResult

| Method | Description |
|--------|-------------|
| `getRawData()` | The raw result object |
| `getAsList()` | The result as a `List` (single values are wrapped) |
| `isList()` | Whether the result is a list |

### JRoot (root symbols)

| Constant | Symbol | Description |
|----------|--------|-------------|
| `JRoot.ROOT` | `$` | Root document |
| `JRoot.CURRENT` | `@` | Current node |

---

## Compatibility & Scenarios

### Cross-platform

JQuick Path is compiled to pure Java bytecode and contains no JNI or platform-specific code, so the same artifact runs unchanged on:

| Platform | Status |
|----------|--------|
| Windows (x86_64 / ARM64) | Supported |
| Linux (x86_64 / ARM64 / LoongArch) | Supported |
| macOS (Intel / Apple Silicon) | Supported |
| **Domestic OS compatible** (Kylin, UOS, openEuler, Anolis) | Supported |

The ANTLR4 runtime, Javelin core and JQuick JSON dependencies are all pure Java, which means the library is **domestic OS compatible** without any recompilation and can be deployed in 信创 environments alongside mainstream domestic JDKs.

### Typical scenarios

- **Configuration reading** — pull nested values out of JSON/YAML-derived configuration documents.
- **API response processing** — extract and reshape fields from third-party responses.
- **Data conversion** — map JSON fragments straight into Java beans with `as()` / `asList()`.
- **Batch and pipeline jobs** — filter, slice, sort and paginate large JSON collections before downstream processing.
- **Untrusted payload inspection** — recursive descent is bounded by `limit()` / `skip()`, so a path expression cannot expand without limit over attacker-controlled JSON (a lightweight **path traversal defense** against resource exhaustion).

---

## Build & Compile

```bash
# Clone the repository
git clone https://github.com/paohaijiao/jquick-path.git
cd jquick-path

# Compile and install into the local repository
mvn clean install

# Package without running tests
mvn clean package -DskipTests

# Generate Javadoc
mvn javadoc:javadoc
```

The ANTLR4 grammar is located in `src/main/java/com/github/paohaijiao/parser` (`JQuickJSONPath.interp` / generated lexer, parser and visitor sources).

---

## Related Ecosystem

JQuick Path is part of the **JQuick** open-source ecosystem:

| Project                                                  | Description |
|----------------------------------------------------------|-------------|
| [JQuick hub](https://github.com/paohaijiao/jquick-hub)    | Lightweight, configuration-driven API gateway |
| JQuick SQL                                               | Declarative SQL query toolkit |
| [JQuick JSON](https://github.com/paohaijiao/jquick-json) | Lightweight, high-performance JSON library for Java |
| [Javelin](https://github.com/paohaijiao/javelin)         | Expression evaluation engine and shared core |
| JQuick Banner                                            | Console banner component used by the executor |

---

## Contributing

Contributions of all kinds are welcome — bug reports, feature requests, documentation improvements and pull requests.

1. Fork the repository and create your branch from `master`.
2. Keep the existing code style and add tests for new behaviour.
3. Make sure `mvn clean install` passes before opening a pull request.
4. Describe your change clearly and reference the related issue.

Please open an [issue](https://github.com/paohaijiao/jquick-path/issues) first for large changes so we can align on the design.

---

## License

JQuick Path is released under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt).

```
Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
