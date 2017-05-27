package com.myproject.myindex.mq;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueConfig {
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("sample.queue");
    }
}
