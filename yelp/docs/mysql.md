# mysql


## Issue


1. OSX Warning the user/local/mysql/data directory is not owned by the mysql or _mysql user

解决方案
```bash
$ sudo chown -RL root:mysql /usr/local/mysql
$ sudo chown -RL mysql:mysql /usr/local/mysql/data
$ sudo /usr/local/mysql/support-files/mysql.server start
```
