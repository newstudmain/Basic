package corejava;

/*
 * 准备(Preparation)
 * 准备阶段是正式为类变量分配内存并设置类变量初始值的阶段，
 * 这些变量所使用的内存都将在方法区中进行分配（逻辑分区）
	 * 1.7之前 永久代
	 * 1.8之后 类变量随Class对象一起存放在java堆中 
	 
 * 这时候进行内存分配的仅包括类变量（被static修饰的变量），而不包括实例变量，
 * 实例变量将会在对象实例化随着对象一起分配在Java堆中。
 * 其次，这里所说的初始值“通常情况”下是数据类型的零值，假设一个类变量的定义为：
 * 
 * private static int value=1;
   变量value在准备阶段过后的初始值为0而不是1，
   因为这时候尚未开始任何Java方法，而把value赋值为1的putstatic指令是程序被编译后，存放于类构造器<clinit>()方法之中，
   所以把value赋值为1的动作将在初始化阶段才会执行。
   
   下面列出了Java中所有基本数据类型的零值：
 * 
 * */
public class Class_Preparation {
	
	private static int value_int = 1; // 0
	private static long value_long = 1; // 0L
	private static float value_float = 1; // 0.0f
	private static double value_double = 1; // 0.0d
	private static byte value_byte = 1; // (byte)0
	private static char value_char = 'a'; // '\u0000'
	private static short value_short = 1; // (short)0
	private static String value_String = "string"; // null
	
	public static final int value=123;
	
}

/*
 *上面提到，在“通常情况”下初始值是零值，那相对的会有一些“特殊情况”：
 *如果类字段的字段属性表中存在ConstantValue属性，那在准备阶段变量value就会被初始化为ConstatnValue属性所制定的值，
 *假设上面类变量value的定义变为：
	public static final int value=123;
  编译时Javac将会为value生成ConstantValue属性，在准备阶段虚拟机就会根据ConstantValue的设置将value赋值为123。
 * 
 * */
