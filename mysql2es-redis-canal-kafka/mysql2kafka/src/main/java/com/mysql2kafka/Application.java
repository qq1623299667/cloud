package com.mysql2kafka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* 将mysql的数据同步到es
 * 流程：canal从redis中获取需要监听的表数据，监听到表变化的时候，将库+表+id信息发送给kafka，es监听kafka的消息，最后时限数据的同步
 * 全量同步：手动触发接口，获取redis的表数据，删除es数据然后查询所有mysql数据最后写入到es
 * 增量同步：实时自动同步，canal监听到mysql表的改动的时候，自动同步，同步的时候，
 *      先删除es的数据，然后调用mysql查询对应的数据，如果没有，则是删除，无需处理。如果是新增或者修改，则可以将数据插入进来
 * 其中，canal监听表变化发送库+表+id信息到kafka可以单独拆分出来，好处是1、实现代码解耦 2、实现表数据变动的监听
* @author 石佳
* @since 2020/8/19
*/
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}