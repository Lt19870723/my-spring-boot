package com.myproject.myindex.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.myproject.myindex.Enum.QueueStatusType;
import com.myproject.myindex.domain.Queue;
import com.myproject.myindex.kernal.exl.queue.QueueExecutorContext;
import com.myproject.myindex.kernal.exl.queue.QueueService;

/**
 * 定时执行放到queue里面去
 */
@Service("QueueJobService")
public class QueueJobTask {
    private final Logger logger   = LoggerFactory.getLogger(getClass());

    @Autowired
    QueueService         queueService;

    @Autowired
    QueueExecutorContext queueExecutorContext;
    final String         isRun    = ResourceBundle.getBundle("queue").getString("com.shenma.pay.schedule.status");

    boolean              isInited = false;


    @PostConstruct
    public void init() {
        if (!"true".equals(isRun)) {
            return;
        }
        if (isInited) {
            return;
        }
        List<QueueStatusType> statusList = new ArrayList<QueueStatusType>();
        statusList.add(QueueStatusType.EXCUTING);
        List<Queue> queueList = queueService.selectQueue(statusList, new Date());
        logger.info("******QueueJobService实例化中，执行init()方法，修改{}条数据为执行中*****", queueList.size());
        push(queueList);
        isInited = true;
    }

    /**
     * 查询这个不大于这个时间内所有的 初始化、失败的待办消息
     */
    /**
     * 每2秒执行
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void execute() {
        if (!"true".equals(isRun)) {
            return;
        }
        List<QueueStatusType> statusList = new ArrayList<QueueStatusType>();
        statusList.add(QueueStatusType.INIT);
        List<Queue> queueList = queueService.selectQueue(statusList, new Date());
        push(queueList);
    }

    private void push(List<Queue> queueList) {
        for (Queue queue : queueList) {
            try {
                queue.setStatus(QueueStatusType.EXCUTING);//执行中
                queueService.save(queue);
                queueExecutorContext.submit(queue.getQueueType(), queue);
            } catch (Exception e) {
                logger.error(
                        "队列类型：" + queue.getQueueType() + "，数据：[" + queue.getDataType() + "@Id=" + queue.getDataId()
                                + "]放入队列失败", e);
            }
        }
    }

}
