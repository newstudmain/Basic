package corejava;

/*
 * 解析(Resolution)
 * 解析阶段即是虚拟机将 常量池 内的符号引用替换为直接引用的过程
 * 不同虚拟机实现可以根据需要判断到底是在类被加载器加载的时候对常量池的符号引用进行解析（也就是初始化之前），
 * 还是等到一个符号引用被使用之前进行解析（也就是在初始化之后）。
 * 
 * 还有一个问题是：如果一个符号引用进行多次解析请求，虚拟机中除了invokedynamic指令外，
 * 虚拟机可以对第一次解析的结果进行缓存（在运行时常量池中记录引用，并把常量标识为一解析状态），这样就避免了一个符号引用的多次解析。
 * 解析动作主要针对的是类或者接口、字段、类方法、方法类型、方法句柄和调用点限定符7类符号引用。

 * 1.符号引用（Symbolic References）
	 * 符号引用以一组符号来描述所引用的目标，符号可以是任何形式的字面量，只要使用时能够无歧义的定位到目标即可。
	 * 符号引用与虚拟机的内存布局无关，引用的目标并不一定加载到内存中。
	 * 各种虚拟机实现的内存布局可能有所不同，但是它们能接受的符号引用都是一致的，
	 * 因为符号引用的字面量形式明确定义在Java虚拟机规范的Class文件格式中。
		 * 例如，在Class文件中它以CONSTANT_Class_info、CONSTANT_Fieldref_info、CONSTANT_Methodref_info等类型的常量出现。
		 * 在Java中，一个java类将会编译成一个class文件。
		 * 在编译时，java类并不知道所引用的类的实际地址，因此只能使用符号引用来代替。
		 * 比如org.simple.People类引用了org.simple.Language类，在编译时People类并不知道Language类的实际内存地址，
		 * 因此只能使用符号org.simple.Language（假设是这个，当然实际中是由类似于CONSTANT_Class_info的常量来表示的）来表示Language类的地址。
*2. 直接引用（Direct References）
	*直接引用是和虚拟机的布局相关的，同一个符号引用在不同的虚拟机实例上翻译出来的直接引用一般不会相同。
	 如果有了直接引用，那引用的目标必定已经被加载入内存中了。
		 直接引用可以是:
		（1）直接指向目标的指针（比如，指向“类型”[Class对象]、类变量、类方法的直接引用可能是指向方法区的指针）
		（2）相对偏移量（比如，指向实例变量、实例方法的直接引用都是偏移量）
		（3）一个能间接定位到目标的句柄

 * 
 * */
public class Class_Resolution {
	
	public static void main(String[] args) {
		ResolutionField.test();
	}
	
}

//类或者接口解析 (C 直接引用不是一个数组类型 - 传递给当前类的类加载器去加载这个类 C，可能会触发其他相关类的加载，如父类或实现的接口)
//当前代码所属类：ResolutionCorI
class ResolutionCorI {
	
	//Class ResolutionCorI [CONSTANT_Class_info Nan...] [未解析] [符号引用] (N)
	//    ||
	//Class ResolutionCorI [已解析] [直接引用] (C)
	//    ||
	//一个类或接口 (C) 的直接引用
	
	/*
	 * 1. 如果 C 直接引用不是一个数组类型
			 那么虚拟机将会把该符号代表的全限定名称传递给 ResolutionCorI 类加载器去加载这个类 C。
			 这个过程由于涉及验证过程，元数据，字节码等。所以可能会触发其他相关类的加载，如父类或实现的接口。 

	   2. 如果 C 直接引用是一个数组类型，并且数组的元素类型为对象。
		     我们知道符号引用是存在方法区的常量池中的，该符号引用的描述符会类似”[java/lang/Integer”的形式，
		   	 将会按照 规则1 加载数组元素类型，如果 N 描述符如前面假设的形式，需要加载的元素类型就是java.lang.Integer ,
		   	 接着由虚拟机将会生成一个代表此数组维度和元素的对象直接引用。

       3. 如果上面的步骤都没有出现异常
       		 那么该 C 已经在虚拟机中成为一个有效的类和接口，但是在解析完成之前需要对符号引用进行验证，
       		 主要是确认 D当前调用这个符号引用的类 是否具有对 C 访问权限，
       		 如果没有访问权限将抛出java.lang.IllegalAccess异常
       		 
       		 JDK9 引入模块化后，public 类型也不再意味着程序任何位置都有它的访问权限，需检查模块间的访问权限
       		 D 拥有 C 的访问权限：
       		 	1. C 是   public，与访问类 D 同模块
       		 	2. C 是   public，与访问类 D 不同模块，但被访问 C 的模块允许访问类 D 访问
       		 	3. C 不是 public，与访问类 D 同包
       		 
	 * 
	 * */
	
}


//字段解析 (C 本身 - 如果 C 中实现了接口，接口和它的父接口 - 如果 C 不是 java.lang.Object 的话，父类) - 权限验证
/*
 * 字段符号引用 [未解析] (字段表内 class_index 项中 CONSTANT_Class_info 的符号引用)
	  || [进行解析]
	解析字段所属的类或接口的符号引用
	  || [解析后]
	解析为字段所属的类或接口 [C]
	  || [解析完成] 
	对 C 进行后续字段的搜索：
			1.  如果 C 本身就包含了简单名称和字段描述符都与目标相匹配的字段，则返回这个字段的直接引用，查找结束
			2.  否则，如果 C 中实现了接口，将会按照继承关系从下往上递归搜索各个接口和它的父接口，
				如果在接口中包含了简单名称和字段描述符都与目标相匹配的字段，那么久直接返回这个字段的直接引用，查找结束
			3.  否则，如果 C 不是 java.lang.Object 的话，将会按照继承关系从下往上递归搜索其父类，
				如果在父类中包含了简单名称和字段描述符都相匹配的字段，那么直接返回这个字段的直接引用，查找结束
			4.  否则，查找失败，抛出 java.lang.NoSuchFieldError 异常
	如果最终返回了这个字段的直接引用，就进行权限验证，如果发现不具备对字段的访问权限，将抛出java.lang.IllegalAccessError异常

 * 
 * =========================================================================
 * 简单名称
 * 没有类型和参数修饰的方法或字段名称，比如一个类的test()方法，它的简单名称是test
 * 
 * 描述符
 * 针对Java字节码。描述符的作用是用来描述字段的数据类型、方法的参数列表（包括数量、类型以及顺序）和返回值。
 * 在JVM规范中，定义了两种类型的描述符，Field Descriptors 和 Method Descriptors
 * 
	 * Field Descriptors
	 * 字段描述符包含BaseType、ObjectType、ArrayType三部分，
	 * 对于基本数据类型(byte、char、double、float、int、long、short、boolean)都用一个大写字母来表示，
	 * 而对象用字符L加对象的全限定名和；来表示
	 * 
		FieldType term 	Type 		Interpretation
		B 				byte 		signed byte
		C 				char 		Unicode character code point in the Basic Multilingual Plane, encoded with UTF-16
		D 				double 		double-precision floating-point value
		F 				float 		single-precision floating-point value
		I 				int 		integer
		J 				long 		long integer
		LClassName; 	reference 	an instance of class ClassName
		S 				short 		signed short
		Z 				boolean 	true or false
		[ 				reference 	one array dimension
 	 *
 	 *对于数组类型，每一个维度使用一个前置的 [ 来描述，
 	 *如一个定义为 java.lang.String[][] 类型的二维数组，将被记录为 [[Ljava/lang/String;
 	 *一个 double 型数组 double[][][] 将被记录为 [[[D
 * 
 * ===========================================================================
 * 
 * 如果有一个同名字段同时出现在某个类的接口和父类当中，或者同时在自己或父类的多个接口中出现，
 * 按解析规则任是可以确定唯一的访问字段，但 Javac 编译器就可能直接拒绝其编译为class文件。
 * 如果注释了Sub类中的"public static int A = 4;",接口与父类同时存在字段A，
 * 那么编译器将提示"The field Sub.A is ambiguous",并拒绝编译这段代码。
 * */
class ResolutionField {
	
	interface Interface0 {
		int a = 0;
	}
	
	interface Interface1 {
		int a = 1;
	}
	
	interface Interface2 {
		int a = 2;
	}
	
	static class Parent implements Interface1 {
		public static int a = 3; 
	}
	
	static class Sub extends Parent implements Interface2{
		public static int a = 4; 
		//若注释 public static int a = 4;  --> The field Sub.a is ambiguous 
	}
	
	public static void test() {
		System.out.println(Sub.a); // 4
	}

}

//类方法解析 (类方法和接口方法的符号引用的常量类型是分开，非类或非接口报错 - 类 C 中查找 - 类 C 的父类中递归查找 - 类 C 的接口列表以及它的父接口中递归查找)
/*
 * 方法符号引用 [未解析] (字段表内 class_index 项中 CONSTANT_Class_info 的符号引用)
	  || [进行解析]
	解析方法所属的类或接口的符号引用
	  || [解析后]
	解析为方法所属的类或接口 [C]
	  || [解析完成] 
	对 C 进行后续字段的搜索：
 * 
 *  1. 类方法和接口方法的符号引用的常量类型是分开的，
 *     所以如果在类方法表中发现class_index（类中方法的符号引用）的索引的 C 是一个接口，
 *     那么会抛出java.lang.IncompatibleClassChangeError的异常

	2. 如果通过第一步，那么在类 C 中查找是否有简单名称和描述符都与目标相匹配的，如果有的话就返回这个方法的直接引用，查找结束

	3. 否则，在类 C 的父类中递归查找是否具有简单名称和描述符都与目标相匹配的方法，如果有，则直接返回这个方法的直接引用，查找结束

	4. 否则，在类 C 的接口列表以及它的父接口中递归查找是否具有简单名称和描述符都与目标相匹配的方法，如果存在匹配的方法，
	   就说明类 C 是一个抽象类，查找结束，返回java.lang.AbstractMethodError异常

	5. 否则，查找失败，抛出java.lang.NoSuchMethodError异常

 * 
 * */

//接口方法解析 (类方法和接口方法的符号引用的常量类型是分开，非类或非接口报错 - 接口 C 中查找 - 接口 C 的父接口中查找，直到Object类（接口方法查找的范围也会包括 Object 类中的方法）为止)
/*
 *  方法符号引用 [未解析] (字段表内 class_index 项中 CONSTANT_Class_info 的符号引用)
	  || [进行解析]
	解析方法所属的类或接口的符号引用
	  || [解析后]
	解析为方法所属的类或接口 [C]
	  || [解析完成] 
	对 C 进行后续字段的搜索：
 *	
 * 1. 与类的方法解析相反，如果在类方法表中发现class_index（类中方法的符号引用）的索引的 C 是一个类，
 * 	  那么会抛出java.lang.IncompatibleClassChangeError的异常
 * 
 * 2. 否则，在接口 C 中查找是否具有简单名称和描述符都与目标相匹配的方法，如果有的话就直接返回这个方法的直接引用。

 * 3. 否则，在接口 C 的父接口中查找，直到Object类（接口方法查找的范围也会包括 Object 类中的方法）为止，如果找到则直接返回这个方法的直接引用

 * 4. 否则，查找失败，抛出java.lang.NoSuchMethodError异常
 * 
 * 
 * */

interface f1{
	static int a = 1;
	void forS();
}

interface f2{
	static int a = 1;
	void forS();
}

interface f3{
	static int a = 1;
	void forS();
}

class son implements f1,f2,f3{
	
	static int a = 1; // 若注释，test() 中的 a 将报错
	
	void test() {
		System.out.println(a); // The field a is ambiguous
	}

//若注释此方法，The type son must implement the inherited abstract method f3.forS()
//虚拟机约定实现最后一个接口的方法
	@Override
	public void forS() {
		// TODO Auto-generated method stub
		
	}
	
}












