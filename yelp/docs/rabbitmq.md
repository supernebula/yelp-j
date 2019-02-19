# RabbitMQ

## install 

### 安装 Erlang

1. 下载 Erlang

由于RabbitMQ软件本身是基于Erlang开发的，所以想要运行RabbitMQ必须要先按照Erlang

[Erlang官网 http://www.erlang.org](http://www.erlang.org/)

[下载 http://www.erlang.org/downloads](http://www.erlang.org/downloads)

2. macOS安装Homebrew （如果需要brew）
    
    https://brew.sh/index_zh-cn.html
    https://www.jianshu.com/p/4e80b42823d5
    
    Mac Homebrew安装命令：
    ```bash
    ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
    ```

2. Erlang安装命令：

    For Homebrew on OS X: ```brew install erlang```

    ```bash
    $ brew install erlang
    -bash: where: command not found
    $ whereis brew
    ```



用到的Linux命令：

[whereis](http://man.linuxde.net/whereis) 




For MacPorts on OS X: ```port install erlang```

For Ubuntu and Debian: ```apt-get install erlang```

For Fedora: ```yum install erlang```

For FreeBSD: ```pkg install erlang```

```