# Spring SpEL

[toc]

## 一、JVM动态语言

作用：表达式语句的动态解析。

> 项目中的实践：在项目开发中，遇到一种场景，需要根据公式计算数据，而涉及到很多公式，并且公式可能会有变动。因此，在开发中我就使用到了动态解析这种方案——将计算公式存到表中。能够非常灵活地计算出结果。

Java6.0 中内置集成了Mozila Rhino的JavaScript解析引擎，因此可以很方便地在Java中调用JavaScript函数。

动态求和函数：

```java
    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        String scriptText = "function sum(a,b){ return a+b;}";
        engine.eval(scriptText);
        Invocable invocable = (Invocable) engine;
        Object result = invocable.invokeFunction("sum", 100, 99);
        System.out.println(result instanceof Double);
        System.out.println(result);
    }
```

## 二、SpEL表达式

对于仅仅需要一些简单的表达式需求的场景，使用脚本语言显得有些“笨重”，这就是介绍SpEL表达式的原因。

- SpEL具有显式方法调用和基本字符串模板函数等特性；

- SpEL不直接依赖于Spring框架，可以独立使用；

  >  可以将SpEL作为独立的动态语言使用

使用前需要引入`spring-expression`:

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-expression</artifactId>
    <version>${spring.version}</version>
</dependency>
```

```java
public static void main(String[] args) {
    ExpressionParser parser = new SpelExpressionParser();
    Expression expression = parser.parseExpression("'hello' + ' world'");
    String message= (String) expression.getValue();
    System.out.println(message);
}
```

## 三、SpEL的核心接口

### 3.1 `ExpressionParser`

#### (1) 字符串的拼接

```java
private static void testStrConcat(){
    ExpressionParser parser = new SpelExpressionParser();
    Expression expression = parser.parseExpression("'HelloWorld'.concat('!')");
    String value = (String)expression.getValue();
    System.out.println(value);
    System.out.println(expression.getValue(String.class));
}
```

### 3.2 `EvaluationContext`

#### (2) 针对特定实例对象的属性进行求值

创建`StandardEvaluationContext`实例时，指定一个跟对象作为求值的目标对象。

```java
private static void objPropertiesValue(){
    User user = new User();
    user.setUserName("XiaoMing");
    user.setCredits(100);
    ExpressionParser parser = new SpelExpressionParser();
    EvaluationContext context = new StandardEvaluationContext(user);
    Object userName = parser.parseExpression("userName").getValue(context);
    System.out.println(userName);
}
```

### 3.3 `SpelCompiler`可以将字节码直接编译成字节码

- 由于`SpelCompiler`直接将表达式编译成字节码，避免了每次调用时的语法解析消耗

```java
    public static void main(String[] args) {
        User user = new User();
        // 创建解析配置
        SpelParserConfiguration configuration = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE,
                CompilerSample.class.getClassLoader());
        // 创建解析器
        SpelExpressionParser parser = new SpelExpressionParser(configuration);

        // 创建获取上下文
        EvaluationContext context = new StandardEvaluationContext(user);

        // 表达式
        String expression = "isVipMember('tom') && isVipMember('pip')";

        // 解析表达式
        Expression spELExpression = parser.parseRaw(expression);

        // 通过表达式求值
        System.out.println(spELExpression.getValue(context));
        System.out.println(spELExpression.getValue(context));
    }
```



## 四、SpEL表达式

### 4.1 文本字符解析

文本表达式支持字符串、日期、数字（正数、实数及十六进制数）、布尔类型及null。

```java
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        String helloWorld = parser.parseExpression("'Hello World'").getValue(String.class);

        double doubleNumber = (Double) parser.parseExpression("6.0221415E+23").getValue();
        System.out.println(doubleNumber);// 6.0221415E23
        
    }
```

### 4.2 对象属性解析

使用类似“xxx.yyy.zzz”的对象属性路径轻松访问对象属性值。

### 4.3 数组、集合类型解析

在SpEL中，支持数组、集合类型（Map、List）的解析。

### 4.4 方法解析

- 支持Java可访问方法。私有方法是不能调用的；
- 可以调用String类型的所有可访问的方法；

### 4.5 操作符解析

#### （1） 关系操作符

`> >= < <= ==`、正则表达式、`instanceof`操作符

#### （2）逻辑操作符

`and 或 &&`、`or 或 ||`、非操作`!`

#### （3）算术运算操作符

- 加法运算可用于数字、字符串、日期；
- 减法运算可用于数字和日期；
- 乘法和除法运算符仅用于数字；
- 还支持`%`、指数幂`^`

### 4.6 安全导航运算符（避免了空指针对象验证）

格式为在“.”前面添加一个“?”

如果user为空，则直接返回null，如果不为空，则获取属性值。

```
"user?.userName"
```

### 4.7 三元运算符

```java
    public static void main(String[] args) {
        User user = new User();
        user.setUserName("Tom");

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(user);

        String result = parser.parseExpression("userName=='Tom'?'hello '+userName:'not Tom'").getValue(context, String.class);
        System.out.println(result);
    }
```

### 4.8 `Elvis`运算符

`<var>?:<value>` 如果左边变量取值为null，就取value值，否则就取var变量自身的值。

```java
       user.setUserName(null);
        String elvisValue = parser.parseExpression("userName?:'empty Username'").getValue(context, String.class);
        System.out.println(elvisValue);
```

### 4.9 赋值、类型、构造器、变量

**赋值**

```java
    public static void main(String[] args) {
        User user = new User();
        user.setUserName("Tom");
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(user);
        // 通过 setValue赋值
        parser.parseExpression("userName").setValue(context, "XiaoMing");
        System.out.println(user.getUserName());
        // 通过 表达式赋值
        parser.parseExpression("userName='xiaoHong'").getValue(context);
        System.out.println(user.getUserName());
    }
```

**类型**

- 如果加载的目标类位于java.lang包下，则可以不带包名；
- T操作符还可以直接调用类静态方法；

```java
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        Class stringClass = parser.parseExpression("T(java.lang.String)").getValue(Class.class);
        System.out.println(stringClass == String.class);

        //  直接调用静态方法
        Object randomValue = parser.parseExpression("T(java.lang.Math).random()").getValue();
        System.out.println(randomValue);
    }
```

**构造器**

```java
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        User value = parser.parseExpression("new com.hef.beans.User('aaa')").getValue(User.class);
        System.out.println(value);
    }
```

**变量**

```java
// 设置一个变量
EvaluationContext context = new StandardEvaluationContext(value);
context.setVariable("oneUserName", "Tom");
parser.parseExpression("userName=#oneUserName").getValue(context);
System.out.println(value.getUserName());
```

### 4.10 集合过滤

```java
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        // 过滤list
        List<Integer> valueList = new ArrayList<>();
        valueList.addAll(Arrays.asList(2,3,6,10,5,11,13));
        context.setVariable("list", valueList);
        List<Integer> value = (List<Integer>) parser.parseExpression("#list.?[#this>5]").getValue(context);
        System.out.println(value);
        // 过滤map
        Map<String, Integer> map = new HashMap<>();
        map.put("aa", 23);
        map.put("bb", 12);
        map.put("cc", 6);

        context.setVariable("map", map);
        // 过滤出子集
        Map<String, Integer> map1= (Map<String, Integer>) parser.parseExpression("#map.?[value>7]").getValue(context);
        System.out.println(map1);
        // 取第一个值
        Map<String, Integer> map2= (Map<String, Integer>) parser.parseExpression("#map.^[value>7]").getValue(context);
        System.out.println(map2);
        // 取最后一个值
        Map<String, Integer> map3= (Map<String, Integer>) parser.parseExpression("#map.$[value>7]").getValue(context);
        System.out.println(map3);
    }
```

### 4.11 集合转换

```java
        // 集合的转换
        List<Boolean> booleanList= (List<Boolean>) parser.parseExpression("#list.![#this>10]").getValue(context);
        System.out.println(booleanList);
```

## 五、在Spring中使用SpEL

### 5.1 基于XML配置

### 5.2 基于注解的配置

`#{properties['password']}`





