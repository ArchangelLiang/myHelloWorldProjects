package com.unknown.zk.protogenezis_client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestZkClient {

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        /*
         * 参数一：设置zookeeper服务器所在ip与端口号，可设置多个（如果一个连接失败，会转而连接另外一个），中间用逗号隔开
         * 参数二:用来设置连接超时时间
         * 参数三：设置事件监听逻辑
         */
        ZooKeeper zk = new ZooKeeper("192.168.43.145:2181", 30000, new Watcher() {
            //此处为事件回调方法
            public void process(WatchedEvent watchedEvent) {
                System.out.println("getState = " + watchedEvent.getState());
                System.out.println("getType = " + watchedEvent.getType());
                switch (watchedEvent.getState()){
                    case SyncConnected:
                        switch (watchedEvent.getType()){
                            case None:
                                System.out.println("连接成功。。。");
                                latch.countDown();
                                break;
                            case NodeCreated:
                                System.out.println("有子节点创建了");
                                break;
                            case NodeDataChanged:
                                System.out.println("有节点数据改变了");
                                break;
                            default:
                                System.out.println("其它连接事件");
                        }
                        break;
                    case Closed:
                        System.out.println("接收到watcher事件 -> " + watchedEvent);
                        System.out.println("事件状态 = " + watchedEvent.getState());
                        System.out.println("事件类型 = " + watchedEvent.getType());
                        System.out.println("事件path = " + watchedEvent.getPath());
                        break;
                }

            }
        });

        System.out.println("阻塞。。。。");
        latch.await();
        System.out.println("阻塞结束.....");

        zk.create("/java","testApi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        Stat stat = new Stat();
        byte[] data = zk.getData("/java", true,stat);
        System.out.println("获取到数据：" + new String(data));
        System.out.println("dataVersion = " + stat.getVersion());

        List<String> children = zk.getChildren("/", true);
        System.out.println(children);

        zk.setData("/java","archangel".getBytes(),1);

        zk.close();
    }

}
