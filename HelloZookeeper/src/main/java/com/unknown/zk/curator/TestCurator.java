package com.unknown.zk.curator;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCurator {

    private static final String connectString = "192.168.43.145:2181";

    public static void main(String[] args) throws Exception {

        //创建会话的方式一
//        CuratorFramework curatorFramework = CuratorFrameworkFactory
//                .newClient(connectString, 5000, 5000,
////                        首次连接1000秒的超时时间，之后每次都会叠加时间，最大重连3次
//                        new ExponentialBackoffRetry(1000, 3));
//        curatorFramework.start();//启动连接
//        创建会话的方式二
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(connectString)
                .connectionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorFramework.start();
        System.out.println("connecting success");
        //创建节点,支持级联创建
        String result = curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .forPath("/curator/one/two/three/four", "curator_data".getBytes());
        System.out.println(result);

        //删除节点，支持级联删除
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator");

        //查询节点
        Stat stat = new Stat();
        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/decade");
        System.out.println(new String(bytes));
        System.out.println(stat);

        //更新节点数据
        Stat stat1 = curatorFramework.setData().forPath("/decade", "zio_and_decade".getBytes());
        System.out.println(stat1);

        //异步操作
        ExecutorService service = Executors.newFixedThreadPool(1);
        CountDownLatch latch = new CountDownLatch(1);
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println(Thread.currentThread().getName() + " -> resultCode : " + curatorEvent.getResultCode() + "type : " + curatorEvent.getType());
                latch.countDown();
            }
        },service).forPath("/asyncTest","ThreadPool_data".getBytes());
        System.out.println("await before");
        latch.await();
        System.out.println("await over");
        service.shutdown();

        //事务操作（curator独有）
        Collection<CuratorTransactionResult> transactionResults = curatorFramework.inTransaction().create().forPath("/test",
                "value".getBytes()).and().setData().forPath("/test", "num".getBytes()).and().commit();
        transactionResults.forEach(ts -> {
            System.out.println("operation path is " + ts.getForPath() + ", operation type is " + ts.getType());
        });

        curatorFramework.close();
    }

}
