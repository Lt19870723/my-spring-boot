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
		System.out.println(str1.equals(str2));//true
		
		System.out.println(str3==str4);//false
		System.out.println(str3.equals(str4));//true
		
		System.out.println(str1==str3);//false
		System.out.println(str1.equals(str3));//true
		
		//intern方法优先从常量池查找返回，同String str1="123" 实例化
		System.out.println(str3.intern()==str4.intern());//true  
		System.out.println(str1.intern()==str3.intern());//true
		
		System.out.println(str3.intern().equals(str4.intern()));//true  
		System.out.println(str1.intern().equals(str3.intern()));//true

	}
}
