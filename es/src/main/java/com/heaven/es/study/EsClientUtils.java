package com.heaven.es.study;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

/**
 * Created by heaven.zyc on 2016/8/15.
 */
public class EsClientUtils {

    TransportClient client;

    private String clusterName;
    private String address;
    private int port;

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @PostConstruct
    public void init() {
        try {
            Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName).build();
            client = TransportClient.builder().settings(settings).build();
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(address), port));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("init es client failure");
        }
    }

    @PreDestroy
    public void destroy() {
        if (client != null) {
            client.close();
        }
    }

    private void printSearchResponse(SearchResponse response) {
        if (response == null) {
            System.out.println("data is null");
            return;
        }
        for (SearchHit hits : response.getHits().getHits()) {
            Map map = hits.getSource();
            System.out.println(JSON.toJSONString(map));
        }
    }

    public IndexResponse index(String index, String type, String json) {
        IndexResponse response = client.prepareIndex(index, type)
                .setSource(json)
                .get();
        return response;
    }

    public void bulkIndex() {
        try {
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            bulkRequest.add(client.prepareIndex("my_index", "tweet", "1").setSource(jsonBuilder().startObject().field("title", "The quick brown fox").field("age", 1).endObject()));
            bulkRequest.add(client.prepareIndex("my_index", "tweet", "2").setSource(jsonBuilder().startObject().field("title", "The quick brown fox jumps over the lazy dog").field("age", 2).endObject()));
            bulkRequest.add(client.prepareIndex("my_index", "tweet", "3").setSource(jsonBuilder().startObject().field("title", "The quick brown fox jumps over the quick dog").field("age", 3).endObject()));
            bulkRequest.add(client.prepareIndex("my_index", "tweet", "4").setSource(jsonBuilder().startObject().field("title", "Brown fox brown dog").field("age", 4).endObject()));
            BulkResponse bulkResponse = bulkRequest.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GetResponse get(String index, String type, String _id){
        GetResponse response = client.prepareGet(index, type, _id)
                .setOperationThreaded(false)
                .get();
        return response;
    }

    public SearchResponse search() {
//        QueryBuilder qb = matchAllQuery();
        QueryBuilder qb = termQuery("sap_id",1028);
        SearchResponse response = client.prepareSearch("mystore").setQuery(qb).execute().actionGet();
        printSearchResponse(response);
        return response;
    }

    public void mutliWordsSearch() {
//        QueryBuilder qb = matchQuery("title", "brown dog");
//        QueryBuilder qb = matchQuery("title", "brown dog").operator(MatchQueryBuilder.Operator.AND);
        QueryBuilder qb = matchQuery("title", "quick brown dog").minimumShouldMatch("75%");
        SearchResponse response = client.prepareSearch("my_index")
                .setQuery(qb).execute().actionGet();
        printSearchResponse(response);
    }

    public void filterSearch() {
//        QueryBuilder qb = termQuery("title", "dog");
//        QueryBuilder qb = rangeQuery("age").from(2).to(4);
//        QueryBuilder qb = rangeQuery("age").gt(2).lt(4);
//        QueryBuilder qb = rangeQuery("age").gte(2).lte(4);
//        QueryBuilder qb = existsQuery("age");
        QueryBuilder qb = prefixQuery("title", "brown");
        SearchResponse response = client.prepareSearch("my_index").setTypes("tweet")
                .setQuery(qb)
                .execute().actionGet();
        printSearchResponse(response);
    }





}
