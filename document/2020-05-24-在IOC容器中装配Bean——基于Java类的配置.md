# 在IoC容器中装配Bean——基于Java类的配置

[toc]

## 一、使用Java类提供Bean定义信息

普通的POJO只要标注`@Configuration`注解，就可以为Spring容器提供Bean定义的信息。每个标注了`@Bean`的类方法都相当于提供了一个Bean的定义信息。

```java
@Configuration
public class AppConf {
    @Bean
    public UserDao userDao(){
        return new UserDao();
    }
}
```

`@Configuration`注解，说明这个类可用于为Spring提供Bean的定义信息。该类的方法可以标注`@Bean`注解，Bean的类型由方法返回值的类型决定，**名称默认和方法名相同**。

- 由于`@Configuration`注解类本身已经标注了`@Component`注解，所以任何标注了`@Configuration`的类，本身也相当于标注了`@Component`，即它们可以像普通的Bean一样被注入其他Bean中；
- 将`AppConf`注入到其他的类中，调用`userDao()`方法的时候，不是简单地执行定义的方法逻辑，而是从Spring容器中返回相应Bean的单例。
- 还可以在`@Bean`处标注`@Scope`注解，以控制Bean的作用范围；
- 由于Spring容器会自动对`@Configuration`对类进行“改造”，以植入Spring容器对Bean的管理逻辑，所以使用基于Java类的配置必须保证将Spring aop类包和CGLIB类包加载到类路径下。

## 二、使用基于Java类的配置信息启动Spring容器

### 2.1 直接通过`@Configuration`类启动Spring容器

Spring提供了一个`AnnotationConfigApplicationContext`类，它能够直接通过标注`@Configuration`的Java类启动Spring容器。

```java
public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConf.class);
        UserDao userDao = context.getBean(UserDao.class);
        userDao.runUser();
    }
```

通过编码的方式加载多个@Configuration配置类：

```java
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
// 注册多个@Configuration配置类
context.register(AppConf.class);
context.register(UserConf.class);
// 刷新容器一应用这些注册的配置类
context.refresh();
```

### 2.2 `@Import`将多个配置类组装到一个配置类中

通过`@Import`将多个配置类组装到一个配置类中，这样仅需注册这个组装好的配置类就可以启动容器。

```java
@Configuration
@Import(UserConf.class)
public class AppConf {
    @Bean
    public UserDao userDao(){
        return new UserDao();
    }
}
```

### 2.3 通过XML配置文件引用`@Configuration`的配置

```java
<!--    通过XML配置文件引用 @Configuration 的配置-->
    <context:component-scan base-package="com.hef.conf" resource-pattern="UserConf.class"/>
```

### 2.4 `@ImportResource`通过`@Configuration`配置类引用XML配置信息

```java
@Configuration
@ImportResource("classpath:beans.xml")
public class UserConf {
    @Bean
    public User user(){
        return new User();
    }
}
```

