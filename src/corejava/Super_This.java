package corejava;

import org.junit.jupiter.api.Test;

public class Super_This {
	public static void main(String[] args) {
		new suC().print();
	}
}

class suA{
	int a =10;
}

class suB extends suA{
	int a = super.a+10;
	
}

class suC extends suB{
	public void print() {
		System.out.println(super.a);
	}
}

//==========return 中使用===============
//希望将句柄返回给当前对象，经常在 return 中使用

class Leaf {
	private int i = 0;
	
	Leaf increment() {
		i++;
		return this;//由于 increment()通过 this 关键字返回当前对象的句柄，所以可以方便地对同一个对象执行多项操作。
	}
	void print() {
		System.out.println("i = " + i);
	}
	
	//@Test
	public static void main(String[] args) {
		Leaf x = new Leaf();
		x.increment().increment().increment().print();//i = 3
	}
}

//=========构造器中使用===========
//若为一个类写了多个构建器，那么经常都需要在一个构建器里调用另一个构建器，以避免写重复的代码。可用 this 关键字做到这一点

class Flower {
	private int petalCount = 0;
	private String s = new String("null");
	
	Flower(int petals) {
		petalCount = petals;
		System.out.println("Constructor w/ int arg only, petalCount= "+ petalCount);
	}
	
	Flower(String ss) {
		System.out.println("Constructor w/ String arg only, s=" + ss);
		s = ss;
	}
	
	Flower(String s, int petals) {
		this(petals);
		//! this(s); // Can't call two!
		
		//由于自变量 s 的名字以及成员数据 s 的名字是相同的，所以会出现混淆。为解决这个问题，可用 this.s 来引用成员数据。
		this.s = s; // Another use of "this"
		System.out.println("String & int args");
	}
	
	Flower() {
		this("hi", 47);
		System.out.println("default constructor (no args)");
	}
	
	void print() {
		//! this(11); // Not inside non-constructor!
		System.out.println(
		"petalCount = " + petalCount + " s = "+ s);
	}
	
	public static void main(String[] args) {
		Flower x = new Flower();
		x.print();
	}
}

/*	this
 *	为已调用了其方法的那个对象生成相应的句柄。可象对待其他任何对象句柄一样对待这个句柄.
 *  只能在方法内部使用,假若准备从自己某个类的另一个方法内部调用一个类方法，就不必使用this.
	只能用于那些特殊的类(需明确使用当前对象的句柄)。例假若您希望将句柄返回给当前对象，经常在 return 中使用.
 *
 *  如果有两个同类型的对象，分别叫作 a 和 b，那么您也许不知道如何为这两个对象同时调用一个 f()方法：
		class Banana { void f(int i) { / ... / } }
		Banana a = new Banana(), b = new Banana();
		a.f(1); ==>>  Banana.f(a,1);
		b.f(2); ==>>  Banana.f(b,2);
	
	若只有一个名叫 f()的方法，它怎样才能知道自己是为 a 还是为 b 调用的呢？
		为了能用简便的、面向对象的语法来书写代码—— 亦即“将消息发给对象”，编译器为我们完成了一些幕后
		工作。其中的秘密就是第一个自变量传递给方法f()，而且那个自变量是准备操作的那个对象的句柄。所以
		前述的两个方法调用就变成了下面这样的形式：
		Banana.f(a,1);
		Banana.f(b,2);
	这是内部的表达形式，我们并不能这样书写表达式，并试图让编译器接受它。但是，通过它可理解幕后到底
	发生了什么事情。
	
	假定我们在一个方法的内部，并希望获得当前对象的句柄。由于那个句柄是由编译器“秘密”传递的，所以
	没有标识符可用。然而，针对这一目的有个专用的关键字： this。 this 关键字（注意只能在方法内部使用）
	可为已调用了其方法的那个对象生成相应的句柄。可象对待其他任何对象句柄一样对待这个句柄。但要注
	意，假若准备从自己某个类的另一个方法内部调用一个类方法，就不必使用this。只需简单地调用那个方法
	即可。当前的 this 句柄会自动应用于其他方法。
 * 
 */