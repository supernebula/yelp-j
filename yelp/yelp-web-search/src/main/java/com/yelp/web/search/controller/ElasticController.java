package com.yelp.web.search.controller;

//import com.yelp.search.service.ElasticsearchService;
import com.yelp.web.search.repository.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("search")
public class ElasticController {

    @Autowired
    private ElasticsearchService elasticsearchService;

    /**
     *
     * @param key
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @GetMapping("review/search")
    public Object designerSearch(String key,Integer page,Integer size) throws Exception {
        return elasticsearchService.reviewSearch(key, page, size);
    }
}
