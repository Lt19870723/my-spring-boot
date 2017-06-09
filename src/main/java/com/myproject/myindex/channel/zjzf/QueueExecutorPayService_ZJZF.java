package com.myproject.myindex.channel.zjzf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.myproject.myindex.domain.Queue;
import com.myproject.myindex.kernal.exl.queue.QueueExecutor;

/***
 * 中金支付支付队列执行器
 */
@Service
public class QueueExecutorPayService_ZJZF implements QueueExecutor {
    private final Logger        logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute(Queue queue) throws Exception {
		//根据queue.getDataId()查询数据进行后续业务处理
		logger.info("从队列中取出数据ID：{}",queue.getDataId());
	}
}
