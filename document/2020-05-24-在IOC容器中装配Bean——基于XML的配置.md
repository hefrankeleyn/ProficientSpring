# 在IOC容器中装配Bean——基于XML

[toc]

## 一、Spring容器的高层视图

Spring容器、Bean配置信息、Bean实现类及应用程序四者的相互关系：

1. **Spring容器**读取**Bean配置信息**，在Spring容器中生成一份相应的Bean配置注册表；
2. Spring容器根据Bean定义注册表**实例化Bean**；
3. 将Bean实例放到Spring容器中（Bean缓存池）；
4. **应用程序**使用Bean；

## 二、Spring支持的Bean配置方式

不同的配置方式在“质”上是基本相同的，只是存在“形”上的区别。

1. XML配置方式；
2. 基于注解的配置方式；
3. Java类的配置方式；
4. Groovy的配置方式；

## 三、基于XML的配置

对于基于XML的配置，Spring1.0的配置文件采用DTD格式，Spring2.0以后采用Schema格式。

> 基于Schema格式，可以让不同类型的配置拥有自己的命名空间，使得配置文件更具扩展性。

一些XML Schema的知识：

1. 默认命名空间：它没有空间名，用于Spring Bean的定义。

```xml
xmlns="http://www.springframework.org/schema/beans"
```

2. xsi标准命名空间：这个命名空间用于为每个文档中的命名空间指定相应的Schema样式文件，是W3C定义的标准命名空间。

```xml
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
```

3. 自定义命名空间

```xml
xmlns:aop="http://www.springframework.org/schema/aop"
```

`aop`是自定义命名空间的简称，`http://www.springframework.org/schema/aop`是命名空间的全称。

4. 在`xsi:schemaLocation`中为每个命名空间指定具体的Schema文件

### 2.1 命名空间定义的步骤

第一步：指定命名空间的缩略名和全名；

第二步：为每个命名空间指定对应的Schema文档格式的定义文件

## 2.2 Bean的命名

```xml
<bean id="car" class="com.hef.Car"/>
<bean name="car1" class="com.hef.Car"/>
```

- id在IOC容器中必须是唯一的，而且id的命名需要满足XML对id的命名规范（必须以字母开始...）；

- name属性没有字符上的限制，几乎可以使用任何字符；

- id和name都可以指定多个名字，名字之间可用逗号、分号或者空格进行分隔；

- Spring不允许出现两个相同的id的`<Bean>`，但却可以出现两个相同的name的`<Bean>`；

- 多个name相同的`<bean>`，通过`getBean(beanName)`获取Bean时，将返回后面生命的那个Bean，原因是后面的Bean覆盖了前面同名的Bean；

- 如果id和name都未指定，Spring自动将全限定类名作为Bean的名称

  ```java
  getBean("com.hef.Car");
  // 多个实现类相同的匿名<Bean>
  getBean("com.hef.Car#1");
  getBean("com.hef.Car#2");
  ```

## 四、依赖注入

- 属性注入
- 构造函数注入
- 工厂方法注入

### 4.1 属性注入

属性注入是指通过`setXxx()`方法注入Bean的属性火依赖对象。**单独使用属性注入要求Bean提供一个默认的构造函数。**但，如果属性注入和构造函数注入混合使用，就不必提供默认的构造函数。

> 如果不提供默认的构造函数，将会报错：No default constructor found

- **Spring只会检查Bean中是否有对应的Setter方法，至于Bean中是否有对应的属性成员则不做要求。**

- JavaBean关于属性命名的特殊规范：变量的前两个字母要么全部大写，要么全部小写。

  > `setIDCode(String iDCode) ;` 对应IDCode属性而非iDcode

示例：

```xml
<bean id="car" class="com.hef.Car">
        <property name="brand"><value>CF03</value></property>
        <property name="color"><value>Red</value></property>
        <property name="maxSpeed"><value>200</value></property>
</bean>
```

### 4.2 构造函数注入

- 按类型匹配入参；
- 按索引匹配入参；
- 联合使用类型和索引匹配入参；
- 通过自身类型反射匹配入参；

```xml
<bean id="car02" class="com.hef.Car">
   <constructor-arg index="0" type="java.lang.String">
       <value>DM01</value>
   </constructor-arg>
   <constructor-arg index="1" type="java.lang.String">
       <value>Green</value>
   </constructor-arg>
   <constructor-arg index="2" type="int">
       <value>200</value>
   </constructor-arg>
</bean>
```

### 4.3 属性注入和构造函数注入一起使用

当属性注入和构造函数注入同时使用的时候，默认的构造函数就不是必须提供的。

JavaBean：

```java
public class Car {
    private String brand;
    private String color;
    private int maxSpeed;
    public Car(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    // getter 和 setter 方法：
    ......
```

装配：

```xml
<bean id="car01" class="com.hef.Car">
   <constructor-arg type="int">
       <value>500</value>
   </constructor-arg>
   <property name="brand" value="CF001"></property>
</bean>
```

### 4.4 工厂方法注入

- 非静态工厂方法注入
- 静态工厂方法注入

```xml
<!--    非静态工厂工厂方法注入-->
    <bean id="carFactory" class="com.hef.factory.CarFactory"/>
    <bean id="hongQiCar" factory-bean="carFactory" factory-method="createHongQiCar"/>
<!--    静态工厂方法注入-->
    <bean id="btCar" class="com.hef.factory.CarStaticFactory" factory-method="createBTCar"/>
```

### 4.5 注入参数详解

#### （1）字面值

“字面值”指的是可用字符串表示的值

**`<![CDATA[]]>`**的作用是让XML解析器将标签中的字符串当作普通的文本对待。

一般情况下，XML解析器会忽略元素标签内部字符串的前后空格，但Spring却不会忽略元素标签内部字符串的前后空格。

#### （2）引用其他的Bean

```xml
<bean id="boss" class="com.hef.beans.Boss">
  <property name="car">
      <ref bean="btCar"/>
  </property>
</bean>
```

`<ref>`元素可以通过两个属性引用容器中的其他Bean：

- bean  引用同一容器或副容器的Bean，最常见；
- parent 应用副容器的Bean

```java
ClassPathXmlApplicationContext contextParent = new ClassPathXmlApplicationContext("beansParent.xml");
// 子容器，里面包含父容器
ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"}, contextParent);
Boss bossHongQiFromParent = context.getBean("bossHongQiFromParent", Boss.class);
bossHongQiFromParent.bossDesc();
```

#### （3）内部Bean

```xml
<bean id="bossInner" class="com.hef.beans.Boss">
    <property name="car">
        <bean class="com.hef.beans.Car">
            <constructor-arg type="int">
                <value>500</value>
            </constructor-arg>
            <property name="brand" value="InnerCar"></property>
        </bean>
    </property>
</bean>
```

#### （4）null值

```
<bean id="carNull" class="com.hef.beans.Car">
        <constructor-arg type="int">
            <value>500</value>
        </constructor-arg>
        <property name="brand"><null/></property>
    </bean>
```

#### （5） 级联属性

使用级联属性，必须改造原来的Bean， 为引用对象创造一个初始化对象。

```xml
<bean id="iniCarBoss" class="com.hef.beans.Boss">
    <property name="iniCar.brand"><value>InitBrand</value></property>
</bean>
```

#### （6）集合类型属性

- List

- Set

- Map

- Properties

- 集合合并

  > 允许子`<bean>`继承父`<bean>`

- 配置集合类型的Bean，而非集合类型的属性，可以通过`util`命名空间进行配置

### 4.6 简化配置方式

（1）字面值属性

```xml
<property name="maxSpeed" value="200"/>
```

（2）引用对象属性

```xml
<property name="car" ref="car"/>
```

（3）使用p命名空间

p命名空间没有对应的Schema定义文件，无须在`xsi:schemaLocation` 中为p命名空间指定Schema定义文件。

```xml
xmlns:p="http://www.springframework.org/schema/p"
```

### 4.7 自动装配

`autowire=""` ，Spring提供了四种自动装配类型：byName、byType、constructor、autodetect

## 五、方法注入

### 5.1 `lookup`方法注入

`lookup`方法·注入有一定使用范围，一般在希望通过一个singleton Bean 获取一个prototype Bean 时使用。

```xml
    <bean id="car" class="com.hef.injectfun.Car"
          p:brand="InjectCar"
          p:color="red"
          p:maxSpeed="200" scope="prototype"/>

    <bean id="magicBoss" class="com.hef.injectfun.MagicBoss">
        <lookup-method name="getCar" bean="car"/>
    </bean>
```

### 5.2 方法替换

```xml
    <bean id="boss1" class="com.hef.injectfun.Boss1">
        <replaced-method name="getCar" replacer="boss2"/>
    </bean>
    <bean id="boss2" class="com.hef.injectfun.Boss2"/>
```

用于替换他人的Bean必须实现`org.springframework.beans.factory.support.MethodReplacer`接口：

```java
public class Boss2 implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        Car car = new Car();
        car.setBrand("ADQ6");
        return car;
    }
}
```

## 六、`<bean>`之间的关系

### 6.1 继承

```xml
<!--    Bean的继承 -->
    <bean id="abstractCar" class="com.hef.injectfun.Car"
          p:brand="abstractCar" p:color="红色" p:maxSpeed="1024"
          abstract="true"/>
    <bean id="car05" class="com.hef.injectfun.Car" p:brand="C05" parent="abstractCar"/>
    <bean id="car06" class="com.hef.injectfun.Car" p:brand="C06" p:color="黑色" parent="abstractCar"/>
```

`<bean>`声明了`abstract="true"` 之后，Spring IoC容器不会实例化该Bean

### 6.2 依赖`depends-on`

```xml
<bean id="manager" class="com.hef.CacheManager" depends-on="sysInit"/>
<bean id="sysInit" class="com.hef.SysInit"/>
```

通过`depends-on`属性将sysInit指定为manager前置依赖的Bean。

**如果前置依赖于多个Bean，则可以通过逗号、空格或分号的方式创建Bean的名称。**

## 七、整合多个配置文件

```xml
<import resource="classpath:beans2.xml"/>
```

在应用层，提供一个整合的配置文件，通过`<import>`将各个模块整合起来。这样，在容器启动的时候，只需加载这个整合的配置文件即可。

## 八、Bean作用域

### 8.1 `singleton`作用域

单例。默认是singleton，所以无须显式指定。Spring的ApplicationContext容器在启动的时候，自动实例化所有Singleton的Bean并缓存于容器中。

如果用户不希望在容器启动时实例化Bean，则可以通过lazy-init属性进行控制。

### 8.2 `prototype`作用域

多例。在默认情况下，Spring容器在启动的时候不实例化prototype的Bean。此外，Spring容器将prototype的Bean交给调用者后，就不再管理它的生命周期。

### 8.3 与Web应用环境相关的Bean作用域

#### （1）额外配置

如果用户使用Spring的WebApplicationContext，则可使用另外3种Bean作用域：request、session、globalSession。

在使用这三个作用域之前，必须在Web容器中进行一些额外的配置：

低版本Web容器的配置：

```xml
<!--   用于启动request、session和globalSesion 作用域， 低版本Web容器（Servlet2.3之前）中的配置-->
    <filter>
        <filter-name>requestContextFilter</filter-name>
        <filter-class>org.springframework.web.filter.RequestContextFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>requestContextFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

高版本Web容器的配置：

```xml
    <!--   用于启动request、session和globalSesion 作用域， 高版本Web容器中的配置-->
    <listener>
        <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
    </listener>
```

#### （2）request 作用域

request作用域的Bean对应一个HTTP请求和生命周期。每次HTTP请求，Spring容器都会创建一个新的Bean，请求处理完后，就销毁Bean。

#### （3）session 作用域

Session中的所有HTTP请求都共享一个Bean，当HTTP Session结束后，实例才被销毁。

#### （4）globalSession 作用域

globalSession 作用域类似于session作用域，不过仅在Portlet的WEB应用中使用。如果不在Portlet Web应用环境下，globalSession作用域等价于session作用域。

### 8.4 作用域的依赖问题

如果想将Web相关的作用域的Bean注入singleton或prototype的Bean中，需要Spring AOP的帮助：

```xml
    <bean name="carRequest" class="com.hef.beans.Car">
        <constructor-arg type="int" value="200"/>
        <aop:scoped-proxy/>
    </bean>
    
    <bean name="boosRequest" class="com.hef.beans.Boss">
        <property name="car" ref="carRequest"/>
    </bean>
```

### 8.5 通过实现FactoryBean接口实例化Bean的逻辑

```xml
<bean id="car001" class="com.hef.beans.CarFactoryBean" p:carInfo="CA,Red,102"/>
```

