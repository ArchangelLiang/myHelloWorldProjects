package com.unknown.jedis.test;

import redis.clients.jedis.Jedis;

import java.util.ResourceBundle;

public class JedisTest {

    public static void main(String[] args) {

        //连接redis
        Jedis jedis = new Jedis("192.168.239.130", 6379,10000);

        //ping pong测试是否连接成功
        String answer = jedis.ping();
        System.out.println(answer);

        jedis.close();
    }

}
