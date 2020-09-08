package corejava;

public class Abstract_Interface {

}

//在abstract class方式中，可以有自己的数据成员，也可以有非abstarct的成员方法
abstract class Demo_a {
	int h;
	int i = 0;
	static int j;
	final int k = 2;
	private int p;
	
	abstract void method1();
	abstract void method2();
	void method32() {};
}

//interface 方式的实现中，Demo只能够有静态的不能被修改的数据成员
//也就是必须是 static final 的，不过在interface中一般不定义数据成员，所有的成员方法都是abstract的。
interface Demo_i {
	int i = 0;
	//int h;
	//static int j;
	final int k = 2;
	//private int p; //only public, static & final are permitted
	public static final int g = 1;
	
    void method1();//所有的成员方法都是abstract的,没有方法体{}
    void method2();
}