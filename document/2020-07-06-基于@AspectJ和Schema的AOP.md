# 基于`@AspectJ`和Schema的AOP

[toc]

## 一、`@AspectJ`热身

前置增强

```java
/**
 * @author lifei
 * @since 2020/6/28
 */
@Aspect
public class PreGreetingAspect {
    @Before("execution(* greetTo(..))")
    public void beforeGreeting(){
        System.out.println("How are you");
    }
}
```

目标对象

```java
/**
 * @author lifei
 * @since 2020/6/28
 */
public class NaiveWaiter implements Waiter {
    @Override
    public void greetTo(String clientName) {
        System.out.println("NaiveWaiter: greet to " + clientName);
    }

    @Override
    public void serveTo(String clientName) {
        System.out.println("NaiveWaiter: serving " + clientName);
    }
}
```

整合的三种方案：

-  编程的方式

```java
    @Test
    public void proxy(){
        Waiter waiter = new NaiveWaiter();
        AspectJProxyFactory factory = new AspectJProxyFactory();
        // 设置目标对象
        factory.setTarget(waiter);
        // 添加切面
        factory.addAspect(PreGreetingAspect.class);
        // 生成织入切面的代理对象
        Waiter proxy = factory.getProxy();
        proxy.greetTo("XiaoMing");
        proxy.serveTo("XiaoMing");
    }
```

- Spring配置的方式

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans-4.0.xsd">
<!--    目标Bean-->
    <bean id="waiter" class="com.hef.service.impl.NaiveWaiter"/>
<!--    使用@Aspect 注解的切面类-->
    <bean class="com.hef.aspectj.PreGreetingAspect"/>
<!--    自动代理创建器， 自动将@AspectJ注解切面类织入到目标Bean中 -->
    <bean class="org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator"/>
</beans>
```

- 使用基于Schema的AOP命名空间进行配置

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
         http://www.springframework.org/schema/aop
         http://www.springframework.org/schema/aop/spring-aop-4.0.xsd">
<!--    基于AspectJ切面的驱动器-->
    <aop:aspectj-autoproxy/>
<!--    目标Bean-->
    <bean id="waiter" class="com.hef.service.impl.NaiveWaiter"/>
<!--    使用@Aspect 注解的切面类-->
    <bean class="com.hef.aspectj.PreGreetingAspect"/>
</beans>
```

## 二、`@AspectJ`语法基础

### 2.1 切点表达式函数

### 2.2 在函数入参中使用通配符

### 2.3 逻辑运算符

### 2.4 不同增强类型

`@Before`、`@AfterReturning`、`@Around`、`@AfterThrowing`、`@After`、`@DeclareParents`（引介增强）

引介增强的示例：

```java
/**
 * @author lifei
 * @since 2020/7/6
 */
public class SmartSeller implements Seller {
    @Override
    public void sell(String goods) {
        System.out.println("There sell " + goods);
    }
}
```

```java
/**
 * @author lifei
 * @since 2020/7/6
 */
@Aspect
public class EnableSellerAspect {

    // 默认的接口实现
    @DeclareParents(value = "com.hef.service.impl.NaiveWaiter", defaultImpl = SmartSeller.class)
    public Seller seller; // 要实现的目标接口
}
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
         http://www.springframework.org/schema/aop
         http://www.springframework.org/schema/aop/spring-aop-4.0.xsd">
<!--    基于AspectJ切面的驱动器-->
    <aop:aspectj-autoproxy/>
<!--    目标Bean-->
    <bean id="waiter" class="com.hef.service.impl.NaiveWaiter"/>
<!--    使用@Aspect 注解的切面类-->
    <bean class="com.hef.aspectj.EnableSellerAspect"/>
</beans>
```

```java
    @Test
    public void waiterSellTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-aop-declare.xml");
        Waiter waiter = (Waiter) context.getBean("waiter");
        waiter.greetTo("XiaoHong");
        Seller seller = (Seller) waiter;
        seller.sell("apple");
    }
```

## 三、切点函数详解

### 3.1 `@annotation()`

表示标注了某个注解的所有方法。

### 3.2 `execution()` 最常用

方法匹配模式串

> execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>) <异常模式>?)

示例：

（1）通过方法签名定义切点

- `execution(public * *(..))` 匹配所有目标类的public方法；
- `execution(* *To(..))` 匹配目标类所有以To为后缀的方法；

（2）通过类定义切点

- `execution(* com.hef.Waiter.*(..))` 匹配Waiter接口的所有方法；

- `execution(* com.hef.Waiter+.*(..))` 匹配Waiter接口及其所有实现类的方法；

  > 不但匹配NativeWaiter的greetTo()这个在Waiter接口中定义的方法，还匹配NativeWaiter#joke()这个不在Waiter接口中定义的方法。

（3）通过类包定义切点

- `execution(* com.hef.*(..))` 匹配包下的所有类；
- `execution(* com.hef..*(..))` 匹配com.hef 包、子孙包下所有类的所有方法；
- `execution(* com..*.*Dao.find*(..))`匹配包名前缀为com的任何包下类名前缀为Dao的方法，方法名必须以find为前缀；

（4）通过方法如参定义切点

- `execution(* joke(String,int))`

  如果入参类型是`java.lang`包下的类，则可以直接使用类名；否则必须使用全限定类名，如：joke(java.util.List,int)

- `execution(* joke(String,*))` 第一个入参为String，第二个入参为int

- `execution(* joke(String,..))`第一个入参为String，后面可以有0个或多个入参，类型不限

- `execution(* joke(Object+))`有一个入参，且入参为Object类型或该类的子类。

### 3.3 `args()`

该参数接收一个类名，表示目标类方法入参对象是指定类（包含子类）时，切点匹配：

```java
@Before("args(com.hef.service.Fly)")
public void readyFly(){
    System.out.println("There is bird ready fly...");
}
```

`args(com.hef.service.Fly)`等价于`execution(* *(com.hef.service.Fly+))`，也等价于`args(com.hef.service.Fly+)`

### 3.4 `@args()`

该函数接收一个注解类的类名，当方法的运行时入参对象标注了指定的注解时，匹配切点。

- 在类的继承树中，注解点（子类）高于入参点（父类），则目标方法不可能匹配切点；
- 如果在类继承树中，注解点（父类）低于入参点（子类），则目标方法匹配切点；

### 3.5 `within()`

和`execution()`类似，不同的是`winthin()`所指定的连接点最小范围只能是类，而`execution()`所指定的连接点可以大到包，小到方法入参。所以从某种意义上说，`execution()`函数涵盖了`within()`函数。

`within(com.hef.service.Fly+)`

### 3.6 `@within()` 和`@target()`

只接受注解类名作为入参。`@target(M)` 匹配任意标注了`@M`的目标类，而`@within`匹配标注了`@M`的类及子孙类。

**`@within()`、`@target()`、	`@annotation`函数都是针对目标类而言的，而非针对运行时的引用类型而言的。**如果标注@M注解的是一个接口，则所有实现该接口的类并不匹配。

### 3.7 `target()`和`this()`

二者仅接受类名的入参，虽然可以带“+”通配符，但使用与不使用，效果完全一样。

`target(M)`匹配目标类的所有方法（目标类及其子孙类的所有方法）。

一般情况下，`target()`与`this()`等效。区别在引介增强时，`this()`能匹配到引入接口方法，而`target()`匹配不到。

