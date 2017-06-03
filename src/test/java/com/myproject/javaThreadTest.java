package com.myproject;

import org.junit.Test;

public class javaThreadTest  {
	//线程状态 创建（直接NEW Thread或者继承Thread或者implements runnable）  就绪 start()  运行 run()   阻塞sleep()或wait() 唤醒notify()或者notifyAll() 死亡   stop()或者run执行结束
	//线程生命周期 先创建线程，然后调用start(),相当于交接给虚拟机的线程调度，这时当前线程为线程体，线程调度会分配时间片段给该线程，该线程就会调用run(),进入运行状态，
	//运行过程调用sleep()或wait()就会进入休眠状态，如果需要可以调用notify()唤醒当前线程或者notifyAll()唤醒所有休眠线程
	//运行完毕后当前线程会自动死亡，或者调用stop()结束当前线程
	
	//单线程时才能跨国start(),直接调用run();多线程时必须通过调用start()启动线程，因为直接调用run()相当于不经过线程调度，线程调度只能默认调度当前线程
	//无法调度其他线程，达不到多线程效果
	
	@Test
	public void test(){
		testThread();
	}
	
    void testThread(){
    	for(int i=0;i<5;i++){
           Thread thread =new Thread(){
        	  public void run(){
        			System.out.println("theadId:"+Thread.currentThread().getId()+"");
        	  } 
           };
           thread.start();
    	}
   }
}
