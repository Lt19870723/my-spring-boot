package com.myproject.myindex.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者.
 */
@Component
public class Consumer {
    @JmsListener(destination = "sample.queueone")
    public void receiveQueueOne(String text) {
        System.out.println(text);
    }
    @JmsListener(destination = "sample.queuetwo")
    public void receiveQueueTwo(String text) {
        System.out.println(text);
    }
}