package com.myproject;

import org.junit.Test;

public class javaThreadTest {
	@Test
	public void test(){
		testThread();
//		testThreadsync("123");
	}
	
    void testThread(){
    	for(int i=0;i<5;i++){
    		//不加锁
           Thread thread =new Thread(){
        	  public void run(){
        			System.out.println("theadId:"+Thread.currentThread().getId()+"");
        	  } 
           };
           thread.start();
    	}
   }
   void testThreadsync(final String sync){
    	for(int i=0;i<5;i++){
    		//加锁
           Thread thread =new Thread(){
        	  public void run(){
        		synchronized (sync) {
        			System.out.println("theadId:"+Thread.currentThread().getId()+"");
				}
        	  } 
           };
           thread.start();
    	}
   }
}
