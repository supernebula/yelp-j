package com.yelp;

import com.github.pagehelper.PageInfo;
import com.yelp.entity.Business;
import com.yelp.entity.Review;
import com.yelp.service.BusinessService;
import com.yelp.service.ReviewService;
import evol.elasticsearch.RestHighDocmentHelper;
import evol.elasticsearch.factory.ElasticRestClientFactory;
import org.elasticsearch.client.RestHighLevelClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;

//import com.yelp.service.impl.BusinessServiceImpl;
//
//import java.util.List;

@SpringBootApplication
@MapperScan({"com.yelp.dao.mapper"})
public class YelpEsdataConsoleApplication {

    private static ConfigurableApplicationContext applicationContext = null;
    private final static String VERSION = "1.0";

    public static void main(String[] args) {
        System.out.println("version:" + VERSION);
        applicationContext = SpringApplication.run(YelpEsdataConsoleApplication.class, args);
        System.out.println("Yelp Elasticsearch Data Import  Console started:" + VERSION);
    }

    //https://blog.csdn.net/nihao12323432/article/details/81205288
    public static void stop() {
        if (applicationContext != null) {
            System.out.println("Yelp Elasticsearch Data Import  Console closing. version:" + VERSION);
            applicationContext.close();
            applicationContext = null;
            System.out.println("Yelp Elasticsearch Data Import  Console closed. version:" + VERSION);
        }

        System.exit(0);
    }

    //https://www.jianshu.com/p/de7b0e124248
    //@Bean
    public CommandLineRunner BusinessQueryCommandLineRunner(ApplicationContext ctx) {

        return args -> {
            System.out.println("commandLineRunner running!");
            System.out.println("ApplicationName:" + ctx.getApplicationName());
            BusinessService busiService = ctx.getBean(BusinessService.class);
            List<Business> list = busiService.getBusinesses(1, 2);
            int num = 0;
            for (Business item : list) {
                num++;
                System.out.println();
                System.out.printf("rowNumber: %d ,business, id:%s, name: %s, address: %s", num, item.getId(), item.getName(), item.getAddress());

            }
        };
    }


    /**
     *插入business到Es的 CommandLineRunner
     * @param ctx
     * @return
     * @throws IOException
     */
    public CommandLineRunner insertBusinessToElasticRunner(ApplicationContext ctx)   throws IOException {
        return args -> {
            System.out.println("commandLineRunner running!");
            System.out.println("ApplicationName:" + ctx.getApplicationName());
            BusinessService busiService = ctx.getBean(BusinessService.class);
            List<Business> list = busiService.getBusinesses(1, 2);
            insertBusinessToElasticsearch(list);
        };
    }


    /**
     * 插入business到Elasticsearch
     * @param list
     * @throws IOException
     */
    private void insertBusinessToElasticsearch(List<Business> list)  throws IOException {
        ElasticRestClientFactory.setHostname("localhost");
        ElasticRestClientFactory.setPort(9200);
        RestHighLevelClient restHighClient = ElasticRestClientFactory.getRestHighClient();
        RestHighDocmentHelper restDocHelper = new RestHighDocmentHelper(restHighClient);
        int num = 0;
        for (Business item : list) {
            num++;
            boolean isSuccess = restDocHelper.IndexDocument("yelp-business", "business", item.getId(), item, Business.class);
            System.out.println("insert isSuccess?:" + isSuccess);
            System.out.printf("rowNumber: %d ,business, id:%s, name: %s, address: %s", num, item.getId(), item.getName(), item.getAddress());
        }

    }



    public CommandLineRunner ReviewQueryCommandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("commandLineRunner running!");
            System.out.println("ApplicationName:" + ctx.getApplicationName());
            ReviewService reviewService = ctx.getBean(ReviewService.class);
            PageInfo<Review> pageInfo = reviewService.getPageReview(1, 100);
            List<Review> list = pageInfo.getList();

            System.out.println("Review Total:" + list.size());

            int num = 0;
            for (Review item : list) {
                num++;
                System.out.println();
                System.out.printf("rowNumber: %d ,Review, id:%s, date: %s, stars: %s", num, item.getId(), item.getDate(), item.getStars());

                if(num > 100)
                    break;
            }

            System.out.println("Review Total:" + list.size());
        };
    }



    /**
     *插入Review到Es的 CommandLineRunner
     * @param ctx
     * @return
     * @throws IOException
     */
    @Bean
    public CommandLineRunner insertReviewToElasticRunner(ApplicationContext ctx)   throws IOException {
        return args -> {
            System.out.println("Insert Review commandLineRunner running!");
            System.out.println("ApplicationName:" + ctx.getApplicationName());
            ReviewService reviewService = ctx.getBean(ReviewService.class);
            List<Review> list = reviewService.getAllReview();
            insertReviewToElasticsearch(list);
        };
    }


    /**
     * 插入 Review 到Elasticsearch
     * @param list
     * @throws IOException
     */
    private void insertReviewToElasticsearch(List<Review> list)  throws IOException {
        ElasticRestClientFactory.setHostname("localhost");
        ElasticRestClientFactory.setPort(9200);
        RestHighLevelClient restHighClient = ElasticRestClientFactory.getRestHighClient();
        RestHighDocmentHelper restDocHelper = new RestHighDocmentHelper(restHighClient);
        int num = 0;
        for (Review item : list) {
            num++;
            boolean isSuccess = restDocHelper.IndexDocument("yelp-review", "doc", item.getId(), item, Review.class);
            System.out.println("insert Review isSuccess?:" + isSuccess);
            System.out.printf("rowNumber: %d ,reveiw, id:%s, coll: %s, date: %s", num, item.getId(), item.getCool(), item.getDate());
            System.out.println();
        }

    }


}
