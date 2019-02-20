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

3. Erlang安装命令：

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


### 安装rabbitMQ


```bash

evoldeMacBook-Pro:~ evol$ brew install rabbitmq
Updating Homebrew...

==> Homebrew has enabled anonymous aggregate formulae and cask analytics.
Read the analytics documentation (and how to opt-out) here:
  https://docs.brew.sh/Analytics

==> Auto-updated Homebrew!
Updated 1 tap (homebrew/core).
==> Updated Formulae
checkbashisms       eslint              flake8              topgrade

==> Downloading https://github.com/rabbitmq/rabbitmq-server/releases/download/v3
==> Downloading from https://github-production-release-asset-2e65be.s3.amazonaws
######################################################################## 100.0%
==> /usr/bin/unzip -qq -j /usr/local/Cellar/rabbitmq/3.7.12/plugins/rabbitmq_man
==> Caveats
Management Plugin enabled by default at http://localhost:15672

Bash completion has been installed to:
  /usr/local/etc/bash_completion.d

To have launchd start rabbitmq now and restart at login:
  brew services start rabbitmq
Or, if you don't want/need a background service you can just run:
  rabbitmq-server
==> Summary
🍺  /usr/local/Cellar/rabbitmq/3.7.12: 236 files, 13.8MB, built in 7 minutes 57 seconds
evoldeMacBook-Pro:~ evol$ 
evoldeMacBook-Pro:~ evol$ brew services start rabbitmq
==> Tapping homebrew/services
Cloning into '/usr/local/Homebrew/Library/Taps/homebrew/homebrew-services'...
remote: Enumerating objects: 17, done.
remote: Counting objects: 100% (17/17), done.
remote: Compressing objects: 100% (14/14), done.
remote: Total 17 (delta 0), reused 11 (delta 0), pack-reused 0
Unpacking objects: 100% (17/17), done.
Tapped 1 command (50 files, 62.9KB).
==> Successfully started `rabbitmq` (label: homebrew.mxcl.rabbitmq)
evoldeMacBook-Pro:~ evol$ 


```

### 启动RabbitMQ

```bash
## 进入安装目录
cd /usr/local/Cellar/rabbitmq/3.7.12
# 启动
brew services start rabbitmq

#重启
brew services restart rabbitmq
```

### 浏览器访问 RabbitMQ

```aidl
http://localhost:15672

默认账号：guest 密码：guest
```


##. Spring boot 集成 RabbitMQ

[SpringBoot RabbitMQ support](https://docs.spring.io/spring-boot/docs/1.5.19.RELEASE/reference/htmlsingle/#boot-features-rabbitmq)

[简书 Spring boot集成RabbitMQ](https://www.jianshu.com/p/6ca34345b796)

[spring RabbitMQ RabbitTemplate](https://spring.io/guides/gs/messaging-rabbitmq/)

[消息队列之 RabbitMQ](https://www.jianshu.com/p/79ca08116d57)
