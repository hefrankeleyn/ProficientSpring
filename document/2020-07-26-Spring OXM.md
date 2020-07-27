# Spring OXM

[toc]

## 一、认识XML解析技术

XML是一组规则和准则的集合，用来描述结构化数据。

- DTD 是一套关于标记符的语法规则，是XML文件的验证机制，属于XML文件组成的一部分。
- XML Schema 在很大程度上重构了XML 1.0 DTD的能力
- XML不仅仅是一种标记语言，而是一系列技术。

## 二、XML的处理技术

- DOM （Document Object Model）
- SAX （Simple API for XML）
- StAX
- XML数据绑定技术

DOM基于XML文档在内存中的树状结构。不足之处：将整个XML文档装入内存所引起的巨大内存开销。

SAX不要求将整个XML文件一起装入内存。它的想法十分简单，一旦XML处理器完成对XML元素的操作，它就立刻调用一个自定义事件处理器及时处理这个元素及相关数据。**虽然SAX解决了DOM速度慢、内存占用大的问题，但在灵活性方面受到很大制约，如无法随机访问文档。**

StAX把重点放在流上。

## 三、XStream

### 3.1 XStream

XStream是一套简洁易用的开源框架，用于将Java对象序列化城XML或者将XML反序列化为Java对象，是Java对象和XML之间的一个双向转换器。

### 3.2 XStream 结构组成

- Converters（转换器）
- I/O (输入/输出)
- Context （上下文引用）
- Facade（统一入口）

### 3.3 使用XStream别名

- 类别名：`alias(String name,Class type)`
- 类成员别名: `alias(String alias, Class definedIn, String fieldName)`
- 类成员作为属性别名: `aliasAttribute(Class definedIn, String attributeName, String alias)`、`useAttributeFor(Class definedIn, String fieldName)`

### 3.4 XStream转换器

有时默认的映射方式无法满足，需要转换一些自定义类型。

- 实现`Converter`接口
- 调用`xStream.registerConverter(Converter);`注册转换器

### 3.5 XStream注解

### 3.6 流对象

### 3.7 持久化API

### 3.8 额外功能：处理JSON

## 四、其他的O/X Mapping开源项目

### 4.1 JAXB

### 4.2 Castor

### 4.3 JiBX

### 4.4 总结比较

## 五、与Spring OXM 整合

### 5.1 Spring OXM概述

### 5.2 整合OXM实现者

