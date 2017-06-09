package com.myproject.myindex.queue.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.myindex.Enum.QueueStatusType;
import com.myproject.myindex.domain.Queue;
import com.myproject.myindex.kernal.exl.queue.QueueService;
import com.myproject.myindex.queue.repo.QueueRepo;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    QueueRepo queueRepo;

    @Override
    public Queue save(Queue queue) {
        if (StringUtils.isEmpty(queue.getQueueId())) {
            return queueRepo.saveAndFlush(queue);
        } else {
            Queue entity = queueRepo.findOne(queue.getQueueId());
            entity.setDataType(queue.getDataType());
            entity.setDataId(queue.getDataId());
            entity.setExpectTime(queue.getExpectTime());
            entity.setFaildTime(queue.getFaildTime());
            entity.setQueueType(queue.getQueueType());
            entity.setModifiedTime(new Date());
            entity.setStatus(queue.getStatus());
            queueRepo.saveAndFlush(entity);
            return queue;
        }
    }

    @Override
    public void delete(String queId) {
        queueRepo.delete(queId);
    }

    public List<Queue> selectQueue(String queueType, String dataType, List<String> statusList, Date expectTime) {
        return queueRepo.selectQueue(queueType, dataType, statusList, expectTime);
    }

    public List<Queue> selectQueue(List<QueueStatusType> statusList, Date expectTime) {
        return queueRepo.selectQueue(statusList, expectTime);
    }
}
