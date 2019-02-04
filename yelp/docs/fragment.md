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


