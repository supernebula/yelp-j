# Redis

# install

[https://redis.io/download](https://redis.io/download)

```jshelllanguage
$ wget http://download.redis.io/releases/redis-5.0.3.tar.gz  //官方下载
$ tar xzf redis-5.0.3.tar.gz  //解压
$ mv redis-5.0.3 /usr/local/  //移动到目录
$ cd /usr/local/redis-5.0.3   //切换目录
$ sudo make test              //编译
$ sudo make install           //安装
$ cd src                      //切换到 redis-5.0.3/src
$ ./redis-server              //启动redis
```


# springboot + redis

1. [springboot之使用redistemplate优雅地操作redis](https://cloud.tencent.com/developer/article/1156496)

概述

    本文内容主要：
    关于spring-redis
    关于redis的key设计
    redis的基本数据结构
    介绍redis与springboot的整合
    sringboot中的redistemplate的使用

[SpringBoot使用Redis缓存](https://www.cnblogs.com/gdpuzxs/p/7222309.html)