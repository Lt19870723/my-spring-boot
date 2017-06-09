package com.myproject.myindex.queue.service;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myproject.myindex.Enum.QueueStatusType;
import com.myproject.myindex.domain.Queue;
import com.myproject.myindex.kernal.exl.queue.QueueExecutor;
import com.myproject.myindex.kernal.exl.queue.QueueExecutorFactory;
import com.myproject.myindex.kernal.exl.queue.QueueService;
import com.myproject.myindex.queue.config.QueueConfig;

/**
 * 分配消费对象
 * 
 */
public class QueueRunnable implements Runnable {

    private final Logger         logger         = LoggerFactory.getLogger(getClass());

    //失败最大次数
    final int                    maxfialdtime   = Integer.valueOf(ResourceBundle.getBundle("queue").getString("com.shenma.pay.schedule.queue.fail.release.times"));
    //失败以后多长时间继续执行
    final int                    fialdnexedtime = Integer.valueOf(ResourceBundle.getBundle("queue").getString("com.shenma.pay.schedule.queue.fail.next.minits"));

    private QueueConfig          config;
    private QueueExecutorFactory factory;
    private QueueService         queueService;

    public QueueRunnable(QueueConfig config, QueueExecutorFactory factory, QueueService queueService) {
        this.config = config;
        this.factory = factory;
        this.queueService = queueService;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Queue queue = config.getQueueList().take();
                QueueExecutor queueExecutor = factory.getExecutor(queue);
                if (queueExecutor == null) {//没有找到对应的执行器,标记取消
                    logger.error("队列元素【队列类型:{}, 数据类型:{}, 数据ID:{}】找不到执行器", queue.getQueueType(), queue.getDataType(), queue.getDataId());
                    queue.setStatus(QueueStatusType.FAILD);
                    queueService.save(queue);
                    continue;
                }
                if (QueueStatusType.CANCEL.getValue().equals(queue.getStatus())) {
                    logger.info("队列元素【队列类型:{}, 数据类型:{}, 数据ID:{}】被取消执行", queue.getQueueType(), queue.getDataType(), queue.getDataId());
                    continue;
                }
                try {
                    queueExecutor.execute(queue);
                    queue.setStatus(QueueStatusType.END);
                    queueService.save(queue);
                } catch (Exception e) {
                    logger.error("队列元素【队列类型:{}, 数据类型:{}, 数据ID:{}】执行异常", queue.getQueueType(), queue.getDataType(), queue.getDataId(), e);
                    queue.setStatus(QueueStatusType.FAILD);
                    queueService.save(queue);
                }
            }
        } catch (InterruptedException e) {
            logger.warn("队列执行过程中出现异常", e);
        }

    }

}
