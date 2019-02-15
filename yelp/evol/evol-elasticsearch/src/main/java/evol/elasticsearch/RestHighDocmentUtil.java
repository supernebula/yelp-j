package evol.elasticsearch;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.index.Index;

public class RestHighDocmentUtil {

    /**
     * 增加记录
     */
    public static <T> boolean IndexDocument(String index, String type, String id, T doc)
    {
        IndexRequest request = new IndexRequest(index, type, id);
        return false;

    }

    /**
     * 获取记录
     */
    public static  <T>  boolean GetDocument(String id)
    {

        return false;
    }

    /**
     * 判断记录是否存在
     */
    public static boolean ExistsDocument(String id)
    {
        return false;

    }

    /**
     * 删除记录
     */
    public static <T> T DeleteDocument(String id)
    {
        return null;

    }

    /**
     * 更新记录
     */
    public static <T> boolean UpdateDocument(T doc)
    {
        return false;

    }

    /**
     * BulkRequest可用于使用单个请求执行多个索引，更新和/或删除操作
     */
    public static boolean BulkDocument()
    {
        return false;

    }

    /**
     * BulkRequest可用于使用单个请求执行多个索引，更新和/或删除操作
     */
    public static boolean MultiGetDocument()
    {

        return false;
    }



}
