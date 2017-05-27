package com.myproject.myindex.mq;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueConfig {
    @Bean
    public Queue queueone() {
        return new ActiveMQQueue("sample.queueone");
    }
    
    @Bean
    public Queue queuetwo() {
        return new ActiveMQQueue("sample.queuetwo");
    }
}
