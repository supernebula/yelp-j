package evol.elasticsearch.factory;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.*;

public class ElasticRestClientFactoryTest {

    private ElasticRestClientFactory esRestClientFactory;

    @Test
    public void getRestHighClient() throws IOException {
        RestHighLevelClient restHighClient = ElasticRestClientFactory.getRestHighClient();
        GetRequest getRequest = new GetRequest(".kibana", "doc", "config:6.3.2");
        try {
            GetResponse getResponse = restHighClient.get(getRequest);
            String message = getResponse.getField("buildNum").getValue();
            System.out.printf("Field buildNum is : %s", message);

        }catch (IOException exception){
            System.out.println(exception.getMessage());
            System.out.println(exception.getStackTrace());
        }



    }

    @Before
    public void before(){

    }

    @After
    public void after(){

    }


    @BeforeClass
    public static void beforeClass(){
        ElasticRestClientFactory.setHostname("localhost");
        ElasticRestClientFactory.setPort(9200);

    }

    @AfterClass
    public static void afterClass(){

    }
}