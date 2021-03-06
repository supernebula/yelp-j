# Pager

## 1. spring boot + mybatis 的默认自动分页

配置方式：

1. 在pom.xml引用如下：

```xml

```

2. 在application.properties中配置如下：

```
#Mybatis-PageHelper, 配置
pagehelper.helper-dialect=mysql
pagehelper.reasonable=true
pagehelper.support-methods-arguments=true
pagehelper.params=count=countSql
```

代码中使用方式

```java

        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Business> list = businessMapper.selectByExample(example);
        PageInfo<Business> pageInfo = new PageInfo<Business>(list);
        return pageInfo;

```

## Spring-Boot Thymeleaf 模板嵌套布局
母版负责页面主框架,包括导航栏,侧边栏,footer等内容,还有公共css,js的引用.

子页负责页面content部分的内容,还可以单独引用css,js

例子如下:

母版  /templates/admin/dashboard
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
  <head>
    <!-- 母版head begin -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>layout 母版</title>
    母版title会被子页覆盖
    <link th:href="@{/css/bootstrap.min.css}" rel="stylesheet" />
    <!-- 母版head end -->
　　 子页head信息会按顺序出现在这里
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" >
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span> 
          </button>
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Menu1</a></li>
            <li><a href="#">Menu2</a></li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">MenuList</a></li>
            <li><a href="#">Menu1</a></li>
            <li><a href="#">Menu2</a></li>
          </ul>
        </div>
　　　　　　
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" layout:fragment="content" 子页定义的东西会被追加到母版容器div上,本例中子页定义了style会加到这> 
　　　　　　　　母版页这里可以放些示例内容,反正也会被子页覆盖,这里省略了
        </div>
      </div>
      <div class="row">
        <div th:replace="footer :: copy"></div>
      </div>
    </div>

    <script th:src="@{/js/jquery-1.12.4.min.js}"></script>
    <script th:src="@{/bootstrap-3.3.7-dist/js/bootstrap.min.js}"></script>

    <th:block layout:fragment="bottomscriptblock">这里是预留给子页单独引用js用的,th:block Thymeleaf不会渲染,放代码块最合适
　　　　th:block is a mere attribute container that allows template developers to specify whichever attributes they want. 
　　　　Thymeleaf will execute these attributes and then simply make the block dissapear without a trace.
　　</th:block>
  </body>
</html>

```

子页 /templates/admin/dict

 如下:
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
    xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout"
    layout:decorator="admin/dashboard">
    <!-- layout母版页文件路径-->

<head>
    <!-- 
    子页head,会排在母版页的后面
    <meta charset="utf-8" />    
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" /> 
    -->
    <title>子页title</title>
</head>
<body>

  <div layout:fragment="content" style="width:90%;">子页这里定义的东西会被添加到母版的div标签上
    <div>
                 子页内容
    </div>
  </div>
  <th:block layout:fragment="bottomscriptblock">
      <script th:src="@{/js/holder.min.js}"></script>
  </th:block>


</body>
</html>
```

子页的head内信息会按顺序出现在母版head信息之后,
```aidl
<title>比较特殊,子页会覆盖母版的<title>
```


子页单独需要引用的js,放到底部th:block块内部.



## 2. Validation failed for object  提交表单或get请求，参数字段类型是 Date

解决方法如下:

在形参后面加上BindingResult bindingResult后解决;

[springboot出错误Validation failed for object='employee'. Error count: 1](https://blog.csdn.net/luo609630199/article/details/81746192)

```java


@PostMapping("/emp")
public String addEmp(Employee employee, BindingResult bindingResult){
    //some statment
    return "redirect:/emps";


```


# Spring Boot CommandLineRunner

[Spring Boot CommandLineRunner和ApplicationRunner](https://www.jianshu.com/p/de7b0e124248)

[Spring Boot Reference Guide 23.8 Using the ApplicationRunner or CommandLineRunner](https://docs.spring.io/spring-boot/docs/1.5.19.RELEASE/reference/htmlsingle/)

#SpringBoot:  ApplicationEvent & Listener

[SpringBoot使用ApplicationEvent&Listener完成业务解耦](https://segmentfault.com/a/1190000011433514)


[EventBus VS Spring Event](https://www.cnblogs.com/shoren/p/eventBus_springEvent.html)


# ISSUE

cannot resovle symbol

在使用idea的过程中，遇到其中一个maven模块变成灰色，如下所示：

造成这个的原因可能是忽略了maven模块，可以尝试如下解决方法：在idea中maven的setting中找到ignored files,看右边的面板中是否将变灰的maven模块忽略了。我的模块变灰就是因为这个原因，如下图所示：