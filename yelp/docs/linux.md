# linux

## Issue

Linux 设置代理时, 密码出现特殊字符怎么办?
https://www.cnblogs.com/qiumingcheng/p/7681753.html

如果直接输入 BASH 会报错: bash: !@172.16.2.17: event not found

解决办法 就是将特殊字符转换成 ASIIC 码形式输入, 以 % + Hex 形式(0x忽略).