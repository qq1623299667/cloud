package com.demo.student;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
*  模块拆分说明:
 *      首先按照业务模块进行拆分，比如student,teacher等等
 *      其次按照entity，spi，service，reference，controller，runtime进行拆分，好处是：解耦，多模块复用，无maven循环依赖
 *          entity：实体类，由于用dubbo需要多服务进行调用，故需要加入序列化
 *          spi：定义接口，通过接口即可知道输入，输出，方法名
 *          service：spi的具体实现，提供spi的具体实现
 *          reference：提供spi的dubbo具体应用对象，是其他模块复用的关键
 *          controller：具体业务调用
 *          runtime：运行独立项目，完全解耦
 *      解耦
 *          各个拆分模块之间彼此独立，方便快速替换
 *      多模块复用
 *          在runtime里面需要加入controller和service的依赖。controller里面加入reference的依赖。reference加入spi的依赖。
 *          spi引入entity依赖。
 *          当runtime运行的时候，会初始化一次service的实现类，将实现加入到spring和dubbo。此时，该spi对应的实现就可以给本项目
 *          和其他项目提供复用了！具体复用的方式是在其他项目中加入reference项目，reference项目除了引入spi之外，
 *          还有对应的dubbo：reference，这就是为什么可以复用的原因。
 *          由于runtime的存在，整个过程中，service的实现只初始化了一次，其他都是应用
 *          解决了dubbo消费者不能复用成提供者的问题。多模块项目中，比如A服务和B服务，B服务有可能需要调用A服务，同时也需要对外
 *          提供服务，所以消费者需要复用成提供者，实现RPC，远程方法本地调用，而不是通过http去调用
 *      无maven循环依赖
 *          A，B两个服务都对外提供服务，A可能本地调用B里面的服务，B也可能本地调用A里面的服务，但是不会引起循环依赖
 *      实例说明
 *          本实例引入student和teacher。student既是提供者又是消费者。teacher同student，teacher由于加入了student的reference，
 *          故可以调用student的方法。student里面也是可以调用teacher的方法的，加入teacher的reference即可。不形成maven的循环依赖
 * 配置文件说明
 *      dubbo.xml               runtime模块下，通用信息配置
 *      dubbo-customer.xml      reference模块下，spi的引用
 *      dubbo-provider.xml      service模块下，spi的实现注入及服务提供
 * 启动
 *      启动时启动runtime即可
* @author 石佳
* @since 2020/5/9
*/
@EnableDubboConfiguration
@ImportResource(locations = {"classpath*:dubbo-customer.xml","classpath*:dubbo-provider.xml","classpath*:dubbo.xml"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
