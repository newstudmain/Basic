package corejava;

/*
 * final 数据
	许多程序设计语言都有自己的办法告诉编译器某个数据是“常数”。常数主要应用于下述两个方面：
	(1) 编译期常数，它永远不会改变
	(2) 在运行期初始化的一个值，我们不希望它发生变化
	对于编译期的常数，编译器（程序）可将常数值“封装”到需要的计算过程里。
	也就是说，计算可在编译期间提前执行，从而节省运行时的一些开销。
	在 Java 中，这些形式的常数必须属于基本数据类型（Primitives），而且要用 final 关键字进行表达。
	在对这样的一个常数进行定义的时候，必须给出一个值。
	
	无论 static 还是 final 字段，都只能存储一个数据，而且不得改变。
	若随同对象句柄使用 final，而不是基本数据类型，它的含义就稍微让人有点儿迷糊了。
	对于基本数据类型， final 会将值变成一个常数；但对于对象句柄， final 会将句柄变成一个常数。
	进行声明时，必须将句柄初始化到一个具体的对象。而且永远不能将句柄变成指向另一个对象。
	然而，对象本身是可以修改的。 Java对此未提供任何手段，可将一个对象直接变成一个常数。
	（但是，我们可自己编写一个类，使其中的对象具有“常数”效果）。这一限制也适用于数组，它也属于对象。
 * 
 * */
public class Final {

	public static void main(String[] args) {
	
		//final变量
        String a = "hello2"; 
        final String b = "hello";
        String d = "hello";
        String c = b + 2; 
        String e = d + 2;
        System.out.println((a == c));//true
        System.out.println((a == e));//false
        
        /*
         * 当final变量b 是基本数据类型以及String类型时，
         * 如果在编译期间能知道它的确切值，则编译器会把它当做编译期常量使用。
         * 也就是说在用到该final变量的地方，相当于直接访问的这个常量，不需要在运行时确定。
         * 因此在上面的一段代码中，由于变量b 被final修饰，因此会被当做编译器常量，
         * 所以在使用到 b 的地方会直接将 变量b 替换为它的  值。
         * 
         * 而对于 变量d 的访问却需要在运行时通过链接来进行。
         * 不过要注意，只有在编译期间能确切知道final变量值的情况下，编译器才会进行这样的优化。
         * */
        
        String a1 = "hello2"; 
        final String b1 = getHello();
        String c1 = b1 + 2; 
        System.out.println((a1 == c1));//false
        
        final MyClassT myClass = new MyClassT();
        System.out.println(++myClass.i);//1
        //引用变量被final修饰之后，虽然不能再指向其他对象，但是它指向的对象的内容是可变的。
        
        MyClassT myClass1 = new MyClassT();
        MyClassT myClass2 = new MyClassT();
        System.out.println(myClass1.i);//0
        
        System.out.println(myClass1.i1);//0.1672762687902194
        System.out.println(myClass1.j);//0.4398751794584551
        System.out.println(myClass2.i1);//0.5724310771817517
        System.out.println(myClass2.j);//0.4398751794584551
        //static作用于成员变量用来表示只保存一份副本，而final的作用是用来保证变量不可变
	}
	
    public static String getHello() {
        return "hello";
    }
}

//final修饰一个类时，表明这个类不能被继承。
//除非这个类在以后不会用来继承或者出于安全考虑，尽量不要将类设计为final类
final class f_clas{
	int i;
	final int a;//或者构造器中进行初始化赋值
	final int b = 0;//成员变量可以根据需要设为final,必须在定义时赋值
	final Object obj = new Object();
	
	public f_clas (int a) {
		this.a = a;//
	}
	
	void a() {};//final类中的所有成员方法都会被隐式地指定为final方法
	
	//b = 1; //基本数据类型的变量，则其数值一旦在初始化之后便不能更改
	//obj  = new Object(); //是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象
} 

class MyClassT {
    public int i = 0;
    
    public final double i1 = Math.random();
    public static double j = Math.random();
}

//class ExFinal extends f_clas {}

 