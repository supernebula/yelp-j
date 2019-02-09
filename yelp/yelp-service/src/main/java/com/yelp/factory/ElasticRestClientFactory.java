package com.yelp.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ElasticRestClientFactory {

    private static String username;
    private static String password;
    private static String hostname;
    private static int port;

    @Value("${aliyun.elastic.username}")
    public void setUsername(String username) {
        ElasticRestClientFactory.username = username;
    }

    @Value("${aliyun.elastic.password}")
    public void setPassword(String password) {
        ElasticRestClientFactory.password = password;
    }

    @Value("${aliyun.elastic.hostname}")
    public void setHostname(String hostname) {
        ElasticRestClientFactory.hostname = hostname;
    }

    @Value("${aliyun.elastic.port}")
    public void setPort(int port) {
        ElasticRestClientFactory.port = port;
    }

    private static RestHighLevelClient restHighClient;

    /**
     * 建立连接
     */
    private static void init() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));

        restHighClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(hostname, port))
                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                            @Override
                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            }
                        })
        );
    }

    public static RestHighLevelClient getRestHighClient() {
        init();
        return restHighClient;
    }
}
