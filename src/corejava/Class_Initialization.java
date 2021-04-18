package corejava;

//初始化(Initialization)
/*
 *类初始化阶段是类加载过程的最后一步，到了初始化阶段，才真正开始执行类中定义的Java程序代码（或者说是字节码）。
 *在准备阶段，变量已经赋过一次系统要求的初始值，
 *而在初始化阶段，则根据程序员通过程序制定的主观计划去初始化类变量和其他资源，
 *或者可以从另外一个角度来表达：初始化阶段是执行类构造器 <clinit>() 方法的过程。
 *
 * */


/*
 *<clinit>() 方法是由编译器自动收集类中的所有类变量的赋值动作和静态语句块 (static{}块) 中的语句合并产生的，
 *编译器收集的顺序是由语句在源文件中出现的顺序所决定的，
 *静态语句块中只能访问到定义在静态语句块之前的变量，定义在它之后的变量，在前面的静态语句块可以赋值，但是不能访问.
 * */
public class Class_Initialization {
	
	static private int before = 0;
	
	static {
		
		//静态语句块中只能访问到定义在静态语句块之前的变量
		int k = before;
		
		//int j = after1; //Cannot reference a field before it is defined
		
		//定义在它之后的变量，在前面的静态语句块可以赋值，但是不能访问
		after2 = 1;
		
	}
	
	static private int after1 = 0;
	static private int after2 = 0;

	public static void main(String[] args) {
		System.out.println(Son_clinit.s);
	}
}

/*
 *<clinit>() 方法与 类的构造函数（或者说实例构造器 <init>() 方法）不同，
 *它不需要显式地调用父类构造器，虚拟机会保证在子类的 <clinit>() 方法执行之前，父类的 <clinit>() 方法已经执行完毕。
 *因此在虚拟机中第一个被执行的 <clinit>() 方法的类肯定是java.lang.Object

 *由于父类的 <clinit>() 方法先执行，也就意味着父类中定义的静态语句块要优先于子类的变量赋值操作
 * */

class Parent_clinit {
	
	static public int p = 1;
	
	static {
		p = 10;
		System.out.println("Parent_clinit...static{}");
	}
	
	//p = 2; // Syntax error on token "p", VariableDeclaratorId expected after this token
	{
		p = 2;
	}
}

class Son_clinit extends Parent_clinit{
	
	//p = 100; // Syntax error on token "p", VariableDeclaratorId expected after this token
	
	{
		p = 20;
	}
	
	static public int s = p;
	
	static {
		p = 100;
		System.out.println("Son_clinit...static{}");
		System.out.println("Son_clinit...int s = " + s);
		System.out.println("Son_clinit...int p = " + p);
	}
}

/*
 * 
	Parent_clinit...static{}
	Son_clinit...static{}
	Son_clinit...int s = 10
	Son_clinit...int p = 100
	10
 * 
 * */

/*
 * <clinit>() 方法对于类或接口来说并不是必需的，
 * 如果一个类中没有静态语句块，也没有对变量的赋值操作，那么编译器可以不为这个类生成 <clinit>() 方法。
 * 
 * 接口中不能使用静态语句块，但仍然有变量初始化的赋值操作，因此接口与类一样都会生成 <clinit>() 方法。
 * 但接口与类不同的是，执行接口的 <clinit>() 方法不需要先执行父接口的 <clinit>() 方法。
 * 只有当父接口中定义的变量使用时，父接口才会初始化。另外，接口的实现类在初始化时也一样不会执行接口的 <clinit>() 方法。
 * 
 * 虚拟机会保证一个类的 <clinit>() 方法在多线程环境中被正确地加锁、同步，如果多个线程同时去初始化一个类，
 * 那么只会有一个线程去执行这个类的 <clinit>() 方法，其他线程都需要阻塞等待，直到活动线程执行 <clinit>() 方法完毕。
 * 如果在一个类的 <clinit>() 方法中有耗时很长的操作，就可能造成多个进程阻塞，在实际应用中这种阻塞往往是很隐蔽的。
 * 
 * */

interface I_clinit{
	static int i = 0;
	int j = 1;
	
	// 接口中不能使用静态语句块，但仍然有变量初始化的赋值操作，因此接口与类一样都会生成 <clinit>() 方法。
	//static {}
}

interface I_clinit_s extends I_clinit{
	
}





















