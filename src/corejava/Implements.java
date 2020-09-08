package corejava;

public class Implements {

	public static void main(String[] args) {
		new impTest1().a();
		new impTest2().a();
	}
}

/*
 * 接口一般是只有方法声明没有定义的，那么java特别指出实现接口是有道理的，
 * 因为继承就有感觉是父类已经实现了方法，而接口恰恰是没有实现自己的方法，仅仅有声明，
 * 也就是一个方法头没有方法体。因此你可以理解成接口是子类实现其方法声明而不是继承其方法。
 * 但是一般类的方法可以有方法体，那么叫继承比较合理。
 * 
 * */
interface i_A {
	//int a;
	int a = 1;//接口属性默认情况下为  public  static final
	//void a() {};//接口方法没有主体,主体由实现类提供
	void a();//接口中的方法在默认情况下为   public abstract
}

interface i_B {
	int b = 2;
	void a();//同时实现的多个接口中，同名方法只会出现一个
	void b();
}

interface i_C {
	int c = 3;
	void a();//同时实现的多个接口中，同名方法只会出现一个
	void b();//同时实现的多个接口中，同名方法只会出现一个
	void c();
}



/*
 *  a）实现一个接口就是要实现该接口的所有的方法(抽象类除外)。
	b）接口中的方法都是抽象的。
	c）多个无关的类可以实现同一个接口，一个类可以实现多个无关的接口。
 * */
class impTest1 implements i_A{
	@Override
	public void a() {
		// TODO Auto-generated method stub
		System.out.println("impTest1 implements A...");
	}
}

class impTest2 implements i_A,i_B,i_C{
	@Override
	public void a() {
		// TODO Auto-generated method stub
		System.out.println("impTest2 implements A,B,C...");
	}

	@Override
	public void b() {
		// TODO Auto-generated method stub
		System.out.println("impTest1 implements A,B,C....");
	}

	@Override
	public void c() {
		// TODO Auto-generated method stub
		System.out.println("impTest1 implements A,B,C....");
	}
}


