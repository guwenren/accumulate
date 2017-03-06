package com.guwr.accumulate.test.zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

import java.util.List;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.zookeeper.CuratorClientTest
 * Date         2017/1/3
 * Time         18:03
 * Description
 */
public class CuratorClientTest {
    /**
     * Zookeeper info
     */
    private static final String ZK_ADDRESS = "192.168.2.249:12181";
    private static final String ZK_PATH = "/zktest";

    public static void main(String[] args) {
        

        RetryNTimes retryNTimes = new RetryNTimes(10, 5000);
        // 1.Connect to zk
        CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, retryNTimes);
        client.start();
        System.out.println("zk client start successfully!");
        // 2.Client API test
        // 2.1 Create node
        String data = "hello";
        try {

            getPath(ZK_PATH, client);
            String path = client.create().creatingParentsIfNeeded().forPath(ZK_PATH, data.getBytes());
            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<String> getPath(String path, CuratorFramework curatorFramework) throws Exception {
        List<String> strings = curatorFramework.getChildren().forPath(path);
        System.out.println("strings = " + JSON.toJSONString(strings));
        return strings;
    }
}
//当前时间戳:1469938351000
//key:Goods:Todays:1469938351000  value:DB数据
//key:Goods:Todays:ing  value:1449538371020
//set Goods:Todays:ing  value:1469938351000
//Goods:Todays:value

