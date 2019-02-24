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


Mac安装后的的配置说明文档
/Library/Documentation/Jenkins/command-line-preferences.html



内容如下：

```html

Jenkins command-line preferences

The Jenkins Wiki page tells you how to change settings like which port number Jenkins will listen to. These settings need to be specified on the command line when Jenkins is started.
Launch daemon

For the Mac, Jenkins is deployed as a launch daemon. (If you want to know more about launchd and daemons, see here and here)

The launch daemon picks up the command line options from a standard preferences file, /Library/Preferences/org.jenkins-ci.plist. If the file does not exist, built-in defaults are used. The preference files are manipulated using the standard utility defaults.
How To Use the defaults Command

To view all settings in the file, run: defaults read /Library/Preferences/org.jenkins-ci

To get the value of a single setting, run: defaults read /Library/Preferences/org.jenkins-ci SETTING

To set the value of a setting, run: defaults write /Library/Preferences/org.jenkins-ci SETTING VALUE

For more information, see man defaults
Supported Settings

The list of settings supported by the Jenkins launch daemon (see documentation):

    prefix
    httpPort
    httpListenAddress
    httpsPort
    httpsListenAddress 

Additionally, you can set also these:

    war (Full path name to jenkins.war file. Defaults to /Applications/Jenkins/jenkins.war)
    JENKINS_HOME (Full path to JENKINS_HOME directory where Jenkins keeps its files. Defaults to /Users/Shared/Jenkins)
    minHeapSize (Passed to java command-line -Xms parameter. Defaults to 256m on 64bit architectures and 64m on 32bit)
    heapSize (Passed to java command-line -Xmx parameter. Defaults to 512m on 64bit architectures and 128m on 32bit)
    minPermGen (Passed to java command-line -XX:PermSize parameter. Defaults to 256m on 64bit architectures and 64m on 32bit)
    permGen (Passed to java command-line -XX:MaxPermSize parameter. Defaults to 512m on 64bit architectures and 128m on 32bit) 


```


file:///Library/Documentation/Jenkins/command-line-preferences.html

Mac 环境下配置目录

/Library/Preferences/org.jenkins-ci.plist

#### 关闭和重启：Mac 下 jenkins

启动
```bash
sudo launchctl load /Library/LaunchDaemons/org.jenkins-ci.plist
```
关闭
```bash
sudo launchctl unload /Library/LaunchDaemons/org.jenkins-ci.plist
```

mac启动或关闭jenkins

#### Instance Configuration

账号:

evol
12345678
supernebula@live.cn

Jenkins URL:

http://localhost:9090/

#### 配置

更改访问端口号

```bash
$ cd /Users/evol
$ ls -a  //-a 列出所有文件，包括隐藏文件
```

## 三、使用
 
 [官方文档: 开始使用Jenkins](https://jenkins.io/zh/doc/pipeline/tour/getting-started/)
 
 [官方文档: 用户手册](https://jenkins.io/zh/doc/book/getting-started/)
 
 [video：用Jenkins自动化搭建测试环境](https://www.imooc.com/learn/1008)


## 附录

### 用到的Mac命令

#### 查看java版本

```bash
$ /usr/libexec/java_home -V
Matching Java Virtual Machines (1):
    1.8.0_192, x86_64:	"Java SE 8"	/Library/Java/JavaVirtualMachines/jdk1.8.0_192.jdk/Contents/Home

/Library/Java/JavaVirtualMachines/jdk1.8.0_192.jdk/Contents/Home
```

#### 查看maven版本

```bash
$ mvn -v
Apache Maven 3.6.0 (97c98ec64a1fdfee7767ce5ffb20918da4f719f3; 2018-10-25T02:41:47+08:00)
Maven home: /program/apache-maven-3.6.0
Java version: 1.8.0_192, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk1.8.0_192.jdk/Contents/Home/jre
Default locale: zh_CN, platform encoding: UTF-8
OS name: "mac os x", version: "10.12.6", arch: "x86_64", family: "mac"

```

#### 安装docker

[MacOS Docker 安装 ](http://www.runoob.com/docker/macos-docker-install.html)

```bash
$ brew cask install docker
```