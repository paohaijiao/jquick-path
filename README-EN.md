# jquick Path document
[简体中文](./README.md) | ENGLISH

> Jquick Path is a query language used to extract data from JSON documents, similar to the role of XPath in XML.   
> It provides a concise way to locate and extract specific parts of JSON structures through path expressions.
## Project Status
[![License](https://img.shields.io/badge/License-Apache%202.0-5470c6.svg)](https://github.com/paohaijiao/jquick-path/blob/master/LICENSE)
[![Latest Version](https://img.shields.io/badge/Version-1.0.0-91cc75.svg)](https://github.com/paohaijiao/jquick-path/releases)
[![Monthly Downloads](https://img.shields.io/badge/Downloads-Coming%20Soon-fac858.svg)](https://github.com/paohaijiao/jquick-path)
[![Contributors](https://img.shields.io/badge/Contributors-1-3ba272.svg)](https://github.com/paohaijiao/jquick-path/graphs/contributors)
[![Build Status](https://img.shields.io/badge/Build-Passing-ee6666.svg)](https://github.com/paohaijiao/jquick-path/actions)
[![Test Coverage](https://img.shields.io/badge/Coverage-Coming%20Soon-73c0de.svg)](https://github.com/paohaijiao/jquick-path)
[![Open Issues](https://img.shields.io/badge/Issues-Coming%20Soon-9a60b4.svg)](https://github.com/paohaijiao/jquick-path/issues)

## 📖 Catalogue
- [BasicSyntax](#BasicSyntax)
- [Introduction and Installation](#IntroductionAndInstallation)
- [Root](#Root)
- [Paragraph](#Paragraph)
- [Subscript](#Subscript)
- [Expression](#Expression)
- [DonationSupport](#DonationSupport)

---

## 🧠 BasicSyntax

| Expression           | Description |
|----------------------|-------------|
| **$**                | Root object |
| **. or [ ]**         | Child operator, used to access object properties |
| **...**              | Recursive descent, searches all child elements |
| **\***               | Wildcard, matches all objects or array elements |
| **[ ]**              | Subscript operator, used for array indexing or filtering |
| **[start:end:step]** | Array slice operation |
| **?()**              | Filter expression |
| **@**                | Current node, used in filter expressions |

---
## 🚀 Introduction and Installation

### Maven Dependency
```xml
<dependency>
  <groupId>io.github.paohaijiao</groupId>
  <artifactId>jquick-path</artifactId>
  <version>${latest.version}</version>
</dependency>
```
**[Function Support](https://github.com/paohaijiao/javelin?tab=readme-ov-file#jevaluator-function-reference "support function")**
## root
### 1. Root Node Expression  Code

# Path Expression (Refer to Code Sample)
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
### 1. Root Node Expression
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

### 2. Current Node Expression
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

## Segment-related Examples
### 3. Property Accessor (Wildcard *)
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

### 4. Wildcard Accessor (Array Elements)
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

### 5. Numeric Subscript Accessor
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

### 6. Subscript Extractor (Property Value)
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

### 7. Subproperty Extractor (Recursive Search)
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

### 8. Subelement Subscript Extractor (Recursive Subscript)
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

## Subscript-related Examples
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
### 9. Numeric Subscript
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

### 10. Wildcard Subscript
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

### 11. Property Extraction (Object Property)
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

### 12. List Slicing
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

### 13. List Filtering
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

### 14. Expression Extraction
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

## Expression-related Examples
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
### 15. Negative Number Expression
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

### 16. Numeric Expression (Positive Integer)
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

### 17. Addition Expression
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

### 18. Subtraction Expression
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

### 19. Nested Function Expression
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

### 20. NOT Expression
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

### 21. Multiplication Expression
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

### 22. Division Expression
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

### 23. Modulo Expression
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

### 24. Greater Than Comparison Expression
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

### 25. Greater Than or Equal To Comparison Expression
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

### 26. Less Than Comparison Expression
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

### 27. Less Than or Equal To Comparison Expression
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

### 28. Equality Expression
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

### 29. Inequality Expression
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

### 30. IN Expression
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

### 31. AND Expression
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

### 32. OR Expression
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

### 33. Direct Use of Path Expression
- **Input Data**: JSON data containing a `store` object and a `books` array
- **Path Expression Logic**: Directly use the path expression `"$.store.books..[2]"` to retrieve the corresponding element
- **Java Code Logic**: Use the `path` method of `JSONPathQueryBuilder` to directly pass in the path expression and execute the query
```string
JSONPathQueryBuilder.from(jsonData).path("$.store.books..[2]").limit(10).execute();
```
- **Output Result**: The book object with index 2 in the `books` array (Book 3)

```
## **How to Support Us**

1. **Buy Me a Coffee**  
   If this project has saved you time or money, please consider supporting me with a small donation.

2. **Where Your Donation Goes**
- Covering server costs to keep the project running.
- Developing new features to deliver more value.
- Optimizing documentation to enhance user experience.

3. **Every Penny Counts**  
   Even a donation of just one cent can motivate me to debug code late into the night!
## **Why Donate?**
✔️ Keep the project forever free and ad-free.  
✔️ Support timely responses to issues and community inquiries.  
✔️ Realize planned future features.

Thank you for being a partner in making the open-source world better!

--- 

### **Additional Notes**
- Maintenance of this project and related products.
- Your support ensures its sustainability and growth.
---

## **🌟 Support Us Now**
Feel free to leave a message via [email](mailto:goudingcheng@gmail.com) when making a donation. Your name will be listed in the **"Special Thanks"** section of the project's README file!
![Pay Now](./src/main/resources/pay/paynow.jpg)
![TNG go](./src/main/resources/pay/tngGo.jpg)

---