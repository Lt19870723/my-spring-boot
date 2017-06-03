package com.myproject;

import org.junit.Test;


public class javaBaseTest {
    @Test
    public void TestString() {
    	//equals 比较的是对象值，不比较内存地址，返回TURE时不一定是同一对象
    	//==比较的是对象值，还比较内存地址，返回TURE时一定是同一对象
    	String str1="123";//实例化并放入常量池
    	String str2="123";//实例化时先从常量池找，发现找到了，直接返回，所以str1 str2是同一对象
		String str3=new String("123");
		String str4=new String("123");
		System.out.println(str1==str2);//true  
		System.out.println(str1.equals(str2));//true 只比较值
		
		System.out.println(str3==str4);//false
		System.out.println(str3.equals(str4));//true  只比较值
		
		System.out.println(str1==str3);//false
		System.out.println(str1.equals(str3));//true 只比较值
		
		//intern方法优先从常量池查找返回，同String str1="123" 实例化
		System.out.println(str3.intern()==str4.intern());//true  
		System.out.println(str1.intern()==str3.intern());//true
		
		System.out.println(str3.intern().equals(str4.intern()));//true  只比较值
		System.out.println(str1.intern().equals(str3.intern()));//true 只比较值

	}
    
    @Test
    public void TestInteger() {
    	//equals 比较的是对象值，不比较内存地址，返回TURE时不一定是同一对象
    	//==比较的是对象值，还比较内存地址，返回TURE时一定是同一对象
    	int int1=123;
    	int int2=123;
    	Integer int3=223;
    	Integer int4=223;
    	Integer int5=new Integer(223);
    	Integer int6=new Integer(223);

		System.out.println(int1==int2);//true  基础数据类型比较，只要值相等返回true
		
		System.out.println(int3==int4);//false 自动装载好的Integer对象，实例化时128以内 优先从常量池查找返回，返回true,128以外实例化时总是NEW，返回false
		System.out.println(int3.equals(int4));//true  只比较值
		
		System.out.println(int5==int6);//false  不论是否大于128，由于外面new重新实例化了，都是不同的对象，总返回false
		System.out.println(int5.equals(int6));//true  只比较值
	}
}
