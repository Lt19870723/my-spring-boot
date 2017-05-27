package com.myproject.myindex.mq;

import net.sf.json.JSONObject;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.myproject.myindex.domain.User;

/**
 * 消息消费者.
 */
@Component
public class Consumer {
    @JmsListener(destination = "sample.queueone")
    public void receiveQueueOne(String text) {
    	User user=(User) JSONObject.toBean(JSONObject.fromObject(text), User.class);
        System.out.println(user.getUsername());
    }
    @JmsListener(destination = "sample.queuetwo")
    public void receiveQueueTwo(String text) {
    	User user=(User) JSONObject.toBean(JSONObject.fromObject(text), User.class);
        System.out.println(user.getUsername());    }
}