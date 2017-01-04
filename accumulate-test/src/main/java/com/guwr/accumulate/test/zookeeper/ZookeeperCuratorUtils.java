package com.guwr.accumulate.test.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.zookeeper.ZookeeperCuratorUtils
 * Date         2017/1/3
 * Time         16:47
 * Description
 */
public class ZookeeperCuratorUtils {
    public static void main(String[] args) throws Exception{
        String connectString = "192.168.2.249:12181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
        client.start();
        GetChildrenBuilder children = client.getChildren();
        System.out.println("children = " + children);
        List<String> strings = children.forPath("/");
        System.out.println("strings = " + strings);
    }
}
