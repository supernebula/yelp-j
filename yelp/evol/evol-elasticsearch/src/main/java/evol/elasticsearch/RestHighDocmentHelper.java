package evol.elasticsearch;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import evol.util.JSONUtil;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RestHighDocmentHelper {

    private RestHighLevelClient client;

    public RestHighDocmentHelper(RestHighLevelClient client){
        this.client = client;
    }


    /**
     * 增加记录
     * @param index
     * @param type
     * @param id
     * @param doc
     * @param <T>
     * @return
     */
    public <T> boolean IndexDocument(String index, String type, String id, T doc, Class<T> classT) throws IOException
    {
        IndexRequest request = new IndexRequest(index, type, id);
        String json = JSONUtil.parse2Json(doc, classT);
        request.source(json, XContentType.JSON);
        IndexResponse response = client.index(request);
        DocWriteResponse.Result result = response.getResult();
        return result == DocWriteResponse.Result.CREATED || result == DocWriteResponse.Result.UPDATED;
    }

    /**
     * 获取记录
     */
    public <T>  T GetDocument(String index, String type, String id, Class<T> classT) throws IOException
    {
        GetRequest getRequest = new GetRequest(index, type, id);
        GetResponse response = client.get(getRequest);
        String sourceStr = response.getSourceAsString();
        T obj = JSONUtil.parseObject(sourceStr, classT);
        return obj;
    }

    /**
     * 判断记录是否存在
     */
    public boolean ExistsDocument(String index, String type, String id) throws IOException
    {
        GetRequest getRequest = new GetRequest(index, type, id);
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean isExist = client.exists(getRequest);
        return isExist;
    }

    /**
     * 删除记录
     */
    public <T> boolean DeleteDocument(String index, String type, String id) throws IOException
    {
        DeleteRequest request = new DeleteRequest(index, type, id);
        DeleteResponse response = client.delete(request);
        DocWriteResponse.Result result = response.getResult();
        return result == DocWriteResponse.Result.DELETED || result == DocWriteResponse.Result.NOT_FOUND;
    }

    /**
     * 更新记录
     */
    public <T> boolean UpdateDocument(String index, String type, String id, T doc, Class<T> classT) throws IOException
    {
        UpdateRequest request = new UpdateRequest(index, type, id);
        String json = JSONUtil.parse2Json(doc, classT);
        request.doc(json, XContentType.JSON);
        UpdateResponse response = client.update(request);
        DocWriteResponse.Result result = response.getResult();
        return result == DocWriteResponse.Result.UPDATED;
    }

    /**
     * 搜索记录
     */
    //public <T> List<T> SearchDocument(Map<String, Object> fields, String index, String type, Class<T> classT) throws IOException
    public <T> long SearchDocument(Map<String, Object> fields, String index, String type, Class<T> classT) throws IOException
    {
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            searchSourceBuilder.query(QueryBuilders.termQuery(entry.getKey(), entry.getValue()));
        }

        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest);

        long total = searchResponse.getHits().totalHits;
        return total;


//        MatchQueryBuilder matchQueryBuilder = null;
//        searchSourceBuilder.
//        for (Map.Entry<String, Object> entry : fields.entrySet()) {
//            if(matchQueryBuilder == null)
//                matchQueryBuilder = QueryBuilders.matchQuery(entry.getKey(), entry.getValue());
//            searchRequest.source()
//            matchQueryBuilder = matchQueryBuilder(entry.getKey(), entry.getValue());
//
//        }

    }

    /**
     * BulkRequest可用于使用单个请求执行多个索引，更新和/或删除操作
     */
    public boolean BulkDocument()
    {
        return false;

    }

    /**
     * BulkRequest可用于使用单个请求执行多个索引，更新和/或删除操作
     */
    public boolean MultiGetDocument()
    {

        return false;
    }



}
