package corejava;

import java.util.Random;

import org.junit.Test;

/*
 * https://www.hollischuang.com/archives/2517
 * https://tech.meituan.com/2014/03/06/in-depth-understanding-string-intern.html
 * 
 * 类加载过程
 * 		加载 ---> 验证 ---> 准备 ---> 解析 ---> 初始化 ---> 使用 ---> 卸载
 * 
 * 1. class常量池(constant pool table)	[静态常量池]
 * 
 * 	Class常量池是Class文件中的资源仓库，其中保存了各种常量。而这些常量都是开发者定义出来，需要在程序的运行期使用的。
 * 	Class是用来保存常量的一个媒介场所，并且是一个中间场所。
 * 	在JVM真的运行时，需要把常量池中的常量加载到内存中，经过运行期转换得到真正的内存入口地址。
 * 
 * 		魔数(cafe babe)
 * 		版本号..次版本号..主板本号(vision:52 JDK_1.8)
 * 		常量池计数器(常量池计数器是从0开始)
 * 		常量池数据区*：
 * 			[ 访问标志  类索引  父类索引  接口计数器  接口信息数据区 ]
 * 
 * 			字面量（literal）
 * 				表达源代码中一个固定值的表示法,对基本值的字面量表示，
 * 					诸如：整数、浮点数以及字符串；final 值
 * 					而有很多也对布尔类型和字符类型的值也支持字面量表示；
 * 					还有一些甚至对枚举类型的元素以及像数组、记录和对象等复合类型的值也支持字面量表示法。
 * 				字面量只可以右值出现，所谓右值是指等号右边的值，如：int a=123这里的a为左值，123为右值。在这个例子中123就是字面量。
 * 
 * 			符号引用（symbolic references）
 * 				限定名
 * 					当前类与直接父类的全限定名
 * 					当前类型接口，超接口的全限定名
 * 				描述符
 * 					字段的名称和描述符
 * 					方法的名称和描述符
 * 			
 * 			符号引用与直接引用
 * 					符号引用 ：(加载时期)
 * 							符号引用以一组符号来描述所引用的目标。符号引用可以是任何形式的字面量，
 * 							只要使用时能无歧义地定位到目标即可，符号引用和虚拟机的布局无关。
 * 							在编译的时候一个每个java类都会被编译成一个class文件，
 * 							但在编译的时候虚拟机并不知道所引用类的地址，多以就用符号引用来代替，
 * 							而在这个解析阶段就是为了把这个符号引用转化成为真正的地址的阶段。
 * 					直接引用：(解析时期)
 * 							直接引用和虚拟机的布局是相关的，不同的虚拟机对于相同的符号引用所翻译出来的直接引用一般是不同的。
 * 							如果有了直接引用，那么直接引用的目标一定被加载到了内存中。经过运行期转换得到真正的内存入口地址
 * 							直接引用可以是： 
 *								1：直接指向目标的指针。（指向对象，类变量和类方法的指针，直接操作内存）
 *								2：相对偏移量。      （指向实例的变量，方法的指针）
 *								3：一个间接定位到对象的句柄。
 * 
 * 2. 运行时常量池
 * 		jvm在执行某个类的时候，必须经过加载、连接、初始化，而连接又包括验证、准备、解析三个阶段。
 * 		而当类加载到内存中后，jvm就会将class常量池中的内容存放到运行时常量池中，由此可知，运行时常量池也是每个类都有一个
 * 		相较于Class文件常量池，运行时常量池更具动态性，在运行期间也可以将新的变量放入常量池中，而不是一定要在编译时确定的常量才能放入。
 * 		最主要的运用便是String类的intern()方法
 *
 *			jdk1.6	及以下版本:它位于永久代-方法区中
 *			
 *			jdk1.7	逐步开始抛弃方法区,将字符串常量池移至堆区.
 *					这里jdk文档并没有说运行时常量池是否也跟着移到堆区,
 *					也就是说运行时常量依然在方法区,永久代仍存在于JDK1.7中.
 *					
 *			jdk1.8	JVM移除了永久区,取而代之的是元空间(Metaspace) ,
 *					也就是将本地内存用来存储.容量取决于是32位或是64位操作系统的可用虚拟内存大小）.
 *					这时候字符串常量池还在堆, 运行时常量池还在方法区, 只不过方法区的实现从永久代变成了元空间.
 * 
 * 3. 全局字符串常量池
 		为了避免多次创建字符串对象,而将字符串在jvm中开辟一块空间,储存不重复的字符串.
		在直接使用双引号""声明字符串的时候, java都会去常量池找有没有这个相同的字符串,
			如果有,则将常量池的引用返回给变量. 
			如果没有,会在字符串常量池中创建一个对象,调用intern()方法后 ，将字符串加入常量池。然后返回这个对象的引用
				intern()有两个作用，
					第一个是将字符串字面量放入常量池（如果池没有的话），
					第二个是返回这个常量的引用。
		
		使用new关键字创建,比如String a = new String("hello");
			- 在类加载阶段,若常量池中已经存在"hello"，则直接引用，也就是此时只会创建一个对象 .说的就是这个字符串字面量在字符串池中被创建的过程。
			- 	在运行期，new String("hello");执行到的时候，是要在Java堆中创建一个字符串对象的，
				而这个对象所对应的字符串字面量是保存在字符串常量池中的。
				但是，String a = new String("hello");
				对象的符号引用 a 是保存在Java虚拟机栈上的，他保存的是堆中刚刚创建出来的的字符串对象的引用。
			
			常量池中的“对象”是在编译期就确定好了的，在类被加载的时候创建的，
			如果类加载时，该字符串常量在常量池中已经有了，那这一步就省略了。
			堆中的对象是在运行期才确定的，在代码执行到new的时候创建的。
			
			//—*—
			将String常量池 从 Perm 区移动到了 Java Heap区
			String#intern 方法时，如果存在堆中的对象，会直接保存对象的引用，而不会重新创建对象。
				这个方法是一个 native 的方法，但注释写的非常明了。
				如果常量池中存在当前字符串, 就会直接返回当前字符串. 
				如果常量池中没有此字符串, 会将此字符串放入常量池中后, 再返回引用。
		
 * 
 * 	jdk1.6及之前,字符串常量池是属于运行时常量池中的
   	jdk1.7字符串常量池从方法区中被单独拿到堆中了
 * 
 * 
 * */
public class String_Pool {

	public static void main(String[] args) {
		String t1 ="abc"; //[string pool] -> t1--abc
		String t2 ="abc"; //[string pool] -> (t1--abc)-<t2
		String t3 ="ab"+"c"; //[string pool] -> (t1--abc)-<t3
		String t4 =new String("abc");//.intern(); //[heap] t4--new String() , [string pool] -> t1--abc
		System.out.println("t1 == t2 : "+ (t1 == t2));//true
		System.out.println("t1 == t3 : "+ (t1 == t3));//true
		System.out.println("t1 == t4 : "+ (t1 == t4));//false
		String t5 =new String("abc").intern();
		System.out.println("t1 == t5 : "+ (t1 == t5));//true
		
		System.out.println("-----");
		
	    String s = new String("1");//.intern();(true)	//常量池中的“1” 和 JAVA Heap 中的字符串对象。
	    String si = s.intern();//s 对象去常量池中寻找后发现 “1” 已经在常量池里了
	    String s2 = "1";//生成一个 s2 的引用指向常量池中的“1”对象
	    System.out.println("s == s2: "+(s == s2));//false
	    System.out.println("si == s2: "+(si == s2));//true
	    System.out.println("s == si: "+(s == si));//false
	    
	    String st = new String("2").intern();//.intern();(true)	//常量池中的“1” 和 JAVA Heap 中的字符串对象。
	    String sti = st.intern();//st 对象去常量池中寻找后发现 “1” 已经在常量池里了
	    String st2 = "2";//生成一个 st2 的引用指向常量池中的“1”对象
	    System.out.println("st == st2: "+(st == st2));//true
	    System.out.println("sti == st2: "+(sti == st2));//true
	    System.out.println("st == sti: "+(st == sti));//true
	    
	    /*
	     *  1. 将String常量池 从 Perm 区移动到了 Java Heap区
	     *  2. String#intern 方法时，如果存在堆中的对象，会直接保存对象的引用，而不会重新创建对象。
	     *  
		     * 	String s = new String("1");
		     *  String si = s.intern();
		     *  s == si ; //false
		     *  
		     *  String st = new String("2").intern();
		     *  String sti = st.intern();
		     *  st == sti; //true
		     *  
		     *  String s3 = new String("1") + new String("1"); 
		     *  String intern_s3 = s3.intern();
		     *  s3 == intern_s3; //true
	     *  
	     *  
	     *  
	     * */
	    
	    System.out.println("-----");
	    
	    String s3 = new String("1") + new String("1"); //字符串常量池中的 “1” 和 JAVA Heap 中的 s3引用指向的对象。中间还有2个匿名的new String("1")，此时s3引用对象内容是”11”，但此时常量池中是没有 “11”对象的。
	    String intern_s3 = s3.intern();//将 s3中的“11”字符串放入 String 常量池中，因为此时常量池中不存在“11”字符串，
//				 	   					 因此常规做法是跟 jdk6 图中表示的那样，在常量池中生成一个 “11” 的对象，
//	          							 关键点是 jdk7 中常量池不在 Perm 区域了，这块做了调整。
//	          							 常量池中不需要再存储一份对象了，可以直接存储堆中的引用。
//	          							 这份引用指向 s3 引用的对象。 也就是说引用地址是相同的。
	    String s4 = "11";//"11"是显示声明的，因此会直接去常量池中创建，创建的时候发现已经有这个对象了，此时也就是指向 s3 引用对象的一个引用。
	    System.out.println("s3 == s4: "+(s3 == s4));//true
	    System.out.println("s3 == intern_s3: "+(s3 == intern_s3));//true
	    System.out.println("intern_s3 == s4: "+(intern_s3 == s4));//true
	    
	    String s_3 = new String("1") + new String("1");//当使用 new String()构造的字符字符串 生成 s_3 引用
	    String s_3t = new String("1") + new String("1");//当使用 new String()构造的字符字符串 生成 s_3t 引用
	    String s_3x = "1" + "1";//直接的字符拼接，直接去常量池中创建，发现已经有这个对象了，此时指向 s3 引用对象的一个引用。
	    
	    System.out.println("s_3t == s_3: "+(s_3t == s_3));//false
	    System.out.println("s_3x == s3: "+(s_3x == s3));//true
	    System.out.println("s_3x == intern_s3: "+(s_3x == intern_s3));//true
	    System.out.println("s_3x == s4: "+(s_3x == s4));//true
	    System.out.println("s_3 == s3: "+(s_3 == s3));//false
	    System.out.println("s_3 == s4: "+(s_3 == s4));//false
	   
	    
	    String s_4 = "11";//"11"是显示声明的，因此会直接去常量池中创建，创建的时候发现已经有这个对象了，此时也就是指向 s3 引用对象的一个引用。
	    String intern_s_3 = s_3.intern();//如果常量池中存在当前字符串, 就会直接返回当前字符串指向的引用s3
	    
	    System.out.println("s_4 == intern_s_3: "+(s_4 == intern_s3));//true
	    System.out.println("s_3 == intern_s3: "+(s_3 == intern_s3));//false
	    System.out.println("intern_s_3 == intern_s3: "+(intern_s_3 == intern_s3));//true
	    System.out.println("s_3 == s4: "+(s_3 == s4));//false
	    System.out.println("s_3 == s_4: "+(s_3 == s_4));//false
	    
	    System.out.println("-----");
	    
	    String str1 = "Hollis";//常量池 中声明  "Hollis"
	    String str2 = new String("Hollis"); //当使用 new String() 构造的字符字符串 生成str2引用
	    String str3 = new String("Hollis").intern();///常量池中存在当前字符串, 就会直接返回当前字符串.str3== "Hollis"

	    System.out.println("str1 == str2: "+(str1 == str2));//false
	    System.out.println("str1 == str3: "+(str1 == str3));//true
	    System.out.println("str1 == str3: "+str1 == str3);//false ("str1 == str3: "+str1) == str3
	}
	
	//
	static final int MAX = 1000 * 10000;
	static final String[] arr = new String[MAX];

	public static void useIntern() throws Exception {
		
		//static final int MAX = 1000 * 10000;// 在C/C++中static是可以作用域局部变量的，但是在Java中切记：static/public 是不允许用来修饰局部变量。
		
	    Integer[] DB_DATA = new Integer[10];
	    Random random = new Random(10 * 10000);
	    
	    for (int i = 0; i < DB_DATA.length; i++) {
	        DB_DATA[i] = random.nextInt();
	    }
	    
		long t = System.currentTimeMillis();
	    for (int i = 0; i < MAX; i++) {
	        //arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length]));
	         arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length])).intern();
	    }

		System.out.println((System.currentTimeMillis() - t) + "ms");
	    System.gc();
	}
	
	/*
	 * 运行时常量的包装类
			我们知道,Integer是int的包装类,而包装类是对象,创建对象就需要消耗资源.
			java中的基本类型的包装类基本都实现了常量池技术.
			
			即Byte,Short,Integer,Long,Character,Boolean。
			这5种包装类默认创建了数值[-128，127]的相应类型的缓存数据
			
			但是超出此范围仍然会去创建新的对象。 两种浮点数类型的包装类Float,Double 并没有实现常量池技术。
	 *
	 * 需要注意的是:
			使用new,仍然会创建新对象. 比如 Integer i1 = new Integer(40);
			Integer a = 40；Java在编译的时候会直接将代码封装成
			Integer a =Integer.valueOf(40);，从而使用常量池中的对象。
	 * */
	@Test
	  public void fun07(){
	    Integer a = 10;
	    Integer b = 10;
	    System.out.println(a == b);
	    Integer c = 200;
	    Integer d = 200;
	    System.out.println(c == d);
	    Long e = 200L;
	    Long f = 200L;
	    System.out.println(e == f);
	    Long g = 20L;
	    Long h = 20L;
	    System.out.println(g == h);
	    Double i = 20.0;
	    Double j = 20.0;
	    System.out.println(i == j);
	  }
}
