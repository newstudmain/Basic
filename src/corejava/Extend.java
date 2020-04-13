package corejava;


class ExA{
	private int i =1;//不能够继承父类的private成员变量
	int j=2;
	protected int k=3;//如果子类和父类在同一个包下，则子类能够继承；否则，子类不能够继承；
	public int g =4;
	public final int h=5;
	
	public static int m =6;
	
	{
		System.out.println("ExA{}...{ }"+"j: "+j);
	}
	static {
		System.out.println("ExA{}...static{ }");
	}
	
	/*
	 * 子类是不能够继承父类的构造器，
	 * 		如果父类的构造器都是带有参数的，则必须在子类的构造器中显示地通过super关键字调用父类的构造器并配以适当的参数列表。
	 * 		如果父类有无参构造器，则在子类的构造器中用super关键字调用父类构造器不是必须的，
	 * 		如果没有使用super关键字，系统会自动调用父类的无参构造器
	 * 
	 * 父类的构造器调用以及初始化过程一定在子类的前面，初始化成员变量在构造器前面。
	 * */
	public ExA() {}
	public ExA(String s ,int i) {}
	
	//类的private方法会隐式地被指定为final方法
	//不能够继承父类的private成员方法
	private void priPri() {
		System.out.println("ExA{}...priPri()");
	}
	
	//只有在想明确禁止 该方法在子类中被覆盖的情况下 才将方法设置为final
	private final void f_priPri() {
		System.out.println("ExA{}...priPri()");
	}
	
	public void priPub() {
		System.out.println("ExA{}...priPub()");
	}
	
	public static void staTs() {
		System.out.println("ExA{}...static void staTs()");
	}
}

class ExB extends ExA{
	
	int j =super.j+2;//注释此句，b的子类中找不到全局j属性，就会向上找父类a中的j属性值。
	
	public void priTest() {
		
		int g =4+10;//如果在子类中出现了同名称的成员变量，则会发生隐藏现象，即子类的成员变量会屏蔽掉父类的同名成员变量。
		int j=super.j+2;//局部j,相当于隐藏了父类j,
		//j=j+2;//此时父类的将被修改，super.j 为4
		
	  //System.out.println(i);//私有属性不能继承
		System.out.println("ExB{}...j+2: "+j);//4
		System.out.println("super.j: "+super.j);//2
		
		System.out.println(k);//3
		
		System.out.println(g);//14
		System.out.println("super.g: "+super.g);//4
		
		System.out.println(h);//5
		
		System.out.println(m);//6
		m=m+10;
		System.out.println("ExA{}...static K+: "+m);//16
		
		//priPri();
		priPub();
		System.out.println("-----");
	}
	
	
	public void priPub() {
		//super.priPub();//在子类中访问父类中同名成员方法，需要使用super关键字来进行引用。
		System.out.println("ExB{}...priPub()");
	}//如果在子类中出现了同名称的成员方法，则称为覆盖，即子类的成员方法会覆盖掉父类的同名成员方法。
	
	public static void staTs() {
		System.out.println("ExB{}...static void staTs()");
	}
	
//	public void staTs() {
//		System.out.println("ExB{}...static void staTs()");
//	}//不能覆盖为非静态
}


/*
 * 子类继承父类，在当前子类中对父类非静态属性值的修改，只在正在修改的当前类中有效。
 * 当其他类 或者 孙子类中 调用 父类属性时 ，其父类属性值依然为初始值，未被上一子类改变，体现了父类的模板作用。
 * 
 * 如果父类中的是static属性，那么可以在子类的中直接改掉其属性值，
 * static属性是属于类的，在创建子类对象时必然存在，
 * 下一次再创建新的子类对象，用的就不是创建第一个子类对象时的父类模板了。
 * 
 * class a{
 * 	int j = 2;
 * }
 * 
 * class b extends a{
 * 		j=j+2;
 * 		...
		System.out.println("ExB{}...j+2: "+j);//4
		System.out.println("super.j: "+super.j);//4
 * }
 * 
 * class c extends a{
 * 		...
 * 		System.out.println(super.j);//2
 * }
 * 
 * */
class ExC extends ExA{
	
	public void print() {
		System.out.println("ExC...ExB 修改父类 属性j+2: "+super.j);//2
		System.out.println("ExC...ExB 修改父类 属性m+10: "+super.m);//16
	}
}

class ExD extends ExB{
	public void print() {
		System.out.println("ExD...ExB 修改父类 属性j+2: "+super.j);//4
		System.out.println("ExD...ExB 修改父类 属性m+10: "+super.m);//16
	}
}

public class Extend {
	public static void main(String[] args) {
		new ExB().priTest();
		
		new ExC().print();
		new ExD().print();
		
		new ExB().priPub();
		new ExA().priPub();
		
		System.out.println("---ExA a = new ExB()---");
		ExA a = new ExB();
		a.priPub();//ExB{}...priPub()
		a.staTs();//ExA{}...static void staTs()
		
		/*
		 * 覆盖只针对非静态方法（终态方法不能被继承，所以就存在覆盖一说了）
		 * 隐藏是针对成员变量和静态方法的。
		 * 这2者之间的区别是：
		 * 覆盖受RTTI（Runtime type  identification）约束的，而隐藏却不受该约束。
		 * 也就是说只有覆盖方法才会进行动态绑定，而隐藏是不会发生动态绑定的。
		 * 在Java中，除了static方法和final方法，其他所有的方法都是动态绑定。
		 * */
	}
}
