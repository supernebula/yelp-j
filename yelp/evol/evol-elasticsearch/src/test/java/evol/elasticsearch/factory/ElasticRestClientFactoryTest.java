//package evol.elasticsearch.factory;
//
//import com.carrotsearch.hppc.ObjectLookupContainer;
//import com.carrotsearch.hppc.cursors.ObjectCursor;
//import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
//import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.client.IndicesAdminClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.cluster.metadata.MappingMetaData;
//import org.elasticsearch.common.collect.ImmutableOpenMap;
//import org.junit.*;
//
//import java.io.IOException;
//import java.net.UnknownHostException;
//import java.util.Iterator;
//import java.util.Map;
//
//import static org.junit.Assert.*;
//
///**
// * Indices APIs
// * https://www.elastic.co/guide/en/elasticsearch/reference/6.3/indices.html
// */
//public class ElasticRestClientFactoryTest {
//
//    private ElasticRestClientFactory esRestClientFactory;
//
//
//    /**
//     * Java REST Client [master] » Java High Level REST Client » Indices APIs » Get Mappings API
//     *
//     * Get Mappings API
//     *
//     * https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.3/client.html
//     * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/java-rest-high-get-mappings.html
//     */
//    @Test
//    public void getMappingTest()  throws UnknownHostException
//    {
//        TransportClient transportClient = ElasticRestClientFactory.getTransportClient();
//        IndicesAdminClient indicesAdminClient = transportClient.admin().indices();
//        try {
//            GetMappingsResponse getMappingsResponse = indicesAdminClient.getMappings(new GetMappingsRequest()).get();
//            ImmutableOpenMap<String, ImmutableOpenMap<String, MappingMetaData>> mappings =
//                    getMappingsResponse.getMappings();
//            Iterator<ObjectObjectCursor<String, ImmutableOpenMap<String, MappingMetaData>>>
//                    mappingIterator = mappings.iterator();
//            while (mappingIterator.hasNext()) {
//                ObjectObjectCursor<String, ImmutableOpenMap<String, MappingMetaData>>
//                        objectObjectCursor = mappingIterator.next();
//                //LOG.info("index: {}", objectObjectCursor.key);
//                System.out.printf("\n index:  %s", objectObjectCursor.key);
//                ImmutableOpenMap<String, MappingMetaData> immutableOpenMap = objectObjectCursor.value;
//                ObjectLookupContainer<String> keys = immutableOpenMap.keys();
//                Iterator<ObjectCursor<String>> keysIterator = keys.iterator();
//                while (keysIterator.hasNext()) {
//                    String type = keysIterator.next().value;
//                    //LOG.info("type: {}", type);
//                    System.out.printf("\n type:  %s", type);
//                    MappingMetaData mappingMetaData = immutableOpenMap.get(type);
//                    Map<String, Object> mapping = mappingMetaData.getSourceAsMap();
//                    if (mapping.containsKey("properties")) {
//                        Map<String, Object> properties = (Map<String, Object>) mapping.get("properties");
//                        for (String attribute : properties.keySet()) {
//                            //LOG.info("attribute: {}", attribute);
//                            System.out.printf("\n attribute:  %s", attribute);
//
//                        }
//                    }
//                }
//            }
//
//        }catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void getRestHighClientTest() throws IOException {
//        RestHighLevelClient restHighClient = ElasticRestClientFactory.getRestHighClient();
//        GetRequest getRequest = new GetRequest(".kibana", "doc", "config:6.3.2");
//        try {
//            GetResponse getResponse = restHighClient.get(getRequest);
//            String index = getResponse.getIndex();
//            String type = getResponse.getType();
//            String id = getResponse.getId();
//            //String message = getResponse.getField("buildNum").getValue();
//            System.out.printf("index : %s, type: %s, id: %s", index, type, id);
//
//        }catch (IOException exception){
//            System.out.println(exception.getMessage());
//            System.out.println(exception.getStackTrace());
//        }
//    }
//
//    @Before
//    public void before(){
//
//    }
//
//    @After
//    public void after(){
//
//    }
//
//
//    @BeforeClass
//    public static void beforeClass(){
//        ElasticRestClientFactory.setHostname("localhost");
//        ElasticRestClientFactory.setPort(9200);
//
//    }
//
//    @AfterClass
//    public static void afterClass(){
//
//    }
//}