package corejava;

/*
 * instanceof 运算符
 * 		在运行时指出对象是否是特定类的一个实例,返回一个布尔值。
 * 			result[bool] = object[obj] /instanceof/ class[cla]
 * 
 *  instanceof是Java的一个二元操作符，和==，>，<是同一类。
 *  由于它是由字母组成的，所以也是Java的保留关键字。
 *  它的作用是测试它左边的对象是否是它右边的类的实例，返回boolean类型的数据。

	instanceof运算符用法
	运算符是双目运算符,左面的操作元是一个对象实例,右面是一个类.
	当左面的对象是右面的类创建的对象时,该运算符运算的结果是true,否则是false
	
	说明:
	　　1.一个类的实例包括本身的实例,以及所有直接或间接子类的实例
	　　2.instanceof左边操作元显式声明的类型与右边操作元必须是同种类或有继承关系,
	　　　　即位于继承树的同一个分支上,否则会编译出错  
 * 		
 * */
public class Instanceof {

	public static void main(String[] args) {
		iB b = new iB();
		iC c = new iC();
		iA a = new iB();
		
		System.out.println(c.getClass());// class corejava.iC
		System.out.println(b.getClass());// class corejava.iB
		System.out.println(a.getClass());// class corejava.iB
		
		System.out.println(iC.class);// class corejava.iC
		System.out.println(iB.class);// class corejava.iB
		System.out.println(iA.class);// interface corejava.iA
		
		//没有考虑继承，是否是此类的确切类型
		System.out.println(c.getClass() == iC.class);// true
//		System.out.println(c.getClass() == iB.class);
		System.out.println(c.getClass() == iA.class);// false
		
		//没有考虑继承，是否是此类的确切类型
		System.out.println(c.getClass().equals(iC.class));// true
		System.out.println(c.getClass().equals(iB.class));// false
		System.out.println(c.getClass().equals(iA.class));// false
		
		//instanceof 保持了类型的概念，此类以及其派生类
		System.out.println(c instanceof iC);// true
		System.out.println(c instanceof iB);// true
		System.out.println(c instanceof iA);// true
		
		//isInstance 保持了类型的概念，此类以及其派生类
		System.out.println(iB.class.isInstance(c));// true
		System.out.println(iB.class.isInstance(b));// true
		System.out.println(iB.class.isInstance(a));// true
		
		//
		System.out.println(iC.class.isInstance(c));// true
		System.out.println(iC.class.isInstance(b));// false
		System.out.println(iC.class.isInstance(a));// false
		

	}
}

interface iA{}

class iB implements iA{}

class iC extends iB {}
