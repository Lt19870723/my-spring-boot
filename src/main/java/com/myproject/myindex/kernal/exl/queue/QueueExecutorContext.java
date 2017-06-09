package com.myproject.myindex.kernal.exl.queue;

import com.myproject.myindex.domain.Queue;

public interface QueueExecutorContext {

    public void submit(String channel, Queue queue) throws Exception;

}
