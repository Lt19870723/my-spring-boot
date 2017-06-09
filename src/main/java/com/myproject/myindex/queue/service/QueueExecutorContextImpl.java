package com.myproject.myindex.queue.service;

import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.myindex.domain.Queue;
import com.myproject.myindex.kernal.exl.queue.QueueExecutorContext;
import com.myproject.myindex.kernal.exl.queue.QueueExecutorFactory;
import com.myproject.myindex.kernal.exl.queue.QueueService;
import com.myproject.myindex.queue.config.QueueConfig;

@Service
public class QueueExecutorContextImpl implements QueueExecutorContext {

    private static final Logger                logger         = LoggerFactory.getLogger(QueueExecutorContextImpl.class);

    @Autowired
    private QueueExecutorFactory               queueExecutorFactory;

    @Autowired
    private QueueService                       queueService;

    final String                               isRun          = ResourceBundle.getBundle("queue").getString("com.shenma.pay.schedule.status");

    final String[]                             channels       = ResourceBundle.getBundle("queue").getString("com.shenma.pay.queue.channel").split(",");

    boolean                                    isInited       = false;

    private ConcurrentMap<String, QueueConfig> queueConfigMap = new ConcurrentHashMap<String, QueueConfig>();                                          //这里不用担心多线程问题

    private int                                defaultSize    = 1;

    @PostConstruct
    public void init() {
        if (!"true".equals(isRun)) {
            return;
        }
        if (isInited) {//初始化过，就不能再初始化了
            return;
        }
        for (String c : channels) {
            int size = Integer.valueOf(ResourceBundle.getBundle("queue").getString("com.shenma.pay.queue.channel." + c + ".size"));

            if ("DEFAULT".equals(c)) {
                defaultSize = size;
            } else {
                ThreadGroup group = new ThreadGroup(c);
                BlockingQueue<Queue> queueList = new LinkedBlockingQueue<Queue>();
                QueueConfig config = new QueueConfig(size, group, queueList);
                for (int i = 0; i < size; i++) {
                    addThread(config);
                }
                logger.info("队列【{}】启动了{}个线程", c, size);
                queueConfigMap.put(c, config);
            }
        }
        isInited = true;
    }

    /**
     * 推动到队列
     */
    public void submit(String channel, Queue queue) throws Exception {
        if (!queueConfigMap.containsKey(channel)) {
            ThreadGroup group = new ThreadGroup(channel);
            BlockingQueue<Queue> queueList = new LinkedBlockingQueue<Queue>();
            QueueConfig config = new QueueConfig(defaultSize, group, queueList);
            for (int i = 0; i < defaultSize; i++) {
                addThread(config);
            }
            logger.info("队列【{}】启动了{}个线程", channel, defaultSize);
            queueConfigMap.put(channel, config);
        }
        QueueConfig config = queueConfigMap.get(channel);
        config.getQueueList().put(queue);
    }

    /**
     * 减少一个处理器
     */
    public void minuThread(String channel) {
        if (!this.queueConfigMap.containsKey(channel)) {
            return;
        }
        QueueConfig config = this.queueConfigMap.get(channel);
        Thread[] ts = new Thread[config.getThreadGroup().activeCount()];
        config.getThreadGroup().enumerate(ts);
        if (ts.length > 0) {
            ts[0].interrupt();
        }
    }

    /**
     * 增加一个处理器
     */
    public void addThread(QueueConfig config) {
        QueueRunnable run = new QueueRunnable(config, queueExecutorFactory, queueService);
        Thread t = new Thread(config.getThreadGroup(), run);
        t.start();
    }

}
