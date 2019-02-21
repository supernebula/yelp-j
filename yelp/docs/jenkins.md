# jenkins

## 一、下载

http://mirrors.jenkins-ci.org/

本初使用wget命令下载

### 1. 

[mac安装wget](https://www.jianshu.com/p/f6b290710262)

[Mac安装wget](https://www.jianshu.com/p/c989655e9592)

下载wget http://ftp.gnu.org/gnu/wget/

```bash
$ brew install wget --with-libressl
```

安装过程可能会出现以下问题
```bash
==> make install
Error: The brew link step did not complete successfully
The formula built, but is not symlinked into /usr/local
Could not symlink share/info/wget.info
/usr/local/share/info is not writable.
You can try again using:
brew link wget
```

这个时候执行以下命令

```bash
$ sudo chown -R $(whoami) /usr/local
```

再调用
```bash
$ brew link wget
```
这时候不报错，就代表安装成功了


2. wget 下载




## 二、安装 

jenkins的Mac默认安装目录

```aidl
/Users/Shared/Jenkins
```

###  安装后，查看默认administrator密码
```bash
$ sudo vim /Users/Shared/Jenkins/Home/secrets/initialAdminPassword
```
1fa56c5f192c448785137998cb5ece1f

#### Instance Configuration

账号:

evol
12345678
supernebula@live.cn

Jenkins URL:

http://localhost:9090/

## 三、使用
 
 [官方文档: 开始使用Jenkins](https://jenkins.io/zh/doc/pipeline/tour/getting-started/)
 
 [官方文档: 用户手册](https://jenkins.io/zh/doc/book/getting-started/)
 
 [video：用Jenkins自动化搭建测试环境](https://www.imooc.com/learn/1008)
