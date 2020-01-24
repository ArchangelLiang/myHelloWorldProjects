package com.unknown;

import com.unknown.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class testRedisTemplate {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testStringTypeAdd(){
        this.redisTemplate.opsForValue().set("springDataRedis","hello,World");
    }

    @Test
    public void testStringTypeGet(){
        Object value = this.redisTemplate.opsForValue().get("springDataRedis");
        System.out.println(value);
    }

    @Test
    public void testStringTypeAddObject(){
        User zio = new User(1,"zio","china");
        //添加对象需要更换value的序列化器，注意：此处使用的序列化器相对比较浪费存储空间，推荐使用json
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        this.redisTemplate.opsForValue().set("user", zio);
    }

    @Test
    public void testStringTypeGetObject(){
        //取出对象需要和添加时使用相同的序列化器
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        Object user = this.redisTemplate.opsForValue().get("user");
        if (user instanceof User){
            System.out.println(user);
        }
    }

    @Test
    public void testStringTypeAddObjectUseJsonSerializer(){
        User zio = new User(2,"decade","china");
        //添加对象需要更换value的序列化器为json
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
        this.redisTemplate.opsForValue().set("user_json", zio);
    }

    @Test
    public void testStringTypeGetObjectUseJsonSerializer(){
        User zio = new User(2,"decade","china");
        //取出对象需要和添加时使用相同的序列化器
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
        Object user_json = this.redisTemplate.opsForValue().get("user_json");
        if (user_json instanceof User){
            System.out.println(user_json);
        }
    }

}
