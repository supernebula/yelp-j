package com.yelp.web.search.repository;


public interface ElasticsearchService {
    /**
     * 初始化索引
     */
    void initIndex() throws Exception;

    /**
     * 设计师搜索
     * @param key
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    Object reviewSearch(String key,Integer page,Integer size) throws Exception;
}
