package com.yelp.web.manage.controller.api;

import com.yelp.service.ElasticRestSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/esrest")
public class EsClientApiController {

    ElasticRestSevice esClient;

    @Autowired
    public EsClientApiController(ElasticRestSevice esClientService){
        esClient = esClientService;
    }

    /**
     * 插入文档
     */
    @RequestMapping("/save")
    public void saveUser() {
        ElasticPojo elasticPojo = new ElasticPojo();
        elasticPojo.setUser("kimchy");
        elasticPojo.setMessage("aaaaaaaaaaa");
        elasticPojo.setSex(2);
        String jsonString1 = JSON.toJSONString(elasticPojo);
        // 存对象的json
        esClient.saveByJson("my_index", "type_1", "5", jsonString1);
    }

    /**
     * 查询文档
     *
     * @return
     */
    @RequestMapping("/get")
    public String getUser() {
        try {
            // 查询条件
            QueryBuilder qb0 = QueryBuilders.termQuery("user", "kimchy");
            QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(qb0);

            Map<String, Object> map = esClient.searchByQuery("my_index", "type_1", 0, 0, queryBuilder);
            List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("list");
            return list.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除文档
     */
    @RequestMapping("/delete")
    public void deleteUser() {
        esClient.deleteById("my_index", "type_1", "4");
    }
}