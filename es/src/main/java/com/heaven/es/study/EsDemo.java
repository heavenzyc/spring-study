package com.heaven.es.study;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by heaven.zyc on 2016/8/11.
 */
public class EsDemo {

    public static void main(String[] args) throws Exception{
//        clientQuery();
//        httpQuery();
//        indexFromMysql();
        search();
//        bulkCreateIndex();
    }

    private static void clientQuery() {
        TransportClient client = null;
        try {
            Settings settings = Settings.settingsBuilder().put("cluster.name", "heaven-app").build();
            client = TransportClient.builder().settings(settings).build();
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
            GetResponse response = client.prepareGet("mytest", "staff", "1").execute().actionGet();
            System.out.println(response.getSourceAsString());
            Map<String,Object> map = response.getSource();
            for (Map.Entry<String,Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "->" + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    private static void httpQuery() throws Exception{
        HttpURLConnection conn = null;
        DataOutputStream wr = null;
        BufferedReader br = null;
        StringBuilder resultBuilder = new StringBuilder();
        String line = null;
        try {
            // 1. Prepare url
            String url01 = "http://127.0.0.1:9200/mytest/staff/_search?pretty";

            // 2. Prepare query param
            //String queryParamJson = buildQueryParamByStr();

            // 3. Inject url
            URL url = new URL(url01);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("Content-Length",Integer.toString(queryParamJson.getBytes().length));
            conn.setRequestProperty("Content-Language", "en-US");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            // 4. Inject query param
            wr = new DataOutputStream (conn.getOutputStream());
//            wr.writeBytes(queryParamJson);

            // Connection failure handling
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            // 5. Get Response
            br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            while ((line = br.readLine()) != null) {
                resultBuilder.append(line);
                resultBuilder.append('\r');
            }
            System.out.println("result~~~" + resultBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(wr != null) {
                wr.close();
            }
            if(br != null) {
                br.close();
            }
            if(conn != null) {
                conn.disconnect();
            }
        }
    }

    private static void indexFromMysql() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-jdbc.xml");
        StoreService storeService = context.getBean("storeService", StoreService.class);
        EsClientUtils esClientUtils = context.getBean("esClientUtils", EsClientUtils.class);
//        UserService userService = context.getBean("userService", UserService.class);
        List<Map<String, Object>> storeList = storeService.getStoreList();
//        List<Map<String, Object>> userList = userService.getUser();
        for (Map<String, Object> map : storeList) {
//            System.out.println(JSON.toJSONString(map));
            esClientUtils.index("mystore", "store", JSON.toJSONString(map));
        }
    }

    private static void search() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-jdbc.xml");
        EsClientUtils esClientUtils = context.getBean("esClientUtils", EsClientUtils.class);
//        esClientUtils.search();
//        esClientUtils.mutliWordsSearch();
        esClientUtils.filterSearch();
    }

    private static void bulkCreateIndex() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-jdbc.xml");
        EsClientUtils esClientUtils = context.getBean("esClientUtils", EsClientUtils.class);
        esClientUtils.bulkIndex();
    }

}
