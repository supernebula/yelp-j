# Elasticsearch guide 6.3.2

[es官方网站](https://www.elastic.co/products/elasticsearch)    [https://www.elastic.co/products/elasticsearch](https://www.elastic.co/products/elasticsearch)

## Installation Steps


1. [Download and unzip Elasticsearch](https://www.elastic.co/downloads/past-releases/kibana-6-3-2)

    [https://www.elastic.co/cn/downloads/elasticsearch](https://www.elastic.co/cn/downloads/elasticsearch)

2. Run bin/elasticsearch (or bin\elasticsearch.bat on Windows)

3. Run curl http://localhost:9200/ or Invoke-RestMethod http://localhost:9200 with PowerShell

4. Dive into the [getting started guide](https://www.elastic.co/guide/en/elasticsearch/reference/current/getting-started.html) and video. 

## 二、启动  

## 三、官方实例代码

java

```java

RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                    new HttpHost("localhost", 9200, "http")));
SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
searchSourceBuilder.query(QueryBuilders.matchAllQuery());            
searchSourceBuilder.aggregation(AggregationBuilders.terms("top_10_states").field("state").size(10));
SearchRequest searchRequest = new SearchRequest();
searchRequest.indices("social-*");
searchRequest.source(searchSourceBuilder);
SearchResponse searchResponse = client.search(searchRequest);

```

C#

```csharp

var client = new ElasticClient();
var searchResponse = client.Search<Tweet>(s => s
    .Index("social-*")
    .Query(q => q
        .Match(m => m
            .Field(f => f.Message)
            .Query("myProduct")
        )
    )
    .Aggregations(a => a
        .Terms("top_10_states", t => t
            .Field(f => f.State)
            .Size(10)
        )
    )
);

```

SQL

```sql

SELECT SCORE(), * FROM social-* 
   WHERE match(message, 'myProduct') 
   ORDER BY SCORE() DESC
SELECT state, COUNT(*) AS state_count FROM social-* 
   WHERE match(message, 'myProduct') 
   GROUP BY state LIMIT 10

```

## 四、使用

#1.  [集成阿里云Elasticsearch及RestHighLevelClient](https://blog.csdn.net/xiaoxudong666/article/details/83616518)

必要参考的两个文档：

1.阿里云Elasticsearch文档（比较粗糙）

https://help.aliyun.com/document_detail/57770.html?spm=a2c4g.11186623.6.542.66e63eb7ka2vhN

2.Elasticsearch官方API（建议使用谷歌浏览器翻译）

https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.3/java-rest-high-search.html

官方API上gradle、maven依赖都有，版本号与阿里云买的一致

3.[Maven Repository & Configuration](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.3/_maven_repository.html)

[https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.3/_maven_repository.html](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.3/_maven_repository.html)

```xml

<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-client-sniffer</artifactId>
    <version>6.3.2</version>
</dependency>

```

4. 参考博文：
   
   常用查询方法QueryBuilder构造举例API：
   
   [https://blog.csdn.net/u012546526/article/details/74184769](https://blog.csdn.net/u012546526/article/details/74184769)
   
   Map对象与JavaBean互转，List<Map>与List<JavaBean>互转：
   
   [https://blog.csdn.net/wyply115/article/details/50994477?utm_source=blogxgwz1](https://blog.csdn.net/wyply115/article/details/50994477?utm_source=blogxgwz1)

# ES可视化管理工具 Kibana 6.3.2

[官方：https://www.elastic.co/cn/downloads/kibana](https://www.elastic.co/cn/downloads/kibana)

## Installation Steps 

1. [Download and unzip Kibana ](https://www.elastic.co/downloads/past-releases/kibana-6-3-2)

2.  Open config/kibana.yml in an editor

    Set elasticsearch.url to point at your Elasticsearch instance
    
3. Run bin/kibana (or bin\kibana.bat on Windows)

4. Point your browser at http://localhost:5601 

## 五、基本教程

### 附录1：参考链接

1. [阮一峰：全文搜索引擎 Elasticsearch 基本教程](http://www.ruanyifeng.com/blog/2017/08/elasticsearch.html)

    http://www.ruanyifeng.com/blog/2017/08/elasticsearch.html

2. Search APIs 

    https://www.elastic.co/guide/en/elasticsearch/reference/current/search.html
    
    https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html
    
    https://www.elastic.co/guide/en/elasticsearch/reference/current/query-filter-context.html
   
3. ElasticSearch 索引查询使用指南——详细版

    https://www.cnblogs.com/pilihaotian/p/5830754.html
    

### 附录2：基本工具对别 Shell vs Kinaba vs PostMan(浏览器)

#### 1. 示例1命令： 

    列出每个 Index 所包含的 Type

(1). shell (mac、linux、unix)

```jshelllanguage

#完整格式

$ curl -X GET 'localhost:9200/_mapping?pretty=true'

#如省略" -X GET "，默认GET请求

$ curl 'localhost:9200/_mapping?pretty=true'
```

(2). 浏览器

```
http://localhost:9200/_mapping?pretty=true
```

(3). Kibana (Dev Tools > Console)

```
GET /_mapping?pretty=true
```

(4)java伪代码 

```java

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import java.net.InetSocketAddress;

//elasticsearch.yml 配置 cluster.name: elasticsearchtest
Settings settings = Settings.builder().put("cluster.name", "elasticsearchtest").build();
TransportClient transportClient = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
IndicesAdminClient indicesAdminClient = transportClient.admin().indices();
GetMappingsResponse getMappingsResponse = indicesAdminClient.getMappings(new GetMappingsRequest()).get();
....





```

## ES基本操作

示例数据 Yelp_dataset


### 1. Index


#### 1.1 创建Index

##### Shell : Empty Index
请求：

```bash
$ curl -X PUT 'localhost:9200/yelp-business'
```
返回：
```javascript
    {
      "acknowledged":true,  //表示操作成功
      "shards_acknowledged":true
    }
```

##### Shell : 创建 yelp-business
请求：

```bash
$ curl -X PUT 'localhost:9200/yelp-business' -d '
{
  "mappings": {
    "business": {
      "properties": {
        "id": {
          "type": "text"
        },
        "name": {
          "type": "text",
          "analyzer": "standard",
          "search_analyzer": "standard"
        },
        "neighborhood": {
          "type": "text",
          "analyzer": "standard",
          "search_analyzer": "standard"
        },
        "address": {
          "type": "text",
          "analyzer": "standard",
          "search_analyzer": "standard"
        },
        "city": {
          "type": "text"
        },
        "state": {
          "type": "text"
        },
        "postal_code": {
          "type": "text"
        },
        "latitude": {
          "type": "float"
        },
        "longitude": {
          "type": "float"
        },
         "stars": {
           "type": "float"
         },
         "review_count": {
           "type": "integer"
         },
         "is_open": {
           "type": "integer"
         }
      }
    }
  }
}'
```


##### Shell : 创建 yelp-business
请求：

```bash
$ curl -X PUT 'localhost:9200/yelp-review' -d '
{
  "mappings": {
    "doc": {
      "properties": {
        "id": {
          "type": "keyword"
        },
        "stars": {
          "type": "integer"
        },
        "date": {
          "type": "date"
        },
        "text": {
          "type": "text",
          "analyzer": "standard",
          "search_analyzer": "standard"
        },
        "useful": {
          "type": "integer"
        },
        "funny": {
          "type": "integer"
        },
        "cool": {
          "type": "integer"
        },
        "business_id": {
          "type": "keyword"
        },
        "user_id": {
          "type": "keyword"
        }
      }
    }
  }
}'
```



返回：
```javascript
    {
      "acknowledged":true,  //表示操作成功
      "shards_acknowledged":true
    }
```




#### 1.2 删除Index

##### Shell: 
请求：

```bash
$ curl -X DELETE 'localhost:9200/yelp-business'
```
返回：
```javascript
    {
      "acknowledged":true  //表示操作成功
    }
```

## 2.记录

#### 2.1. 新增记录

##### Shell: 

（1）指定Id，PUT请求1：

```bash
$ curl -X PUT 'localhost:9200/yelp-business/business/--6MefnULPED_I942VcFNA' -d '
{
    "id": "--6MefnULPED_I942VcFNA",
    "name": "John's Chinese BBQ Restaurant",
    "neighborhood": "",
    "address": "328 Highway 7 E, Chalmers Gate 11, Unit 10",
    "city": "Richmond Hill",
    "state": "ON",
    "postal_code": "L4B 3P7",
    "latitude": 43.8409,
    "longitude": -79.3996,
    "stars": 3,
    "review_count": 30,
    "is_open": true
}' 
```
返回1：
```javascript
{
  "_index": "yelp-business",
  "_type": "business",
  "_id": "--6MefnULPED_I942VcFNA",
  "_version": 1,
  "result": "created",
  "_shards": {
    "total": 2,
    "successful": 1,
    "failed": 0
  },
  "_seq_no": 0,
  "_primary_term": 1
}
```


（2）未指定Id，POST请求1：

```bash
$ curl -X POST 'localhost:9200/yelp-business/business' -d '
{
    "id": "----7zmmkVg-IMGaXbuVd0SQ",
    "name": "Primal Brewery",
    "neighborhood": "",
    "address": "16432 Old Statesville Rd",
    "city": "Huntersville",
    "state": "NC",
    "postal_code": "28078",
    "latitude": 35.4371,
    "longitude": -80.8437,
    "stars": 4,
    "review_count": 42,
    "is_open": true
}' 
```
返回1：
```javascript
{
  "_index": "yelp-business",
  "_type": "business",
  "_id": "Fe5i62gBMYyY55i0-LfZ",
  "_version": 1,
  "result": "created",
  "_shards": {
    "total": 2,
    "successful": 1,
    "failed": 0
  },
  "_seq_no": 1,
  "_primary_term": 1
}
```

#### 2.2. 查看记录

##### Shell: 

请求:

```bash
$ curl -X GET 'localhost:9200/yelp-business/business/Fe5i62gBMYyY55i0-LfZ?pretty=true'

```
Id正确时，返回：
```javascript
{
  "_index": "yelp-business",
  "_type": "business",
  "_id": "Fe5i62gBMYyY55i0-LfZ",
  "_version": 1,
  "found": true,                        //true,查询成功
  "_source": {                          //_source字段返回原始记录
    "id": "----7zmmkVg-IMGaXbuVd0SQ",
    "name": "Primal Brewery",
    "neighborhood": "",
    "address": "16432 Old Statesville Rd",
    "city": "Huntersville",
    "state": "NC",
    "postal_code": "28078",
    "latitude": 35.4371,
    "longitude": -80.8437,
    "stars": 4,
    "review_count": 42,
    "is_open": true
  }
}
```

Id不正确，查不到数据，返回：
```javascript
{
  "_index": "yelp-business",
  "_type": "business",
  "_id": "Fe5i62gBMYyY55i0-LfZ00",
  "found": false                        //true,查询失败
}
```

#### 2.3. 删除记录


##### Shell: 

请求:

```bash
$ curl -X DELETE 'localhost:9200/yelp-business/business/Fu5r62gBMYyY55i0vbf7?pretty=true'

```
Id正确时，返回：
```javascript
{
  "_index": "yelp-business",
  "_type": "business",
  "_id": "Fu5r62gBMYyY55i0vbf7",
  "_version": 2,
  "result": "deleted",                  //deleted, 已删除
  "_shards": {
    "total": 2,
    "successful": 1,
    "failed": 0
  },
  "_seq_no": 3,
  "_primary_term": 1
}
}
```

Id不正确，查不到数据，返回：
```javascript
{
  "_index": "yelp-business",
  "_type": "business",
  "_id": "Fu5r6222222222234234234234",
  "_version": 9,
  "result": "not_found",                    ///not_found, Id不正确，未删除
  "_shards": {
    "total": 2,
    "successful": 1,
    "failed": 0
  },
  "_seq_no": 12,
  "_primary_term": 1
}
```


#### 2.4. 更新记录

##### Shell: 

更新记录就是使用 PUT 请求，重新发送一次数据。

（1）PUT请求1：

```bash
$ curl -X PUT 'localhost:9200/yelp-business/business/--6MefnULPED_I942VcFNA' -d '
{
    "id": "--6MefnULPED_I942VcFNA 0",
    "name": "John's Chinese BBQ Restaurant 0",
    "neighborhood": "0",
    "address": "328 Highway 7 E, Chalmers Gate 11, Unit 10 0",
    "city": "Richmond Hill 0",
    "state": "ON" 0,
    "postal_code": "L4B 3P7 0",
    "latitude": 43.84091,
    "longitude": -79.39961,
    "stars": 31,
    "review_count": 301,
    "is_open": false
}' 
```
返回1：
```javascript
{
  "_index": "yelp-business",
  "_type": "business",
  "_id": "--6MefnULPED_I942VcFNA",
  "_version": 2,
  "result": "updated",                  //updated，更新成功
  "_shards": {
    "total": 2,
    "successful": 1,
    "failed": 0
  },
  "_seq_no": 13,
  "_primary_term": 1
}
```



#### 3. 搜索

##### 3.1 返回所有数据

##### Shell: 



（1） GET请求1：

```bash
$ curl -X PUT 'localhost:9200/yelp-business/business/_search'
```
返回1：
```javascript
{
  "took": 18,                           //表示该操作的耗时（单位为毫秒）
  "timed_out": false,                   //表示是否超时
  "_shards": {
    "total": 5,
    "successful": 5,
    "skipped": 0,
    "failed": 0
  },
  "hits": {                             //表示命中的记录
    "total": 2,                         //返回记录数
    "max_score": 1,                     //最高的匹配程度
    "hits": [                           //返回的记录组成的数组
      {
        "_index": "yelp-business",
        "_type": "business",
        "_id": "Fe5i62gBMYyY55i0-LfZ",
        "_score": 1,                    //表示匹配的程序，默认是按照这个字段降序排列
        "_source": {
          "id": "----7zmmkVg-IMGaXbuVd0SQ",
          "name": "Primal Brewery",
          "neighborhood": "",
          "address": "16432 Old Statesville Rd",
          "city": "Huntersville",
          "state": "NC",
          "postal_code": "28078",
          "latitude": 35.4371,
          "longitude": -80.8437,
          "stars": 4,
          "review_count": 42,
          "is_open": true
        }
      },
      {
        "_index": "yelp-business",
        "_type": "business",
        "_id": "--6MefnULPED_I942VcFNA",
        "_score": 1,
        "_source": {
          "id": "--6MefnULPED_I942VcFNA",
          "name": "John's Chinese BBQ Restaurant",
          "neighborhood": "",
          "address": "328 Highway 7 E, Chalmers Gate 11, Unit 10",
          "city": "Richmond Hill",
          "state": "ON",
          "postal_code": "L4B 3P7",
          "latitude": 43.8409,
          "longitude": -79.3996,
          "stars": 3,
          "review_count": 30,
          "is_open": true
        }
      }
    ]
  }
}
```


##### 3.2 全文搜索

##### Shell: 



（1） 基本搜索，GET请求1：

```bash
$ curl -X PUT 'localhost:9200/yelp-business/business/_search'  -d '
{
    "query" : {
        "match" : {
            "name" : "Primal"       //指定的匹配条件是name字段里面包含"Primal"这个词
        }
    }
}'
```
返回1：
```javascript
{
  "took": 3,				//表示该操作的耗时（单位为毫秒）
  "timed_out": false,			//表示是否超时
  "_shards": {
    "total": 5,
    "successful": 5,
    "skipped": 0,
    "failed": 0
  },
  "hits": {				//表示命中的记录
    "total": 1,				//返回记录数
    "max_score": 0.80259144,		//最高的匹配程度
    "hits": [				//返回的记录组成的数组
      {
        "_index": "yelp-business",
        "_type": "business",
        "_id": "Fe5i62gBMYyY55i0-LfZ",
        "_score": 0.80259144,		//表示匹配的程序，默认是按照这个字段降序排列
        "_source": {
          "id": "----7zmmkVg-IMGaXbuVd0SQ",
          "name": "Primal Brewery",
          "neighborhood": "",
          "address": "16432 Old Statesville Rd",
          "city": "Huntersville",
          "state": "NC",
          "postal_code": "28078",
          "latitude": 35.4371,
          "longitude": -80.8437,
          "stars": 4,
          "review_count": 42,
          "is_open": true
        }
      }
    ]
  }
}
```

（2） Elastic 默认一次返回10条结果，可以通过size字段改变这个设置，GET请求：


```bash
$ curl -X PUT 'localhost:9200/yelp-business/business/_search'  -d '
{
    "query" : {
        "match" : {
            "name" : "软件 系统"       //逻辑运算, 软件 or 系统
        }
    },
    "from": 1,                      //指定位移, 默认是从位置0开始
    "size": 1                       //返回10条结果, 表示返回的条数，默认10条
}'
```

```bash
$ curl -X PUT 'localhost:9200/yelp-business/business/_search'  -d '
{
    "query" : {
        "bool": {
              "must": [
                { "match": { "desc": "软件" } },
                { "match": { "desc": "系统" } }
              ]
         }
    },
    "sort" : [                      //排序
      { "stars" : "asc" },          //按字段stars升序
      { "review_count" : "desc" }   //按字段review_count倒序
    ],
    "from": 1,                      //指定位移, 默认是从位置0开始
    "size": 1                       //返回10条结果, 表示返回的条数，默认10条
}'
```

返回1，无数据时：
```javascript

{
  "took": 1,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "skipped": 0,
    "failed": 0
  },
  "hits": {
    "total": 0,
    "max_score": null,
    "hits": []
  }
}

```




（3）










# 附录：调试-常见问题及解决

1. TransportClient 无法访问Elasticsearch问题： "None of the configured nodes are available"


https://blog.csdn.net/blueheart20/article/details/79546011
https://blog.csdn.net/lu_wei_wei/article/details/51263133

PreBuiltTransportClient

https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.3/transport-client.html
https://discuss.elastic.co/t/prebuilttransportclient-doesnt-exist-in-my-6-3-0-library/156223

TransportClient vs NodeClient

https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.3/client.html#client

如何使用Elasticsearch Node Client Java连接到远程服务器
https://codeday.me/bug/20190105/481377.html
https://my.oschina.net/claireliu/blog/464215

IndicesAdminClient

https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.3/java-admin-indices.html

GetMappingsRequest 支持 Es6.4+

https://www.programcreek.com/java-api-examples/?api=org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest


```
配置elasticsearchyml 的cluster.name，并在代码中设置

```

2. 

2. InetAddress类相关

[InetAddress类概述与实例   : https://blog.csdn.net/swt369/article/details/77866386](https://blog.csdn.net/swt369/article/details/77866386)
[InetAddress类之创建新的InetAddress对象:  https://my.oschina.net/fhd/blog/371997](https://my.oschina.net/fhd/blog/371997)


    
