package com.yelp.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication
@RunWith(SpringJUnit4ClassRunner.class)//使用junit4进行测试
//@ContextConfiguration({"classpath*:application.test.properties"}) //加载配置文件,(1.4.0过期)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet() throws Exception {
        stringRedisTemplate.opsForValue().set("username", "zhangsan");
        System.out.println("key username value is:" + stringRedisTemplate.opsForValue().get("username"));
        Assert.assertEquals("zhangsan", stringRedisTemplate.opsForValue().get("username"));
    }

}
