# CMS项目原型
由于经常需要搭建一些类似cms这样的系统，如果每次都是手动创建项目，创建模块，拷贝改，无疑是蛋疼的。
所以我需要做一个项目原型，每次需要搭建类似cms系统的时候，只需要一键即可生成！

## 一、简介
### 1. 效果图
![](https://kangyonggan.com/upload/cms-01.png)
![](https://kangyonggan.com/upload/cms-02.png)
![](https://kangyonggan.com/upload/cms-03.png)
![](https://kangyonggan.com/upload/cms-04.png)
![](https://kangyonggan.com/upload/cms-05.png)
![](https://kangyonggan.com/upload/cms-06.png)
![](https://kangyonggan.com/upload/cms-07.png)

### 2. 技术点
1. Spring
2. SpringMVC
3. Mybatis
4. Freemarker
5. Log4j2
6. Shiro
7. MBG
8. Ace Admin Ajax
9. jQuery
10. Bootstrap
11. MySQL

> 关于集成redis、mq、dubbo、Spring Batch、Activiti，会在后面慢慢推出，这只是一个基础版的。

### 3. 功能点
由于这只是一个项目原型，以后可能会用于各大场景，所以下面的功能只是一些最基础的。  
另外系统大量使用编译时注解代替aop以提升运行时性能，大量运用了freemerker的宏定义，把各个组件抽取出来以便复用。  
使用的是ace admin的单页面系统，内置了防重复提交等功能。

#### 登录
#### 工作台
1. 系统
    - 用户管理
    - 角色管理
    - 菜单管理
    - 字典管理
        - 字典类型
        - 字典类型
    - 偏好管理
2. 监控
    - 登录日志
    - 操作日志
3. 我的
    - 基本信息

## 二、生成项目
### 1. 依赖
1. Lombok插件
2. MySQL5.6+
3. JDK1.8+
4. Maven3+
5. Git

### 2. 拉取项目到本地 
```
git clone https://github.com/kangyonggan/cms-archetype.git
```

### 3. 编译并安装 
```
mvn clean install
```

### 4. 一键生成项目 
```
mvn archetype:generate -DarchetypeGroupId=com.kangyonggan -DarchetypeArtifactId=cms-archetype -DarchetypeVersion=1.0-SNAPSHOT -DarchetypeCatalog=local
```

## 三、启动项目
### 1. 执行初始化脚本`schema.sql`

### 2. 修改app.properties

### 3. 使用tomcat7插件启动

### 4. 访问localhost:8080

## 四、集成redis
### 1. 添加依赖
```
<dependency>
    <groupId>com.kangyonggan</groupId>
    <artifactId>app-redis</artifactId>
</dependency>
```

### 2. 添加redis配置
```
# redis
redis.host=127.0.0.1
redis.maxIdle=100
redis.maxTotal=1000
redis.minIdle=50
redis.password=123456
redis.port=6379
redis.prefix=demo
redis.testOnBorrow=true
```

### 3. 加载redis配置
```
<!--Redis缓存-->
<import resource="classpath*:applicationContext-redis.xml"/>
```

> 此配置文件是内置在app-redis中，只需要提供properties配置即可。