package corejava;

public class Static {

	private String str1 = "str1";
	
	/*
	 * 静态变量被所有的对象所共享，在内存中只有一个副本，它当且仅当在类初次加载时会被初始化。
	 * 而非静态变量是对象所拥有的，在创建对象的时候被初始化，存在多个副本，各个对象拥有的副本互不影响。
	 * */
	private static String str2 = "str2";
	
	public void print1() {
		System.out.println(str1);
		System.out.println(str2);
	}
	public static void print2() {
		//System.out.println(str1);//static方法内部不能调用非静态方法,变量
		//print1();
		System.out.println(str2);
	}
	/*
	 * static方法就是没有this的方法。在static方法内部不能调用非静态方法，反过来是可以的。
	 * 而且可以在没有创建任何对象的前提下，仅仅通过类本身来调用static方法。
	 * 非静态成员方法，它访问静态成员方法/变量显然是毫无限制的。
	 * */
	static {

	}
	
	static int value = 33;
    private void printValue(){
    	//static int sta = 1;//在C/C++中static是可以作用域局部变量的，但是在Java中切记：static是不允许用来修饰局部变量。
        int value = 3;
        System.out.println(this.value);//33
    }
    /*
     * 这里面主要考察队this和static的理解。this代表什么？this代表当前对象，
     * 那么通过new Static()来调用printValue的话，当前对象就是通过new Static()生成的对象。
     * 而 static 变量是被对象所享有的，因此在 printValue中 的 this.value 的值毫无疑问是 33。
     * 在printValue方法内部的 value 是局部变量，根本不可能与 this 关联，所以输出结果是33。
     * 
     * 静态成员变量虽然独立于对象，但是不代表不可以通过对象去访问，所有的静态方法和静态变量都可以通过对象访问（只要访问权限足够）。
     * */
    
    public static void main(String[] args) {
    	new Static().printValue();
	}
}
