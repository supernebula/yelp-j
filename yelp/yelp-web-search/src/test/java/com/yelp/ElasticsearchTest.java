//package com.yelp;
//
//import com.alibaba.fastjson.JSONObject;
//import com.yelp.web.search.repository.ElasticsearchService;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.index.query.MatchAllQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//public class ElasticsearchTest {
//
//    @Autowired
//    private TransportClient transportClient;
//    @Autowired
//    private ElasticsearchService elasticsearchService;
//    @Test
//    public void findAll() {
//        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
//        QueryBuilders.boolQuery().must().
//        SearchRequestBuilder search = transportClient.prepareSearch("yelp_reveiw").setQuery(matchAllQueryBuilder);
//        SearchResponse searchResponse = search.get();
//        System.out.println(JSONObject.toJSONString(searchResponse));
//    }
//
//    @Test
//    public void initIndex() throws Exception {
//        elasticsearchService.initIndex();
//    }
//}
//
