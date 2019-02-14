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
    

### 附录2：基本工具对别 Shell vs Kinaba vs PostMan

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

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

RestHighLevelClient restHighClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200)));
GetRequest getRequest = new GetRequest(".kibana", "doc", "config:6.3.2");




```




## 1. 创建索引

## 2.1. 创建记录

## 2.2. 跟新记录

## 2.3. 查找记录

## 2.4. 删除记录

## 3. 搜索
    
