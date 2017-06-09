package com.myproject.myindex.kernal.exl.queue;

import java.util.Date;
import java.util.List;

import com.myproject.myindex.Enum.QueueStatusType;
import com.myproject.myindex.domain.Queue;

public interface QueueService {

    /**
     * 修改
     * @param queue
     * @return
     */
    public Queue save(Queue queue);

    /**
     * 修改
     * @param queue
     * @return
     */
    public void delete(String queId);

    public List<Queue> selectQueue(String queueType, String dataType, List<String> statusList, Date expectTime);

    public List<Queue> selectQueue(List<QueueStatusType> statusList, Date expectTime);
}
