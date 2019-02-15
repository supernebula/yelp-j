package evol.elasticsearch;

import evol.elasticsearch.factory.ElasticRestClientFactory;
import evol.elasticsearch.model.Business;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.UUID;

import static org.junit.Assert.*;

public class RestHighDocmentHelperTest {

    private static RestHighLevelClient restHighClient;

    private RestHighDocmentHelper restDocHelper;

    @Test
    public void indexDocument()  throws IOException {
        Business model = new Business();
        model.setId(UUID.randomUUID().toString());
        model.setName("1111");
        model.setNeighborhood("111");
        model.setAddress("111");
        model.setCity("111");
        model.setState("111");
        model.setPostalCode("111");
        model.setLatitude(11.1f);
        model.setLongitude(22.2f);
        model.setStars(1f);
        model.setReviewCount(1);
        model.setIsOpen(1);

        boolean indexSuccess = restDocHelper.IndexDocument("yelp-business", "business", model.getId(), model, Business.class);
        Assert.assertTrue(indexSuccess);

    }

    @Test
    public void getDocument() {
    }

    @Test
    public void existsDocument() {
    }

    @Test
    public void deleteDocument() {
    }

    @Test
    public void updateDocument() {
    }

    @Test
    public void searchDocument() {
    }

    @Test
    public void bulkDocument() {
    }

    @Test
    public void multiGetDocument() {
    }


    @Before
    public void before()  throws UnknownHostException{

        RestHighLevelClient restHighClient = ElasticRestClientFactory.getRestHighClient();
        restDocHelper = new RestHighDocmentHelper(restHighClient);
    }

    @After
    public void after(){
        restDocHelper = null;
    }


    @BeforeClass
    public static void beforeClass()  throws UnknownHostException {
        ElasticRestClientFactory.setHostname("localhost");
        ElasticRestClientFactory.setPort(9200);

    }

    @AfterClass
    public static void afterClass(){

    }
}