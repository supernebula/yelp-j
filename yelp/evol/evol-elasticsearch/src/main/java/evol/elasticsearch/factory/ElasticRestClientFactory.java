package evol.elasticsearch.factory;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.UnknownNamedObjectException;
//import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class ElasticRestClientFactory {

    private static String username;
    private static String password;
    private static String hostname;
    private static int port;

    @Resource(name="elastic.username")
    public static void setUsername(String username) {
        ElasticRestClientFactory.username = username;
    }

    @Resource(name="elastic.password")
    public static void setPassword(String password) {
        ElasticRestClientFactory.password = password;
    }

    //@Value("${elastic.hostname}")
    @Resource(name="elastic.hostname")
    public static void setHostname(String hostname) {
        ElasticRestClientFactory.hostname = hostname;
    }

    //@Value("${elastic.port}")
    @Resource(name="elastic.port")
    public static void setPort(int port) {
        ElasticRestClientFactory.port = port;
    }

    private static boolean inited = false;

    //TransportClient：
    //作为一个外部访问者，请求ES的集群，对于集群而言，它是一个外部因素。

    //NodeClient
    //作为ES集群的一个节点，它是ES中的一环，其他的节点对它是感知的。


    private static RestHighLevelClient restHighClient;
    //private static AdminClient adminClient;
    private static TransportClient transportClient;

    private static NodeClient nodeClient;


    /**
     * 建立连接
     */
//    private static void init() {
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY,
//                new UsernamePasswordCredentials(username, password));
//
//        restHighClient = new RestHighLevelClient(
//                RestClient.builder(new HttpHost(hostname, port))
//                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//                            @Override
//                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
//                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//                            }
//
//                        })
//        );
//    }

    /**
     * 建立连接
     */
//    private static void init() throws UnknownHostException {
//
//        if(inited == true) return;
//
//
//        //https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.3/transport-client.html
//        //https://discuss.elastic.co/t/prebuilttransportclient-doesnt-exist-in-my-6-3-0-library/156223
//
//        transportClient = new PreBuiltTransportClient(Settings.EMPTY)
//                .addTransportAddress(new TransportAddress(InetAddress.getByName(hostname), port));
//                //.addTransportAddress(new TransportAddress(InetAddress.getByName("host2"), 9300));
//
//
//        //adminClient = transportClient.admin();
//        inited = true;
//
//    }



    public static RestHighLevelClient getRestHighClient() throws UnknownHostException {
        //init();
        restHighClient = new RestHighLevelClient(RestClient.builder(new HttpHost(hostname, port)));
        return restHighClient;
    }

    /**
     * https://elasticsearch.cn/article/380
     * @return
     * @throws UnknownHostException
     */
    public static TransportClient getTransportClient() throws UnknownHostException {
        //transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                //.addTransportAddress(new TransportAddress(InetAddress.getByName("my-es1"), port));



//        byte[] addr = {127,0,0,1};
//        transportClient = new PreBuiltTransportClient(Settings.EMPTY)
//                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), port));
//        //.addTransportAddress(new TransportAddress(InetAddress.getByAddress(addr), port));

        Settings settings = Settings.builder().put("cluster.name", "elasticsearchtest").build();
        transportClient = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(new InetSocketAddress("127.0.0.1", 9300)));

        return transportClient;
    }

//    public static NodeClient getNodeClient() throws UnknownHostException {
//        nodeClient = new NodeClient(Settings.EMPTY, null).;
//        nodeClient.admin().indices()
//        return nodeClient;
//    }
}
