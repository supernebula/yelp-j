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

import javax.annotation.Resource;

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

    static {

    }

    private static RestHighLevelClient restHighClient;

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
    private static void init() {

        restHighClient = new RestHighLevelClient(RestClient.builder(new HttpHost(hostname, port)));
    }



    public static RestHighLevelClient getRestHighClient() {
        init();
        return restHighClient;
    }
}
