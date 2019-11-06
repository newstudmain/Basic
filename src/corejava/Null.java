package corejava;

import java.util.List;

public class Null {
	
	public static void nullTest() {
		System.out.println("null...");
	}
	
	public static void print(Object o) {
		System.out.println(o);
		System.out.println((String)o);//null
		//System.out.println((int)o);//java.lang.NullPointerException
		System.out.println((List)o);//null
	}
	
	public static String str;
	
	public static void main(String[] args) {
		
		String str = null;
		String str2 = "null";
		//int i = null;
		
		System.out.println(str);//null
		//System.out.println(str.toString());//java.lang.NullPointerException
		
		Null n = new Null();
		n = null;
		nullTest();//null...
		
		((Null)null).nullTest();//null...
		
		if(str==null) {}
		//if(str=null) {}
		
		//Integer i = null;
		//int j = (int)i;	//java.lang.NullPointerException	 任何含有null值的包装类在java拆箱生成基本数据类型时候都会抛出一个空指针异常。
		//System.out.println(j);
		
		System.out.println((str instanceof String));//false
		
		System.out.println((null == null));//true
		
		print(null);//null
	}
		
		/*
		 * 在内存中null具体是什么?在Java中 null 值是什么，在内存中null是什么?
				首先需要明确,null不是一个合法的object实例,所以并没有为其分配内存. 
				null 仅仅用于表明该引用目前没有指向任何对象。
					JVM规范 的描述:
						Java虚拟机规范并不强制要求使用一个具体的值编码null。
		 *
		 * 我认为和其它类C语言一样,null是对引用变量的值全部置0。
				Java对象的属性域默认初始化其实很简单，把分配的内存所有位全部置0,
				所以 数字是0, boolean是false, 浮点是 0.0f, 引用是 null, 
				因为引用是内存地址,所以可以推测出 null 实际上就是一堆0. 地址 0 肯定是不可以存放任何对象的。
		 *
		 * - null 可以强转为任何类类型，但是null强转以后是无效对象，其返回值为null
		 * - 一个null类型可以赋值给任意类类型，将返回一个该类型对象的空引用(其实还是null)。
		 * - java中的任何引用变量都将null作为默认值。这对所有变量都是适用的，
		 * 	   如成员变量、局部变量、实例变量、静态变量(但当你使用一个没有初始化的局部变量，编译器会警告)。
		 * - null可以赋值给引用变量，不能将null赋值给基本类型变量
		 * - 如果使用了带有null值的引用类型的变量，instanceof操作会返回false
		 * - 可以使用静态方法来使用一个值为null的引用类型变量。因为静态方法使用静态类型绑定，不会抛空指针异常
		 * - 任何含有null值的包装类在java拆箱生成基本数据类型时候都会抛出一个空指针异常。
		 * 
		 * https://blog.csdn.net/qq_25077777/article/details/80174763
		 * */

}
