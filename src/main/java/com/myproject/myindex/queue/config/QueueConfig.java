package com.myproject.myindex.queue.config;

import java.util.concurrent.BlockingQueue;

import com.myproject.myindex.domain.Queue;

public class QueueConfig {
    private int                  maxSize;
    private ThreadGroup          threadGroup;
    private BlockingQueue<Queue> queueList;

    public QueueConfig(int maxSize, ThreadGroup threadGroup, BlockingQueue<Queue> queueList) {
        super();
        this.maxSize = maxSize;
        this.threadGroup = threadGroup;
        this.queueList = queueList;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public ThreadGroup getThreadGroup() {
        return threadGroup;
    }

    public void setThreadGroup(ThreadGroup threadGroup) {
        this.threadGroup = threadGroup;
    }

    public BlockingQueue<Queue> getQueueList() {
        return queueList;
    }

    public void setQueueList(BlockingQueue<Queue> queueList) {
        this.queueList = queueList;
    }

}
