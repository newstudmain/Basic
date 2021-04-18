package corejava;

import org.junit.jupiter.api.Test;

/*
 * public
 * main方法作为应用程序的入口，该方法是程序启动时候由Java虚拟机调用的，所以访问权限当然是需要设置为public。
 * 
 * static
 * 如果不是静态的方法，就需要通过对象来调用。而当Java程序运行，虚拟机调用main方法的时候，没有必要创建含有main方法类的对象。
 * 例如：类A中声明了main方法，当运行类A的时候，虚拟机直接调用类A的main方法，没有必要创建A对象，然后通过对象调用main方法。
 * 
 * void
 * main方法在退出时，并没有给系统返回退出代码，而是在需要时候使用System.exit(int status);方法来返回，所以返回类型为void。
 * 
 * String [] args
 * 该字符串数组用来运行时接收用户输入的参数，具体长度取决于用户输入的参数的长度，如果用户没有输入参数，则长度为0.
 * 
 * */

public class Main_Method {
	
	/*
	 * 若注释mian方法1，运行时报错
	 * 
	 * 错误: 在类 corejava.Main_Method 中找不到 main 方法, 请将 main 方法定义为:
	   		 public static void main(String[] args)
			 否则 JavaFX 应用程序类必须扩展javafx.application.Application
			 
	 * 所以，Java虚拟机要求的main方法必须是按照 mian方法1 声明方式，否则，将认为你没有给程序定义好入口（main方法）。
	 * */
	
	public static void main(char c) {
		System.out.println("mainTop");
		System.out.println(c);
	}
	
	
	public static void main(String[] args) {
		System.out.println("main1");
		
		String[] temp_arr = args;
		System.out.println(temp_arr.length);
		for (String str : temp_arr) {
			System.out.println(str);
		}
		
		main(100); //100; 此时能调用到重载的main函数
		//System.out.println("-----");
	}
	
	public static void main(String arg) {
		System.out.println("main2");
		System.out.println(arg);
	}
	
	public static void main(int i) {
		System.out.println("main3");
		System.out.println(i);
		//main(new String[] {"s","t","r"});
		
	}
	
	//当前类可以调用当前重载 main 方法
	/*
	 *  main1
		1
		Stirng[]...1
		main3
		100
		main2
		String2
		main3
		200
	 * */
	@Test
	public void test_M() {
		main(new String[] {"Stirng[]...1"});
		main("String2");
		main(200);
	}
}

class S_Main extends Main_Method {
	
	//当前类没有重写 public static void main(String[] args) 时，将调用父类的 public static void main(String[] args) 方法
	public void test() {
		main("s"); //main1 0 ; (//main(300);test_M();)
		main(300); //main1 0;  (//test_M();)
		test_M();//main1 0 main3 100; 
		main(new String[] {"S_[]"});//main1 0 main3 100; 
		
		/*
		 * 以上输出为：
						main1
						0
						main3
						100
		 * 说明 main(String[] args) 方法的调用与 调用顺序和数量无关，只会调用 main(String[] args) 方法
		 * */
	}
	
	//只有 main(String[] args) 方法才会被调用
	public static void main(String arg) {
		System.out.println("S_Main_main1");
		System.out.println(arg);
	}
	
	
	//当子类重写了 main(String[] args) 时，调用子类此main方法
//	public static void main(String[] args) {
//		System.out.println("S_Main...");
//	}
}
