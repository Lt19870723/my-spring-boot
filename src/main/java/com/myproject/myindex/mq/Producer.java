package com.myproject.myindex.mq;

import javax.jms.Queue;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myproject.myindex.domain.User;

/**
 * 消息生产者.
 */
@Component
@EnableScheduling
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue                queueone;
    @Autowired
    private Queue                queuetwo;
    @Scheduled(fixedDelay = 3000)
    //每3s执行1次
    public void send() {
    	User use=new User();
    	use.setUsername("liutao");
    	use.setPassword("liutao");
    	use.setSex("男");
        this.jmsMessagingTemplate.convertAndSend(this.queueone, JSONObject.fromObject(use).toString());
        User use1=new User();
        use1.setUsername("lihua");
        use1.setPassword("lihua");
        use1.setSex("女");
        this.jmsMessagingTemplate.convertAndSend(this.queuetwo, JSONObject.fromObject(use1).toString());

    }

}