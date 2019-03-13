# Junit

## JUnit加载properties配置文件

 1. 引入  import org.springframework.test.*;
 
 ```java
 
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

 ```
       
https://blog.csdn.net/somilong/article/details/48604015
    
https://blog.csdn.net/goligory/article/details/81737523


## Junit、Assert、内省、Properties类与配置文件的使用

https://blog.csdn.net/wust__wangfan/article/details/53164533

## SpringBoot Junit

1. [Spring-boot junit类读取config配置文件的注解](https://blog.csdn.net/qq_32447321/article/details/74183357)

```java
@SpringBootApplication
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/*@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml"})*/
public class Tests{
    @Test
    public void Test1(){
        //测试方法语句
    }
}
```



可以看到，对spring-boot项目进行单元测试是件容易的事儿，需要添加spring-boot-starter-test依赖，然后 使用@RunWith和@SpringBootTest或@SpringApplicationConfiguration(1.4.0过期)注解，然后引入自己要测试的bean，调用指定方法进行测试即可。
