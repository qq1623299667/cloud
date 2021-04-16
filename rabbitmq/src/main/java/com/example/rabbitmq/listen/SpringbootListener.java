package com.example.rabbitmq.listen;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * springboot的特定写法,空降一个方法去接受消息.
 * 在container里面配置listenerAdapter,在listenerAdapter配置
 * 这种设定很容易让代码看不懂
 */
@Component
public class SpringbootListener {
    static  final String topicExchangeName = "spring-boot-topic-exchange";
    static final String queueName = "spring-boot-queue";

    /**
     * 申明队列
     */
    @Bean
    Queue queue(){
        return new Queue(queueName,false);
    }

    /**
     * 申明交换机
     */
    @Bean
    TopicExchange exchange(){
        return new TopicExchange(topicExchangeName);
    }

    /**
     * 将交换机与队列绑定
     */
    @Bean
    Binding binding(Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    /**
     * 申明处理消息的适配器,指明用哪个方法处理接收消息,
     * 这里指明了用Receiver的receiveMessage()方法接收消息
     * @param receiver 这是自己注入的对象,里面有一个receiveMessage方法
     */
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
        return  new MessageListenerAdapter(receiver,"receiveMessage");
    }

    /**
     * 申明一个Listener容器
     * 需要设置连接消息,指明监听哪个queue,受到消息的处理方法
     */
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }
}
