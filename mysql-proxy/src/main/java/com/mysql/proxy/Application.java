package com.mysql.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

/**
 * 利用spring提供的AbstractRoutingDataSource类的determineCurrentLookupKey方法获取数据源标识，达到切换数据源的目的
 * 参考：https://mp.weixin.qq.com/s/73_8_O5qMkn-gcJH2IZbJg
 * 参考：https://www.cnblogs.com/liujiduo/p/5004691.html
 * 参考：https://cloud.tencent.com/developer/article/1497712
* @author 石佳
* @since 2020/5/12
*/
@SpringBootApplication(exclude={JpaRepositoriesAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
