package corejava;

class A {
    public String show(D obj){  
           return ("A and D");  
    }   
    public String show(A obj){  
           return ("A and A");  
    } 
}

class B extends A{	
	public String show(B obj){  
		return ("B and B");  
	}  
	public String show(A obj){  
		return ("B and A");  
	} 
}

class C extends B {}

class D extends B{}

public class Polymorphism {
	public static void main(String[] args) {
		A a1 = new A();  
	    A a2 = new B();  
	    B b = new B();  
	    C c = new C();   
	    D d = new D();
	    System.out.println(a1.show(b)); //"A and A"
	    System.out.println(a1.show(c));//"A and A"
	    System.out.println(a1.show(d)); //"A and D"
	    System.out.println(a2.show(b)); //"B and B"---->?4 "B and A"
	    System.out.println(a2.show(c)); //"B and B"---->?5 "B and A"
	    System.out.println(a2.show(d));	//"A and D"
	    System.out.println(b.show(b)); //"B and B"
	    System.out.println(b.show(c));//"B and B"	
	    System.out.println(b.show(d));//"A and D"
	}
}

/**

原理：
		this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)

				 this为当前申明的引用变量，即父类引用。
				【注意】当在父类中找到类型一致的方法时，还要看子类有没有重写此方法。
				 如果new的是子类对象，那么将执行子类中重写的方法，父类方法将被覆盖。

		比如④，a2.show(b)，a2是一个引用变量，类型为A，则this为a2，b是B的一个实例。

		        A a2 = new B();  
		        B b = new B();  

			1. 它到类A里面找show(B obj)方法，没有找到，
			2. 到A的super(超类)找，而A没有超类，
			3. 转到第三优先级this.show((super)O)，this仍然是a2，
			4. 这里O为B，(super)O即(super)B即A，因此它到类A里面找show(A obj)的方法，
			5. 类A有这个方法，但是由于a2引用的是类B的一个对象，B覆盖了A的show(A obj)方法，
			6. 最终锁定到类B的show(A obj)，输出为"B and A"。

				class A {
				        public String show(D obj){  
				               return ("A and D");  
				        }   
				        public String show(A obj){  
				               return ("A and A");  
				        } 
				}
				class B extends A{	
					public String show(B obj){  
						return ("B and B");  
					}  
					public String show(A obj){  
						return ("B and A");  
					} 
				}

*/
