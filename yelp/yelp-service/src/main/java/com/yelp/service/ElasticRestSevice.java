package com.yelp.service;

import com.yelp.factory.ElasticRestClientFactory;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ElasticRestSevice {

    /**
     * 存json
     *
     * @param index
     * @param type
     * @param id
     * @param json
     */
    public void saveByJson(String index, String type, String id, String json) {

        try {
            RestHighLevelClient restHighClient = ElasticRestClientFactory.getRestHighClient();
            IndexRequest request = new IndexRequest(index, type, id)
                    .source(json, XContentType.JSON);
            IndexResponse indexResponse = restHighClient.index(request);
            restHighClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 存map
     *
     * @param index
     * @param type
     * @param id
     * @param map
     */
    public void saveByMap(String index, String type, String id, Map<String, Object> map) {

        try {
            RestHighLevelClient restHighClient = ElasticRestClientFactory.getRestHighClient();
            IndexRequest request = new IndexRequest(index, type, id)
                    .source(map);
            IndexResponse indexResponse = restHighClient.index(request);
            restHighClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按条件搜索
     *
     * @param index
     * @param type
     * @param queryBuilder
     * @param start
     * @param size
     * @return
     */
    public Map<String, Object> searchByQuery(String index, String type, int start, int size, QueryBuilder queryBuilder) {

        try {
            RestHighLevelClient restHighClient = ElasticRestClientFactory.getRestHighClient();
            Map<String, Object> resultMap = new HashMap<>();
            //搜索请求
            SearchRequest searchRequest = new SearchRequest(
                    index);
            searchRequest.types(type);
            //控制搜索行为
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            if (queryBuilder != null) {
                searchSourceBuilder.query(queryBuilder);
            }
            if (start != 0 && size != 0) {
                searchSourceBuilder.from(start);
                searchSourceBuilder.size(size);
            }
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = restHighClient.search(searchRequest);
            //搜索返回文档
            SearchHits hits = searchResponse.getHits();
            long total = hits.getTotalHits();
            resultMap.put("total", total);

            List<Map<String, Object>> mapList = new ArrayList<>();
            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit : searchHits) {
                // 查询的结果 Map的形式
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                mapList.add(sourceAsMap);
            }
            resultMap.put("list", mapList);
            restHighClient.close();
            return resultMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据id删除
     *
     * @param index
     * @param type
     * @param id
     */
    public void deleteById(String index, String type, String id) {

        try {
            RestHighLevelClient restHighClient = ElasticRestClientFactory.getRestHighClient();
            DeleteRequest request = new DeleteRequest(index, type, id);
            DeleteResponse deleteResponse = restHighClient.delete(request);
            restHighClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
