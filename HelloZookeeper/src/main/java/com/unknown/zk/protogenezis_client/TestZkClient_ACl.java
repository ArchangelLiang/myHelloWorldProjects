package com.unknown.zk.protogenezis_client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class TestZkClient_ACl implements Watcher {

    private static ZooKeeper zk = null;
    private static CountDownLatch latch = new CountDownLatch(1);
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception{

        zk = new ZooKeeper("192.168.43.145:2181", 30000,new TestZkClient_ACl());

        latch.await();

        ArrayList<ACL> acls = new ArrayList<>();

        zk.addAuthInfo("digest","zio:999".getBytes());

        ACL auth = new ACL(ZooDefs.Perms.ALL, new Id("auth", "zio:999"));
        ACL auth2 = new ACL(ZooDefs.Perms.CREATE, new Id("ip", "192.168.43.225"));

        acls.add(auth);
        acls.add(auth2);

        zk.create("/testAuth_java2","auth_data".getBytes(),acls, CreateMode.PERSISTENT);

        byte[] data = zk.getData("/testAuth_java2", true, stat);

        System.out.println("get data = " + new String(data));

        zk.close();
    }

    public static void println(Object value){
        System.out.println(value);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        switch(watchedEvent.getState()) {
            case SyncConnected:
                switch (watchedEvent.getType()){
                    case None:
                        println("connecting success");
                        latch.countDown();
                        break;
                    case NodeCreated:
                        println("happen node created event");
                        println("crated node path = " + watchedEvent.getPath());
                        try {
                            println("create node data value = " + new String(zk.getData(watchedEvent.getPath(), true, stat)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case NodeDataChanged:
                        println("happen node dada change event");
                        println("change path = " + watchedEvent.getPath());
                        try {
                            println("change after data value = " + new String(zk.getData(watchedEvent.getPath(), true, stat)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case NodeDeleted:
                        println("happen node delete event");
                        println("delete node is = " + watchedEvent.getPath());
                        break;
                    default:
                        println("other event type,event is + " + watchedEvent.getType());
                }
                break;
            case Closed:
                println("connecting close");
                break;
            default:
                println("other event stat,event is + " + watchedEvent.getState());
        }
    }
}
