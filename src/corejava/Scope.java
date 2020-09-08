package corejava;

/* JAVA会自动初始化成员变量，但自动初始化会带来一些错误，
 * 所以在使用变量之前最好对其进行初始化，以保证变量的使用符合自己想要的效果；
 * 默认初始化只对于JAVA成员变量有效
 * 
 * 方法级和块级的变量没有默认的初始值，必须被显示地初始化，否则不能访问。
 * 
 * 同一作用域范围的包裹下成员变量名和局部变量名是可以变量名相同的。
 * 在方法中使用变量时，如果不指明使用成员变量还是局部变量，那么默认使用局部变量（就近原则），
 * 但是如果局部变量超出了它本身的作用域范围则会失效，被JVM垃圾回收，那么则可以重复命名此变量。
 * 
 * 同一个作用域范围的包裹下局部变量和局部变量不可以变量名相同（作用域内不能重复命名）。
 * 
 * ======================
 *  数据类型        	默认的初始值
    int         0
    float       0.0f
    double      0.0
    boolean     false
    char        ''
	引用数据类型        	null
 *========================
 * */

public class Scope {
	//作用域由{}决定
	//默认初始化只对于JAVA成员变量有效
	
	int x;// 对象实例级变量 
	static int X;// 类级变量
	
	// 属性块，在类初始化属性时候运行
	{
		int y;// 块级变量
		int x = 14;
		{
//			int x = 15;// 非法变量
		}
		System.out.println(x);//14
//		System.out.println(y);// 块级的变量没有默认的初始值，必须被显示地初始化，否则不能访问
	}
	
	public void scope(int z) {
		int k = 10;// 方法级变量
//		static int t = 1;// static不能作用于局部变量，只能作用于域
		int j;
		
		System.out.println(x);
		System.out.println(z);
		System.out.println(k);
//		System.out.println(y);// 不能引用{}内的y变量
//		z = j+2;// 如果要使用局部变量必须要对其进行初始化，否则会得到编译错误
		z = k+2;
		z = x+2;
		z = z+2;
		
		if (z>0) {
			int h = 2;// 块级变量
			System.out.println(z+h);
		}
//		System.out.println(h);// 不能引用{}内的h变量
	}
	
	//不指明使用成员变量还是局部变量，那么默认使用局部变量（就近原则）
		//成员变量
		public int i = 0;
		
		public void scopeSame() {
			//局部变量
			for(int i=0; i<1; i++) {
				//变量i指for循环块内局部变量
				System.out.println(i);
			}
			//局部变量
			int i = 1;// 局部变量超出了它本身的作用域范围则会失效，被JVM垃圾回收，那么则可以重复命名此变量。
			//变量i指局部变量
			System.out.println(i);
			
			{
//				int i = 1;// 同一个作用域范围的包裹下局部变量和局部变量不可以变量名相同（作用域内不能重复命名）。
			}
		}
	
	public static void main(String[] args) {
		new Scope();
	}
}

class ScopeObj{
	
	Scope s;
	
	{
		Scope sBlock = new Scope();
		{
//			Scope sBlock = new Scope();
		}
		System.out.println(s);
		System.out.println(sBlock);
	}
	
	public void scope(Scope sf) {
//		System.out.println(sBlock);
		/*
		 * 那么句柄 sBlock 会在作用域的终点处消失。然而， sBlock 指向的 String 对象依然占据着内存空间。
		 * 在上面这段代码里，我们没有办法访问对象，因为指向它的唯一一个句柄已超出了作用域的边界。
		 * */
	}
	
}


















