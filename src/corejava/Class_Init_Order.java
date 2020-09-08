package corejava;

public class Class_Init_Order {

	public static void main(String args[]){
		MyClass mc1 = new MyClass();
		System.out.println("------");
		MyClass mc2 = new MyClass();
		System.out.println("------");
		System.out.println(mc1.cc == mc2.cc);
		System.out.println(mc1.ca == mc2.ca);
		
//		System.out.println(ClassC.a);
//		System.out.println(ClassC.a);
//		System.out.println(ClassC.b);
	}	
}

class ClassA{
	static {
		System.out.println("In ClassA Static");
	}
	public ClassA(){
		System.out.println("ClassA()");
	}
}

class ClassB{
	static {
		System.out.println("In ClassB Static");
	}
	public ClassB(){
		System.out.println("ClassB()");
	}
}

class ClassC extends ClassB{
	
	//访问类的常量，不会初始化类
	//public final static int b = 1;
	//通过 类名. 访问 类的 静态变量时，不执行类的构造方法
//	public static String a;
	
	static{
		System.out.println("In ClassC Static");
		//a = "...str"; //静态语句块只能给静态变量赋值,里面不能出现方法,同样,静态方法里面也不能出现静态语句块
	}
	public ClassC(){
		System.out.println("ClassC()");
	}
}

class MyClass {
//	static{
//		System.out.println("In Static MyClass");
//	}
	//static 
	static ClassA ca = new ClassA();//类加载过程中的准备阶段，会为类变量(static)分配内存，设初值(null)
	ClassC cc = new ClassC();//变量赋值 早于 {} 块执行
	
	{
		System.out.println("In MyClass");
	}
	
	static{
		System.out.println("In Static MyClass");
	}
	
	public MyClass(){
		System.out.println("MyClass()");
	}
}

/*	.java - .class -> 加载/验证 - 准备 - 解析 - 初始化 - 使用 
 * 
 	//类加载过程中的准备阶段，会为类变量(static)分配内存，设初值(null)
 	//解析阶段为字面应用，替换直接引用
 	//ClassA 先被执行
	In ClassA Static
	ClassA()
	
	//初始化阶段，会收集类中 赋值动作 和 静态代码块 。
	//静态块 > 变量赋值 > 代码块
	In Static MyClass
	
	In ClassB Static
	In ClassC Static
	
	//类中当执行完 静态块 ，变量赋值 ，代码块 后
	//将执行类的构造方法
	ClassB()
	ClassC()
	
	MyClass()
	
	//static变量 和 代码块，是类被加载的时候执行，且只执行这一次。
	ClassB()
	ClassC()
	MyClass()
	
	false
	true
 * 
 * */

//测试1

class Test {
    Person person = new Person("Test");//3->
    static{
        System.out.println("test static");//1
    }
     
    public Test() {
        System.out.println("test constructor");//5
    }
     
    public static void main(String[] args) {
        new MyClazz();
    }
}
 
class Person{
    static{
        System.out.println("person static");//3
    }
    public Person(String str) {
        System.out.println("person "+str);//4	person +Test
        								 //6	person +MyClass
    }
}
 
class MyClazz extends Test {
    Person person = new Person("MyClass");//6->
    static{
        System.out.println("myclass static");//2
    }
     
    public MyClazz() {
        System.out.println("myclass constructor");//7
    }
}

//测试2
class Test1 {
    
    static{
        System.out.println("test static 1");//1
    }
    public static void main(String[] args) {
         
    }
    static{
        System.out.println("test static 2");//2
    }
}
