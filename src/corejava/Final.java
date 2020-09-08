package corejava;

public class Final {

	public static void main(String[] args) {
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

 