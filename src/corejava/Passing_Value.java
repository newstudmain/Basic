package corejava;

import java.util.Enumeration;
import java.util.Vector;

import org.junit.jupiter.api.Test;

public class Passing_Value {

	/*
		intPass... 20
		intTest()... 10
	 * 
	 * 基本数据类型的传递(byte char short int float long double boolean)
	 * 		对主数据类型的赋值是非常直接的。由于主类型容纳了实际的值，而且并非指向一个对象的句柄，
	 * 		所以在为其赋值的时候，可将来自一个地方的内容复制到另一个地方。
		 * 		例如，假设为主类型使用  A=B，那么 B 处的内容就复制到 A。
		 * 		若接着又修改了 A，那么 B 根本不会受这种修改的影响。
	 * 
	 * 
	 * intTest()和intPass(int i)
			 i的改动，只是改变了各自当前栈帧（ 方法所在栈帧）里的内容，
			  当方法执行结束之后，这些局部变量都会被销毁，
			 intTest方法所在栈帧重新回到栈顶，成为当前栈帧，
			  再次输出i时，依然是初始化时的内容。
	 * 值传递：传递的是真实内容的一个副本，对副本的操作不影响原内容，也就是形参怎么变化，不会影响实参对应的内容。
	 * */
	@Test
	public void intTest() {
		int i = 10;
		intPass(i);
		System.out.println("intTest()... "+i);
	}
	public void intPass(int i) {
		i = i*2;
		System.out.println("intPass()... "+i);
	}
	
	/*
		stringPass()... hello
		intTest()... he
	 *
	 * 
	 * 
	 * 
	 * */
	@Test
	public void stringTest() {
		String s = "he";
		stringPass(s);
		System.out.println("intTest()... "+s);
	}
	public void stringPass(String s) {
		s = s+"llo";
		System.out.println("stringPass()... "+s);
	}
	
	/*
	 * 引用传递：也就是指向真实内容的地址值，在方法调用时，实参的地址通过方法调用被传递给相应的形参，
	 * 		      在方法体内，形参和实参指向通愉快内存地址，对形参的操作会影响的真实内容。
	 * 
		h inside f(): corejava.PassHandles@4a87761d
		h inside main(): corejava.PassHandles@4a87761d
	 * 
	   	打印出对象的类，接着是那个对象所在的位置（不是句柄，而是对象的实际存储位置）。
	   	可以看到，都是同一个对象。这比复制一个新的 PassHandles 对象有效多了，使我们能将一个参数发给一个方法。
	 * 
	 * 别名问题
		别名意味着多个句柄都试图指向同一个对象，就象前面的例子展示的那样。若有人向那个对象里写入一
		点什么东西，就会产生别名问题。若其他句柄的所有者不希望那个对象改变，恐怕就要失望了。
	 * 
	 * */
	@Test
	public void handleTest() {
		
		PassHandles handle1 = new PassHandles();
		PassHandles handle2 = handle1;
		
		System.out.println(handle1);
		System.out.println(handle2);
		
		System.out.println("------");
		
		PassHandles h = new PassHandles();
		h.f(h);
		System.out.println("h inside main(): " + h);
	}
	
	/*
		corejava.Alias1@4a87761d
		corejava.Alias1@4a87761d
		-----
		x: 7
		y: 7
		Incrementing x
		x: 8
		y: 8
	 *
	 *Alias1 y = x; // Assign the handle
	 * 	它会新建一个 Alias1 句柄，但不是把它分配给由 new 创建的一个新鲜对象，而是分配给一个现有的句柄。所
		以句柄 x 的内容—— 即对象 x 指向的地址—— 被分配给 y，所以无论 x 还是 y 都与相同的对象连接起来。这
		样一来，一旦 x 的 i 在下述语句中增值：
			x.i++;
		y 的 i 值也必然受到影响。从最终的输出就可以看出.
		
		此时最直接的一个解决办法就是干脆不这样做：不要有意将多个句柄指向同一个作用域内的同一个对象。这
		样做可使代码更易理解和调试。然而，一旦准备将句柄作为一个自变量或参数传递—— 这是 Java 设想的正常
		方法—— 别名问题就会自动出现，因为创建的本地句柄可能修改“外部对象”（在方法作用域之外创建的对
		象）。
	 * 
	 * 
	 * */
	@Test
	public void alias1Test() {
		Alias1 x = new Alias1(7);
		Alias1 y = x; // Assign the handle
		
		System.out.println(x);
		System.out.println(y);
		
		System.out.println("-----");
		
		System.out.println("x: " + x.i);
		System.out.println("y: " + y.i);
		System.out.println("Incrementing x");
		x.i++;
		System.out.println("x: " + x.i);
		System.out.println("y: " + y.i);
	}
	
	/*
		x: 7
		Calling f(x)
		x: 8
	 *
	 * 
	 *  方法改变了自己的参数—— 外部对象。
	 *  一旦遇到这种情况，必须判断它是否合理，用户是否愿意这样，以及是不是会造成问题。
		通常，我们调用一个方法是为了产生返回值，或者用它改变为其调用方法的那个对象的状态（方法其实就是
		我们向那个对象“发一条消息”的方式）。很少需要调用一个方法来处理它的参数；这叫作利用方法的“副
		作用”（ Side Effect）。所以倘若创建一个会修改自己参数的方法，必须向用户明确地指出这一情况，并警
		告使用那个方法可能会有的后果以及它的潜在威胁。由于存在这些混淆和缺陷，所以应该尽量避免改变参数。
		若需在一个方法调用期间修改一个参数，且不打算修改外部参数，就应在自己的方法内部制作一个副本，从
		而保护那个参数。
	 * */
	@Test
	public void alias2Test() {
		Alias2 x = new Alias2(7);
		
		Alias2 y = x;
		System.out.println("x: " + x.i);
		y.i++;
		System.out.println("x: " + x.i);
		
		System.out.println("-----");
		
		System.out.println("x: " + x.i);
		System.out.println("Calling f(x)");
		x.f(x);
		System.out.println("x: " + x.i);
	}
	
	/*总结：
	 * 
		Java 中的所有自变量或参数传递都是通过传递句柄进行的。也就是说，当我们传递“一个对
		象”时，实际传递的只是指向位于方法外部的那个对象的“一个句柄”。所以一旦要对那个句柄进行任何修
		改，便相当于修改外部对象。此外：
			■参数传递过程中会自动产生别名问题
			■不存在本地对象，只有本地句柄
			■句柄有自己的作用域，而对象没有
			■对象的“存在时间”在 Java 里不是个问题
			■没有语言上的支持（如常量）可防止对象被修改（以避免别名的副作用）
	 * 
	 * 按值传递
			最常见的意思是获得要传递的任何东西的一个本地副本，但这里真正的问题是如何看待
			自己准备传递的东西。对于“按值传递”的含义，目前存在两种存在明显区别的见解：
			
				(1) Java 按值传递任何东西。若将基本数据类型传递进入一个方法，会明确得到基本数据类型的一个副本。
				但若将一个句柄传递进入方法，得到的是句柄的副本。所以人们认为“一切”都按值传递。
				当然，这种说法也有一个前提：句柄肯定也会被传递。但 Java 的设计方案似乎有些超前，
				允许我们忽略（大多数时候）自己处理的是一个句柄。也就是说，它允许我们将句柄假想成“对象”，
				因为在发出方法调用时，系统会自动照管两者间的差异。
				
				(2) Java 主要按值传递（无自变量），但对象却是按引用传递的。得到这个结论的前提是句柄只是对象的一
				个“别名”，所以不考虑传递句柄的问题，而是直接指出“我准备传递对象”。由于将其传递进入一个方法
				时没有获得对象的一个本地副本，所以对象显然不是按值传递的。
				Sun 公司似乎在某种程度上支持这一见解，因为它“保留但未实现”的关键字之一便是byvalue（按值）。
				但没人知道那个关键字什么时候可以发挥作用。
			
			尽管存在两种不同的见解，但其间的分歧归根到底是由于对“句柄”的不同解释造成的。
			大家不久就会知道，这个问题争论下去其实是没有意义的—— 最重要的是理解一个
			句柄的传递会使调用者的对象发生意外的改变。
	 *
	 * ####
	 * 	- 如果是对基本数据类型的数据进行操作，由于原始内容和副本都是存储实际值，并且是在不同的栈区，因此形参的操作，不影响原始内容。
		- 如果是对引用类型的数据进行操作，分两种情况，
			一种是形参和实参保持指向同一个对象地址，则形参的操作，会影响实参指向的对象的内容。
			一种是形参被改动指向新的对象地址（如重新赋值引用），则形参的操作，不会影响实参指向的对象的内容。

	 * 
	 * */
	
	
	/*
	 * 克隆对象
	 * 		若需修改一个对象，同时不想改变调用者的对象，就要制作该对象的一个本地副本。这也是本地副本最常见
			的一种用途。若决定制作一个本地副本，只需简单地使用 clone()方法即可。 Clone 是“克隆”的意思，即制
			作完全一模一样的副本。这个方法在基础类 Object 中定义成“ protected”（受保护）模式。但在希望克隆
			的任何衍生类中，必须将其覆盖为“ public”模式。例如，标准库类 Vector 覆盖了 clone()，所以能为
			Vector 调用 clone()
	 * 	
	 * */
	@Test
	public void cloneTest() {
		
			Vector v = new Vector();
			for(int i = 0; i < 10; i++ )
				v.addElement(new Int(i));
			System.out.println("v: " + v);
			
			Vector v2 = (Vector)v.clone();
			// Increment all v2's elements:
			for(Enumeration e = v2.elements();
					e.hasMoreElements(); )
			((Int)e.nextElement()).increment();
			// See if it changed v's elements:
			System.out.println("v: " + v);
			
			System.out.println(v);
			
	}
}

class PassHandles {
	static void f(PassHandles h) {
		System.out.println("h inside f(): " + h);
	}
}

class Alias1 {
	int i;
	Alias1(int ii) { 
		i = ii; 
	}
}

class Alias2 {
	int i;
	Alias2(int ii) {
		i = ii; 
	}
	static void f(Alias2 handle) {
		handle.i++;
	}
}	

class Int {
	private int i;
	public Int(int ii) {
		i = ii;
	}
	public void increment() {
		i++;
	}
	public String toString() {
		return Integer.toString(i);
	}
}