# Yelp

## [yelp for github ](https://github.com/Yelp)

## 表结构

https://www.yelp.com/dataset/documentation/main

https://www.yelp.com/dataset/documentation/photos

## yelp_db schema DDL

```sql

CREATE TABLE `attribute` (
  `id` varchar(36) NOT NULL,
  `business_id` varchar(22) NOT NULL COMMENT '商家编号',
  `name` varchar(255) DEFAULT NULL COMMENT '属性名称',
  `value` text COMMENT '属性值(字符串 或 JSON)',
  PRIMARY KEY (`id`),
  KEY `fk_table1_business_idx` (`business_id`),
  CONSTRAINT `fk_table1_business` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家属性';

```

```sql

CREATE TABLE `business` (
  `id` varchar(22) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '商家名称',
  `neighborhood` varchar(255) DEFAULT NULL COMMENT '街区',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `state` varchar(255) DEFAULT NULL COMMENT '州',
  `postal_code` varchar(255) DEFAULT NULL COMMENT '邮政编码',
  `latitude` float DEFAULT NULL COMMENT '纬度',
  `longitude` float DEFAULT NULL COMMENT '精度',
  `stars` float DEFAULT NULL COMMENT '星级',
  `review_count` int(11) DEFAULT NULL COMMENT '评论数量',
  `is_open` tinyint(4) DEFAULT NULL COMMENT '0：表示关闭，1：表示打开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家表';

```



```sql

CREATE TABLE `category` (
  `id` varchar(36) NOT NULL,
  `business_id` varchar(22) NOT NULL COMMENT '商家编号',
  `category` varchar(255) DEFAULT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`),
  KEY `fk_categories_business1_idx` (`business_id`),
  CONSTRAINT `fk_categories_business1` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家类别';

```


```sql

CREATE TABLE `checkin` (
  `id` varchar(36) NOT NULL,
  `business_id` varchar(22) NOT NULL COMMENT '商家编号',
  `date` varchar(255) DEFAULT NULL COMMENT '多个日期时间，半角逗号分隔',
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_checkin_business1_idx` (`business_id`),
  CONSTRAINT `fk_checkin_business1` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家签到表';

```

```sql

CREATE TABLE `elite_years` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(22) NOT NULL COMMENT '用户编号',
  `year` char(4) DEFAULT NULL COMMENT '精英年度，格式，例：2014',
  PRIMARY KEY (`id`),
  KEY `fk_elite_years_user1_idx` (`user_id`),
  CONSTRAINT `fk_elite_years_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='年度精英用户表';

```


```sql

CREATE TABLE `friend` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(22) NOT NULL COMMENT '用户编号',
  `friend_id` varchar(22) DEFAULT NULL COMMENT '朋友的用户编号',
  PRIMARY KEY (`id`),
  KEY `fk_friends_user1_idx` (`user_id`),
  CONSTRAINT `fk_friends_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户朋友表';

```


```sql

CREATE TABLE `hours` (
  `id` varchar(36) NOT NULL,
  `hours` varchar(255) DEFAULT NULL COMMENT '营业时间，格式：星期几|开始时间-结束时间，例如：Monday|10:00-21:00',
  `business_id` varchar(22) NOT NULL COMMENT '商家编号',
  PRIMARY KEY (`id`),
  KEY `fk_hours_business1_idx` (`business_id`),
  CONSTRAINT `fk_hours_business1` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家营业时间表';

```


```sql

CREATE TABLE `photo` (
  `id` varchar(22) NOT NULL,
  `business_id` varchar(22) NOT NULL COMMENT '商家编号',
  `caption` varchar(255) DEFAULT NULL COMMENT '标题',
  `label` varchar(255) DEFAULT NULL COMMENT '标签',
  PRIMARY KEY (`id`),
  KEY `fk_photo_business1_idx` (`business_id`),
  CONSTRAINT `fk_photo_business1` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='照片表';

```


```sql

CREATE TABLE `review` (
  `id` varchar(22) NOT NULL COMMENT '	\n',
  `stars` int(11) DEFAULT NULL COMMENT '星级',
  `date` datetime DEFAULT NULL COMMENT '日期',
  `text` text COMMENT '正文',
  `useful` int(11) DEFAULT NULL COMMENT '收到的有用票数',
  `funny` int(11) DEFAULT NULL COMMENT '收到的有趣票数',
  `cool` int(11) DEFAULT NULL COMMENT '收到的酷票数',
  `business_id` varchar(22) NOT NULL COMMENT '商家编号',
  `user_id` varchar(22) NOT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`),
  KEY `fk_reviews_business1_idx` (`business_id`),
  KEY `fk_reviews_user1_idx` (`user_id`),
  CONSTRAINT `fk_reviews_business1` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reviews_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

```


```sql

CREATE TABLE `tip` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(22) NOT NULL,
  `business_id` varchar(22) NOT NULL,
  `text` text,
  `date` datetime DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tip_user1_idx` (`user_id`),
  KEY `fk_tip_business1_idx` (`business_id`),
  CONSTRAINT `fk_tip_business1` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tip_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提示表（和评论类似，但文字更短）';

```

```sql

CREATE TABLE `user` (
  `id` varchar(22) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `review_count` int(11) DEFAULT NULL COMMENT '评论数量',
  `yelping_since` datetime DEFAULT NULL COMMENT '注册时间',
  `useful` int(11) DEFAULT NULL COMMENT '好友数量',
  `funny` int(11) DEFAULT NULL COMMENT '有趣投票数量',
  `cool` int(11) DEFAULT NULL COMMENT '酷投票数量',
  `fans` int(11) DEFAULT NULL COMMENT '粉丝数量',
  `average_stars` float DEFAULT NULL COMMENT '平均评分',
  `compliment_hot` int(11) DEFAULT NULL,
  `compliment_more` int(11) DEFAULT NULL,
  `compliment_profile` int(11) DEFAULT NULL,
  `compliment_cute` int(11) DEFAULT NULL,
  `compliment_list` int(11) DEFAULT NULL,
  `compliment_note` int(11) DEFAULT NULL,
  `compliment_plain` int(11) DEFAULT NULL,
  `compliment_cool` int(11) DEFAULT NULL,
  `compliment_funny` int(11) DEFAULT NULL,
  `compliment_writer` int(11) DEFAULT NULL,
  `compliment_photos` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

```
