package com.myproject.myindex.queue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.myindex.Enum.PayChannelEnum;
import com.myproject.myindex.Enum.QueueDataTypeEnum;
import com.myproject.myindex.channel.zjzf.QueueExecutorPayService_ZJZF;
import com.myproject.myindex.domain.Queue;
import com.myproject.myindex.kernal.exl.queue.QueueExecutor;
import com.myproject.myindex.kernal.exl.queue.QueueExecutorFactory;

@Service
public class QueueExecutorFactoryImpl implements QueueExecutorFactory {
	 @Autowired
	 QueueExecutorPayService_ZJZF        queueExecutorPayService_ZJZF;
    @Override
    public QueueExecutor getExecutor(Queue queue) {
    	if (PayChannelEnum.ZJ.getValue().equals(queue.getQueueType())) {
            if (QueueDataTypeEnum.PAY.getValue().equals(queue.getDataType())) {
                return queueExecutorPayService_ZJZF;
            } 
        }
        return null;
   }
}
