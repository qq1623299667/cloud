package com.demo.canal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  监听表的动态变化
 *  下载canal：canal.deployer-1.1.5-SNAPSHOT.tar.gz
 *  解压，修改 D:\program\canal\conf\example\instance.properties
 *  主要修改3个属性：数据库地址，用户名，密码
 *      canal.instance.master.address = 127.0.0.1:3306
 *      canal.instance.dbUsername = canal
 *      canal.instance.dbPassword = canal
 *  启动bin/startup.bat，启动canal，光启动canal还无法按照需求进行表监听
 *  然后编写java客户端代码，也就是本demo，启动后即可实时监听表改动
 * @author 石佳
 * @since 2020/6/10
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}