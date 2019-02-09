# Elasticsearch guide 6.3.2

1. [es官方网站](https://www.elastic.co/products/elasticsearch)    [https://www.elastic.co/products/elasticsearch](https://www.elastic.co/products/elasticsearch)
    
## 一、安装 



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

