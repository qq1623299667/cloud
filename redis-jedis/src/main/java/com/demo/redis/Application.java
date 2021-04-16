package com.demo.redis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
*  jedis版本的redis分布式加锁的实现
 *  参考：https://www.cnblogs.com/zhaopengcheng/p/12144022.html
* @author 石佳
* @since 2020/6/22
*/
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}