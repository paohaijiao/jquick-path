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

### 2. 当前节点表达式
- **输入数据**：同根节点表达式的输入数据
- **路径表达式逻辑**：从当前节点开始，依次访问store属性和books属性
- **Java代码逻辑**：与根节点表达式类似，区别在于路径从当前节点（JRoot.CURRENT）开始构建
```string
    JSONPathQueryBuilder.from(jsonObject)
    .document(JPath.fromRoot(JRoot.CURRENT)
    .property("store").property("books"))
    .limit(10).execute();
```
- 等价路径表达式:@.store.books
- **输出结果**：与根节点表达式输出结果相同，均为books数组中的所有书籍对象

## 段（segment）相关示例
### 3. 属性取值器（通配符*）
- **输入数据**：包含store对象，其下有books数组的JSON数据
- **路径表达式逻辑**：从根节点开始，使用通配符*获取根节点下的所有属性
- **Java代码逻辑**：构建从根节点出发，使用property("*")获取所有属性的路径，执行查询
```string
   JSONPathQueryBuilder.from(jsonData)
   .document(JPath.fromRoot(JRoot.ROOT)
   .property("*")).limit(10).execute();
```
- 等价路径表达式:$.*
- **输出结果**：根节点下的store对象中的books数组

### 4. 通配符取值器（数组元素）
- **输入数据**：同属性取值器的输入数据
- **路径表达式逻辑**：从根节点开始，依次访问store、books属性，再用通配符*获取books数组的所有元素
- **Java代码逻辑**：构建路径依次指定store、books属性，再通过property("*")获取数组所有元素，执行查询
```string
  JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                .property("store").property("books").property("*"))
                .limit(10)
                .execute();
```
- 等价路径表达式:$.store.books.*
- **输出结果**：books数组中的所有书籍对象

### 5. 数值下标取值器
- **输入数据**：包含store对象及books数组的JSON数据
- **路径表达式逻辑**：访问store下的books数组，获取索引为2的元素
- **Java代码逻辑**：构建路径到books数组后，通过segment方法指定下标为2的元素，执行查询
```string
- JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                .property("store").property("books")
                .segment(JSegments.subscript(JIndexSubscript.of(2))))
                .limit(10)
                .execute();
```
- 等价路径表达式:$.store.booksp[2]
- **输出结果**：books数组中索引为2的书籍对象（Book 3）

### 6. 下标提取器（属性值）
- **输入数据**：同数值下标取值器的输入数据
- **路径表达式逻辑**：获取books数组索引为2的元素的price属性值
- **Java代码逻辑**：在获取到索引为2的元素后，继续指定property("price")获取价格属性，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                .property("store").property("books")
                .segment(JSegments.subscript(JIndexSubscript.of(2))).property("price"))
                .limit(10)
                .execute();
```
- 等价路径表达式:$.store.books[2].price
- **输出结果**：索引为2的书籍的价格（20）

### 7. 子属性提取器（递归搜索）
- **输入数据**：包含store对象及books数组的JSON数据
- **路径表达式逻辑**：递归递归方式搜索books数组下所有的price属性值
- **Java代码逻辑**：构建路径到books数组后，使用segment(JSegments.recursiveId("price"))递归获取price属性，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                .property("store").property("books")
                .segment(JSegments.recursiveId("price")))
                .limit(10)
                .execute();
```
- 等价路径表达式:$.store.books..price
- **输出结果**：所有书籍的价格组成的数组（[10, 15, 20]）

### 8. 子元素下标提取器（递归下标）
- **输入数据**：同子属性提取器的输入数据
- **路径表达式逻辑**：递归搜索books数组下索引为2的元素
- **Java代码逻辑**：构建路径到books数组后，通过segment(JSegments.recursiveSubscript(JIndexSubscript.of(2)))获取指定下标元素，执行查询
```string
JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT)
                .property("store").property("books")
                .segment(JSegments.recursiveSubscript(JIndexSubscript.of(2)))
                ).limit(10)
                .execute();
```
- 等价路径表达式:$.store.books..[2]
- **输出结果**：books数组中索引为2的书籍对象（Book 3）

## 下标（subscript）相关示例
- 参考json
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
### 9. 数字下标
- **输入数据**：包含books数组和extract对象的JSON数据，books数组有三本图书信息
- **路径表达式逻辑**：获取books数组索引为0的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.index(0)指定下标，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books")
                .segment(JSubscriptSegment.of(JSubscripts.index(0))))
                .limit(10)
                .execute();
```
- 等价路径表达式:$.books[0]
- **输出结果**：books数组中索引为0的书籍对象（Book 1）

### 10. 通配符下标
- **输入数据**：同数字下标的的输入数据
- **路径表达式逻辑**：获取books数组的所有元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.wildcard()获取所有元素，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
                .document(JPath.fromRoot(JRoot.ROOT).property("books").segment(JSubscriptSegment.of(JSubscripts.wildcard())))
                .limit(10)
                .execute();
```
- 等价路径表达式:$.books[*]
- **输出结果**：books数组中的所有书籍对象

### 11. 属性提取（对象属性）
- **输入数据**：包含books数组和extract对象的JSON数据
- **路径表达式逻辑**：获取extract对象的title属性值
- **Java代码逻辑**：构建路径到extract对象后，使用JSubscripts.property("title")获取title属性，执行查询
```string
   JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("extract")
    .segment(JSubscriptSegment.of(JSubscripts.property("title"))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.extract['title']
- **输出结果**：extract对象的title属性值（Book 3）

### 12. 列表切片
- **输入数据**：同属性提取的输入数据
- **路径表达式逻辑**：对books数组进行切片，从索引0开始，到索引1结束，步长为2
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.slice(0,1,2)进行切片，执行查询
```string
  JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books").segment(JSubscriptSegment.of(JSubscripts.slice(0,1,2))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.books[0:1:2]
- **输出结果**：切片片后的数组，包含索引为0的书籍对象（Book 1）

### 13. 列表过滤
- **输入数据**：包含books数组和extract对象的JSON数据
- **路径表达式逻辑**：过滤出books数组中title为'Book 1'的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.filter(JPredicate.eq("title", "Book 1"))进行过滤，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.eq("title", "Book 1")))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.books[?(@.title == 'Book 1')]
- **输出结果**：符合条件的书籍对象（Book 1）

### 14. 表达式提取
- **输入数据**：同列表过滤的输入数据
- **路径表达式逻辑**：通过表达式0*1计算索引，获取books数组对应索引的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.expr("0*1")指定索引表达式，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("0*1"))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.books[0*1]
- **输出结果**：books数组中索引为0的书籍对象（Book 1）

## 表达式相关示例
- 参考json
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
### 15. 负数表达式
- **输入数据**：包含books数组和extract对象的JSON数据，books数组有三本图书，含isbn属性
- **路径表达式逻辑**：获取books数组中索引为-2的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.expr("-2")指定负数索引，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("-2"))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.books[-2]
- **输出结果**：books数组中索引为-2的书籍对象（Book 1）

### 16. 数字表达式（正整数）
- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：获取books数组中索引为2的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.expr("2")指定索引，执行查询
```string
  JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("2"))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.books[2]
- **输出结果**：books数组中索引为2的书籍对象（Book 3）

### 17. 加法表达式
- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：通过表达式1+1计算索引，获取books数组对应索引的元素
- ** Java代码逻辑 **：构建路径到books数组后，使用JSubscripts.expr("1+1")指定加法表达式，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("1+1"))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.books[1+1]
- ** 输出结果 **：books数组中索引为2的书籍对象（Book 3）

### 18. 减法表达式
-** 输入数据 **：同负数表达式的输入数据
-** 路径表达式逻辑 **：通过表达式1-1计算索引，获取books数组对应索引的元素
-** Java代码逻辑 **：构建路径到books数组后，使用JSubscripts.expr("1-1")指定减法法表达式，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("1-1"))))
    .limit(10)
    .execute();
 ```
- 等价路径表达式:$.books[1-1]
  -** 输出结果 **：books数组中索引为0的书籍对象（Book 1）

### 19. 嵌套函数表达式
-** 输入数据 **：同负数表达式的输入数据
-** 路径表达式逻辑 **：通过函数@.length()获取books数组长度，再减1得到索引，获取对应元素
-** Java代码逻辑 **：构建路径到books数组后，使用JSubscripts.expr("(@.length())-1")指定包含函数的表达式，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("(@.length())-1"))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.books[(@.length())-1]
- ** 输出结果 **：books数组的最后一个元素（Book 3）

### 20. 非表达式
-** 输入数据 **：同负数表达式的输入数据
-** 路径表达式逻辑 **：过滤出books数组中isbn为false的元素
-** Java代码逻辑 **：构建路径到books数组后，使用JSubscripts.filter(JPredicate.custom("!@.isbn"))进行非逻辑过滤，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("!@.isbn")))))
    .limit(10);
```
- 等价路径表达式:$.books[?(!@.isbn)]
  -** 输出结果 **：isbn为false的书籍对象（Book 2）

### 21. 乘法表达式
-** 输入数据 **：同负数表达式的输入数据
-** 路径表达式逻辑 **：通过表达式1*1计算索引，获取books**数组对应索引的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.expr("1*1")指定乘法表达式，执行查询
```string
  JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("1*1"))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.books[1*1]
- **输出结果**：books数组中索引为1的书籍对象（Book 2）

### 22. 除法表达式
- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：通过表达式1/1计算索引，获取books数组对应索引的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.expr("1/1")指定除法表达式，执行查询
```string
  JSONPathQueryBuilder.from(jsonData)
     .document(JPath.fromRoot(JRoot.ROOT).property("books")
     .segment(JSubscriptSegment.of(JSubscripts.expr("1/1"))))
     .limit(10)
     .execute();
```
- 等价路径表达式:$.books[1/1]
- **输出结果**：books数组中索引为1的书籍对象（Book 2）

### 23. 取模表达式
- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：通过表达式1%1计算索引，获取books数组对应索引的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.expr("1%1")指定取模表达式，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("1%1"))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.books[1%1]
- **输出结果**：books数组中索引为0的书籍对象（Book 1）

### 24. 大于比较表达式
- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：过滤出books数组中price大于15的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.filter(JPredicate.custom("@.price>15"))进行大于比较过滤，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price>15")))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.books[?(@.price>15)]
- **输出结果**：price大于15的书籍对象（Book 3）

### 25. 大于等于比较表达式
- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：过滤出books数组中price大于等于15的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.filter(JPredicate.custom("@.price>=15"))进行大于等于比较过滤，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price>=15")))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.books[?(@.price>=15)]
- **输出结果**：price大于等于15的书籍对象（Book 2、Book 3）

### 26. 小于比较表达式
- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：过滤出books数组中price小于15的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.filter(JPredicate.custom("@.price<15"))进行小于比较过滤，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price<15")))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.books[?(@.price<15)]
- **输出结果**：price小于15的书籍对象（Book 1）

### 27. 小于等于比较表达式
- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：过滤出books数组中price小于等于15的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.filter(JPredicate.custom("@.price<=15"))进行小于等于比较过滤，执行查询
```string
JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price<=15")))))
    .limit(10)
    .execute();
```
- 等价路径表达式:$.books[?(@.price<=15)]
- **输出结果**：price小于等于15的书籍对象（Book 1、Book 2）

### 28. 等于表达式
- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：过滤出books数组中price等于15的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.filter(JPredicate.custom("@.price==15"))进行等于比较过滤，执行查询
```string
  JSONPathQueryBuilder.from(jsonData)
   .document(JPath.fromRoot(JRoot.ROOT).property("books")
   .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price==15")))))
   .limit(10)
   .execute();
```
- 等价路径表达式: $.books[?(@.price==15)]
- **输出结果**：price等于15的书籍对象（Book 2）

### 29. 不等于表达式
- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：过滤出books数组中price不等于15的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.filter(JPredicate.custom("@.price!=15"))进行不等于比较过滤，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
  .document(JPath.fromRoot(JRoot.ROOT).property("books")
  .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.price!=15")))))
  .limit(10)
  .execute();
```
- 等价路径表达式: $.books[?(@.price!=15)]
- **输出结果**：price不等于15的书籍对象（Book 1、Book 3）

### 30. in表达式
- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：过滤出books数组中title在('Book 3','Book 2')中的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2')"))进行in逻辑过滤，执行查询
```string
   JSONPathQueryBuilder.from(jsonData)
  .document(JPath.fromRoot(JRoot.ROOT).property("books")
  .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2')")))))
  .limit(10)
  .execute();
```
- 等价路径表达式: $.books[?(@.title in ('Book 3','Book 2'))]
- **输出结果**：title在指定范围内的书籍对象（Book 2、Book 3）

### 31. 与表达式
- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：过滤出books数组中title在('Book 3','Book 2')且isbn为true的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2') &&@.isbn"))进行与逻辑过滤，执行查询
```string
 JSONPathQueryBuilder.from(jsonData)
  .document(JPath.fromRoot(JRoot.ROOT).property("books")
  .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2') &&@.isbn")))))
  .limit(10)
  .execute();
```
- 等价路径表达式: $.books[?(@.title in ('Book 3','Book 2') &&@.isbn)]
- **输出结果**：符合与逻辑条件的书籍对象（Book 3）

### 32. 或表达式
- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：过滤出books数组中title在('Book 3','Book 2')或isbn为true的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2') ||@.isbn"))进行或逻辑过滤，执行查询
```string
  JSONPathQueryBuilder.from(jsonData)
   .document(JPath.fromRoot(JRoot.ROOT).property("books")
   .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("@.title in ('Book 3','Book 2') ||@.isbn")))))
   .limit(10)
   .execute();
```
- 等价路径表达式: $.books[?(@.title in ('Book 3','Book 2') ||@.isbn)]
- **输出结果**：符合或逻辑条件的书籍对象（Book 1、Book 2、Book 3）

### 33. 路径表达式直接使用
- **输入数据**：包含store对象及books数组的JSON数据
- **路径表达式逻辑**：直接使用路径表达式"$.store.books..[2]"获取对应元素
- **Java代码逻辑**：使用JSONPathQueryBuilder的path方法直接传入路径表达式，执行查询
```string
JSONPathQueryBuilder.from(jsonData).path("$.store.books..[2]").limit(10).execute();
```
- **输出结果**：books数组中索引为2的书籍对象（Book 3）
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