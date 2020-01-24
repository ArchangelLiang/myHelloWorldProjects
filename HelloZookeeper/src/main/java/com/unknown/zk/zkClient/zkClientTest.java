package com.unknown.zk.zkClient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class zkClientTest {

    public static void println(Object value){
        System.out.println(value);
    }

    public static void main(String[] args) throws InterruptedException {

        //创建连接
        ZkClient client = new ZkClient("192.168.43.145:2181", 30000);

        //zkClient提供级联创建节点的功能
//        client.createPersistent("/one/two/three",true);

        //级联删除
//        client.deleteRecursive("/one");

        //获取子节点
        List<String> children = client.getChildren("/");
        System.out.println(children);

        //注册watcher
        client.subscribeChildChanges("/", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                println("监听的节点 -> " + s + ",变化后子节点 = " + list);
            }
        });
        client.create("/decade","decade_data", CreateMode.PERSISTENT);

        TimeUnit.SECONDS.sleep(3);

        println("child count = " + client.countChildren("/"));
        client.close();
    }

}
