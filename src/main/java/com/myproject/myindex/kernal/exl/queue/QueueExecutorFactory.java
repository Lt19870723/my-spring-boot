package com.myproject.myindex.kernal.exl.queue;

import com.myproject.myindex.domain.Queue;
/**
 * 工厂实现类
 */
public interface QueueExecutorFactory {
    QueueExecutor getExecutor(Queue queue);
}
