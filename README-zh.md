<p align="center">
  <img src="./src/main/resources/static/jquick-logo.svg" width="700" alt="JQuick Path Logo" />
</p>

<h1 align="center">JQuick Path</h1>

<p align="center"><b>轻量、高效的 Java 路径工具库，使用类 XPath 的路径表达式查询与提取 JSON 数据。</b></p>

<p align="center">
  <a href="./README.md">English</a> | <b>简体中文</b>
</p>

<p align="center">
  <a href="https://central.sonatype.com/artifact/io.github.paohaijiao/jquick-path"><img src="https://img.shields.io/maven-central/v/io.github.paohaijiao/jquick-path?logo=apachemaven&label=maven" alt="Maven Version"></a>
  <a href="http://www.apache.org/licenses/LICENSE-2.0.txt"><img src="https://img.shields.io/badge/license-Apache%202.0-blue.svg" alt="License"></a>
  <a href="https://www.oracle.com/java/"><img src="https://img.shields.io/badge/JDK-8%2B-orange.svg" alt="JDK"></a>
  <a href="https://github.com/paohaijiao/jquick-path/stargazers"><img src="https://img.shields.io/github/stars/paohaijiao/jquick-path.svg?logo=github" alt="GitHub Stars"></a>
  <a href="https://github.com/paohaijiao/jquick-path/network/members"><img src="https://img.shields.io/github/forks/paohaijiao/jquick-path.svg?logo=github" alt="GitHub Forks"></a>
</p>

---

## 📖 目录

- [项目简介](#项目简介)
- [核心特性](#核心特性)
- [快速开始](#快速开始)
- [使用示例](#使用示例)
- [许可证](#许可证)

---

## 项目简介

JQuick Path 是一种用于从 JSON 文档中提取数据的查询语言，类似于 XPath 在 XML 中的作用。它通过路径表达式提供了一种简洁的方式来定位和提取 JSON 结构中的特定部分。

库完全由纯 Java 实现，基于 ANTLR4 语法驱动，无任何本地依赖，在 Windows、Linux、macOS 以及信创操作系统上表现一致。底层提供流式的 `JSONPathQueryBuilder` 构建器与完整的表达式引擎：根节点与当前节点、属性访问、通配符、递归下降、数组下标、切片、过滤、算术与逻辑表达式，并支持将结果映射为 Java Bean。

无论是读取配置文件、处理 API 响应、进行数据转换，还是校验不可信数据，JQuick Path 都能提供一个小巧、可预测、零本地依赖的 Java 路径工具。

---

## 核心特性

- **流式查询构建器** —— `JSONPathQueryBuilder.from(...).document(...).limit(...).execute()`。
- **编程式路径构建** —— 通过 `JPath`、`JSegments`、`JSubscripts` 构建路径，无需手写字符串。
- **字符串路径表达式** —— 直接将 `$.store.books..[2]` 传给 `path(String)`。
- **根节点与当前节点** —— `$`（`JRoot.ROOT`）与 `@`（`JRoot.CURRENT`）。
- **段选择器** —— 属性访问、通配符 `*`、递归下降 `..`、下标段与递归下标。
- **下标选择器** —— 数字下标、负数下标、通配符、对象属性 `['title']`、切片 `[start:end:step]`、过滤 `[?(...)]` 与算术表达式下标。
- **丰富的谓词库** —— `eq`、`ne`、`gt`、`ge`、`lt`、`le`、`like`、`regex`、`startsWith`、`endsWith`、`contains`、`in`、`exists`、`notExists`、`and`、`or`、`not` 以及原始 `custom` 表达式。
- **完整的过滤表达式引擎** —— 比较、逻辑、算术、`in`、取非、正则以及 `@.length()` 这类嵌套函数调用。
- **多种输入来源** —— JSON 字符串、`Map`、POJO 或 `JSONObject`。
- **结果处理** —— `JSONPathResult` 提供 `getRawData()`、`getAsList()`、`isList()`；`as()` / `asList()` 可将结果映射为 Java Bean。
- **排序与分页** —— `JSortBuilder.asc()/desc()` 排序，`limit()` / `skip()` 分页。
- **语法驱动解析** —— ANTLR4 语法配合专用错误监听器，语法错误提示清晰。
- **跨平台与信创适配** —— 纯 Java 字节码、无 JNI，支持 Windows / Linux / macOS 及麒麟、统信 UOS、openEuler 等国产操作系统。
- **遍历有界** —— 对不可信 JSON 的递归下降受 `limit()` / `skip()` 约束，可防止结果无限膨胀，形成轻量的路径遍历防护。
- **注解支持** —— `@JSONPath`、`@JSONObject`、`@JSONArray`、`@JSONFilter`、`@JSONSort`、`@JSONFormat`。
- **易于扩展** —— `JValueContextFactory` 提供上下文取值，`JTemplateEngine` 提供模板求值。
- **Apache-2.0 开源协议** —— 可自由用于商业与个人项目。

---

## 快速开始

### Maven

```xml
<dependency>
  <groupId>io.github.paohaijiao</groupId>
  <artifactId>jquick-path</artifactId>
  <version>2.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'io.github.paohaijiao:jquick-path:2.0.0'
```

**[函数支持](https://github.com/paohaijiao/javelin?tab=readme-ov-file#jevaluator-function-reference "support function")**

### 基础语法

| 表达式 | 说明 |
|--------|------|
| **$** | 根对象 |
| **. 或 [ ]** | 子操作符，用于访问对象属性 |
| **...** | 递归下降，搜索所有子元素 |
| **\*** | 通配符，匹配所有对象或数组元素 |
| **[ ]** | 下标操作符，用于数组索引或过滤 |
| **[start:end:step]** | 数组切片操作 |
| **?()** | 过滤表达式 |
| **@** | 当前节点，用于过滤表达式 |

---

## 使用示例

- 参考数据

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

### 1. 根节点表达式

- **输入数据**：包含store对象，其下有books数组，数组内是包含title、author、price的书籍对象
- **路径表达式逻辑**：从根节点开始，依次访问store属性和books属性
- **Java代码逻辑**：使用JSONPathQueryBuilder，从根节点构建路径，指定store和books属性，设置limit为10后执行查询

```string
      JSONPathQueryBuilder.from(jsonObject)
         .document(JPath.fromRoot(JRoot.ROOT)
         .property("store").property("books"))
         .limit(10).execute();
```

- 等价路径表达式:$.store.books
- **输出结果**：books数组中的所有书籍对象

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
- **路径表达式逻辑**：递归方式搜索books数组下所有的price属性值
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
- **输出结果**：切片后的数组，包含索引为0的书籍对象（Book 1）

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
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.expr("1+1")指定加法表达式，执行查询

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("1+1"))))
    .limit(10)
    .execute();
```

- 等价路径表达式:$.books[1+1]
- **输出结果**：books数组中索引为2的书籍对象（Book 3）

### 18. 减法表达式

- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：通过表达式1-1计算索引，获取books数组对应索引的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.expr("1-1")指定减法表达式，执行查询

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("1-1"))))
    .limit(10)
    .execute();
 ```

- 等价路径表达式:$.books[1-1]
- **输出结果**：books数组中索引为0的书籍对象（Book 1）

### 19. 嵌套函数表达式

- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：通过函数@.length()获取books数组长度，再减1得到索引，获取对应元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.expr("(@.length())-1")指定包含函数的表达式，执行查询

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.expr("(@.length())-1"))))
    .limit(10)
    .execute();
```

- 等价路径表达式:$.books[(@.length())-1]
- **输出结果**：books数组的最后一个元素（Book 3）

### 20. 非表达式

- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：过滤出books数组中isbn为false的元素
- **Java代码逻辑**：构建路径到books数组后，使用JSubscripts.filter(JPredicate.custom("!@.isbn"))进行非逻辑过滤，执行查询

```string
 JSONPathQueryBuilder.from(jsonData)
    .document(JPath.fromRoot(JRoot.ROOT).property("books")
    .segment(JSubscriptSegment.of(JSubscripts.filter(JPredicate.custom("!@.isbn")))))
    .limit(10);
```

- 等价路径表达式:$.books[?(!@.isbn)]
- **输出结果**：isbn为false的书籍对象（Book 2）

### 21. 乘法表达式

- **输入数据**：同负数表达式的输入数据
- **路径表达式逻辑**：通过表达式1*1计算索引，获取books数组对应索引的元素
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

---

## 许可证

JQuick Path 基于 [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt) 发布。
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
