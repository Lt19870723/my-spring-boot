package com.myproject.myindex.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myproject.myindex.Enum.PayChannelEnum;
import com.myproject.myindex.Enum.QueueDataTypeEnum;
import com.myproject.myindex.Enum.QueueStatusType;
import com.myproject.myindex.domain.Queue;
import com.myproject.myindex.kernal.exl.queue.QueueService;

/**
 * 中金支付定时任务
 */
@Component
@Configurable
@EnableScheduling
public class ZjpayTask {
    private final Logger        logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	QueueService                queueService;
	
	
    @Scheduled(cron = "0/3 * * * * ?")
    public void batchQueryForPay() {
    	for(int i=0;i<30;i++){
    		//推送队列
            Queue queue = new Queue();
            queue.setDataId(getTxNo27());
            queue.setDataType(QueueDataTypeEnum.PAY.getValue());
            Calendar c = Calendar.getInstance();
            c.add(Calendar.SECOND, 10);
            queue.setExpectTime(c.getTime());
            queue.setStatus(QueueStatusType.INIT);
            queue.setQueueType(PayChannelEnum.ZJ.getValue());
            queueService.save(queue);
            logger.info("插入一条中金代付队列数据，queueId：{}，dataId：{}", queue.getQueueId(), queue.getDataId());
    	}
      
}
    /**
     * 生成27位序列号
     *  <pre>17位时间 + 10 位随机数</pre>
     * @return
     */
    public static String getTxNo27(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateStr = sdf.format(Calendar.getInstance().getTime());
        String randomStr;
        for(randomStr = String.valueOf(Math.abs( new Random().nextLong()));
            randomStr.length() < 10;
            randomStr = randomStr + String.valueOf(Math.abs( new Random().nextLong()))) {}
        return dateStr + randomStr.substring(0, 10);

    }
}
