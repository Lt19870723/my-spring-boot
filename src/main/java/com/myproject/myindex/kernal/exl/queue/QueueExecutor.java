package com.myproject.myindex.kernal.exl.queue;

import com.myproject.myindex.domain.Queue;

public interface QueueExecutor {

    public void execute(Queue queue) throws Exception;

}
