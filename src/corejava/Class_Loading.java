package corejava;

/*
 * Java类从被加载到虚拟机内存中开始，到卸载出内存为止，它的整个生命周期包括：
 * 
 * 加载（Loading）
 * 验证（Verification）
 * 准备(Preparation)
 * 解析(Resolution)
 * 初始化(Initialization)
 * 使用(Using)
 * 卸载(Unloading)
 * 
 * 其中验证、准备、解析3个部分统称为连接（Linking），
 * 加载、验证、准备、初始化和卸载这5个阶段的顺序是确定的，
 * 类的加载过程必须按照这种顺序按部就班地开始，而解析阶段则不一定：
 * 它在某些情况下可以在初始化阶段之后再开始，
 * 这是为了支持Java语言的运行时绑定（也称为动态绑定或晚期绑定）。

 * 
 * */

/*
 * 加载（Loading）
 * (1). 通过一个类的全限定名来获取定义此类的二进制字节流（并没有指明要从一个Class文件中获取，可以从其他渠道，譬如：网络、动态生成、数据库等）
		 * 		zip压缩包 jar ear war
		 * 		网络 web applet
		 * 		运行时计算 动态代理
		 * 		其他文件生成 jsp
		 * 		数据库中 
		 * 		加密文件中
　 (2). 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构；
　 (3). 在内存中(对于HotSpot虚拟就而言就是方法区)生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口；

 * 加载阶段和连接阶段（Linking）的部分内容（如一部分字节码文件格式验证动作）是交叉进行的，
 * 加载阶段尚未完成，连接阶段可能已经开始，但这些夹在加载阶段之中进行的动作，仍然属于连接阶段的内容，
 * 这两个阶段的开始时间仍然保持着固定的先后顺序。
 * */
public class Class_Loading {
	
	public static void main(String[] args) {
		int tmp_static_int = LoadTest.n_p; //LoadTest...static{} Init!
		int tmp_final_static_int = LoadTest.sf; // 未初始化
		LoadTest.test(); //LoadTest...static{} Init! --> LoadTest test()...
		
		/*	LoadTest...static{} Init! // 静态块只执行一次
			LoadTest test()...
			Main Finally
		 * */
		
		LoadTest[] tmp_array = new LoadTest[10]; // 未初始化
		System.out.println("Main Finally");
		
		int tmp_son_visit_f = SubClass.i; // SuperClass Init!
		String CONSTANT = ConstClass.CONSTANT; // 未初始化
	}

	
}

/*
 * //类加载时机
 * 主动引用
 * 1) 	遇到new、getstatic、putstatic 或 invokestatic 这四条字节码指令
 * 		如果类没有进行过初始化，则需要先对其进行初始化。生成这四条指令的最常见的Java代码场景是：
 * 			1.使用new关键字实例化对象的时候
    		2.读取或设置一个类的静态字段（被final修饰，已在编译器把结果放入常量池的静态字段除外）的时候
    		3.调用一个类的静态方法的时候。
    	注意，newarray指令触发的只是数组类型本身的初始化，而不会导致其相关类型的初始化，
    	比如，new String[]只会直接触发String[]类的初始化，也就是触发对类[Ljava.lang.String的初始化，而直接不会触发String类的初始化

 * 2) 	使用 java.lang.reflect 包的方法对类进行反射调用的时候，如果类没有进行过初始化，则需要先触发其初始化。

 * 3) 	当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。

 * 4) 	当虚拟机启动时，用户需要指定一个要执行的主类（包含main()方法的那个类），虚拟机会先初始化这个主类。

 * 5) 	当使用jdk1.7动态语言支持时，如果一个java.lang.invoke.MethodHandle实例最后的解析结果REF_getstatic,REF_putstatic,REF_invokeStatic的方法句柄，并且这个方法句柄所对应的类没有进行初始化，则需要先出触发其初始化。
 
 * 6)   当一个接口中定义了 JDK8 中新加入的默认方法（被default关键字修饰），如果此类的实现类发生初始化，那该接口要在之前初始化
 * 
 * 注意，对于这六种会触发类进行初始化的场景，虚拟机规范中使用了一个很强烈的限定语：“有且只有”，
 * 这六种场景中的行为称为对一个类进行 主动引用。除此之外，所有引用类的方式，都不会触发初始化，称为 被动引用。
 * 
 * 被动引用
 * 1)、通过子类引用父类的静态字段，不会导致子类初始化
 * 2)、通过数组定义来引用类，不会触发此类的初始化
 *
*/

class SuperClass{
	
	static public int i = 1;
	private int j = 2;
	
	static {
		System.out.println("SuperClass Init!");
	}
}

interface SuperInter{
	
//	static {
//		System.out.println("SuperInter Init!");
//	}
}

class SubClass extends SuperClass{
	int j = i+1;
	int k = j+2;
	static {
		System.out.println("SubClass Init!");
	}
}

class ConstClass{

    static{
        System.out.println("ConstClass init!");
    }

    public static  final String CONSTANT = "hello world";
}


class LoadTest{
	public int i = 0;
	private int n  = 1;
	static int n_p = 1;
	static private int s = 2;
	final static int sf = 2;
	
	static {
		System.out.println("LoadTest...static{} Init!");
	}
	
	public LoadTest() {
		System.out.println("LoadTest..LoadTest() Init!");
	}
	
	static void test() {
		System.out.println("LoadTest test()...");
	}
}
