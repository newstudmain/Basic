package corejava;

/*
 * 整数型
		byte 	8 位		Byte		0		-128...127
		short 	16 位		Short		0		-32768...32767
		int 	32 位		Integer		0		-2147483648...2147483647
		long 	64 位		Long		0L		-9223372036854775808...922337203685477580
		
		//Float 和 Double 的最小值和最大值都是以科学记数法的形式输出的，
		//结尾的 "E+数字" 表示 E 之前的数字要乘以 10 的多少倍。
		//比如3.14E3就是3.14×1000=3140，3.14E-3就是3.14/1000=0.00314。
		 
	浮点型
		float 	32 位，直接赋值时必须在数字后加上 f 或 F
		double 	64 位，赋值时一般在数字后加 d 或 D
		
	字符型
		char 	16 位，存储 Unicode 码，用单引号赋值
		
	布尔型
		boolean - 只有 true 和 false 两个取值
 * 
 * */

/*基本类型 (void boolean byte char short int float long double)
 *  有一系列类需特别对待,可将它们想象成基本、主（ Primitive）类型，进行程序设计时要频繁用到它们。
 
	之所以要特别对待，是由于用 new 创建对象（特别是小的、简单的变量）并不是非常有效，因为 new 将对象置于“堆”里。
	对于这些类型， Java 采纳了与 C 和 C++相同的方法。也就是说，不是用new创建变量，而是创建一个并非句柄的“自动”变量。
	这个变量容纳了具体的值，并置于堆栈中，能够更高效地存取。
 * */



public class Data_Types {

	public static void main(String[] args) {
		
	    // byte  
        System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE);  
        System.out.println("包装类：java.lang.Byte");  
        System.out.println("最小值：Byte.MIN_VALUE=" + Byte.MIN_VALUE);  
        System.out.println("最大值：Byte.MAX_VALUE=" + Byte.MAX_VALUE);  
        System.out.println();  
  
        // short  
        System.out.println("基本类型：short 二进制位数：" + Short.SIZE);  
        System.out.println("包装类：java.lang.Short");  
        System.out.println("最小值：Short.MIN_VALUE=" + Short.MIN_VALUE);  
        System.out.println("最大值：Short.MAX_VALUE=" + Short.MAX_VALUE);  
        System.out.println();  
  
        // int  
        System.out.println("基本类型：int 二进制位数：" + Integer.SIZE);  
        System.out.println("包装类：java.lang.Integer");  
        System.out.println("最小值：Integer.MIN_VALUE=" + Integer.MIN_VALUE);  
        System.out.println("最大值：Integer.MAX_VALUE=" + Integer.MAX_VALUE);  
        System.out.println();  
  
        // long  
        System.out.println("基本类型：long 二进制位数：" + Long.SIZE);  
        System.out.println("包装类：java.lang.Long");  
        System.out.println("最小值：Long.MIN_VALUE=" + Long.MIN_VALUE);  
        System.out.println("最大值：Long.MAX_VALUE=" + Long.MAX_VALUE);  
        System.out.println();  
  
        // float  
        System.out.println("基本类型：float 二进制位数：" + Float.SIZE);  
        System.out.println("包装类：java.lang.Float");  
        System.out.println("最小值：Float.MIN_VALUE=" + Float.MIN_VALUE);  
        System.out.println("最大值：Float.MAX_VALUE=" + Float.MAX_VALUE);  
        System.out.println();  
  
        // double  
        System.out.println("基本类型：double 二进制位数：" + Double.SIZE);  
        System.out.println("包装类：java.lang.Double");  
        System.out.println("最小值：Double.MIN_VALUE=" + Double.MIN_VALUE);  
        System.out.println("最大值：Double.MAX_VALUE=" + Double.MAX_VALUE);  
        System.out.println();  
  
        // char  
        System.out.println("基本类型：char 二进制位数：" + Character.SIZE);  
        System.out.println("包装类：java.lang.Character");  
        // 以数值形式而不是字符形式将Character.MIN_VALUE输出到控制台  
        System.out.println("最小值：Character.MIN_VALUE="  
                + (int) Character.MIN_VALUE);  
        // 以数值形式而不是字符形式将Character.MAX_VALUE输出到控制台  
        System.out.println("最大值：Character.MAX_VALUE="  
                + (int) Character.MAX_VALUE);  
	}
}
