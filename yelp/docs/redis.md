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