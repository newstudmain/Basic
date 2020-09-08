package corejava;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

/*
 * Object的设计主要为了拓展，作为它的非final方法都有明确的约定，因为被设计成是要被覆盖的。
 * 
 * 	Object()
	registerNatives()
	getClass()
	hashCode()	//
	equals(Object)	//
	clone()	//
	toString()	//
	notify()
	notifyAll()
	wait(long)
	wait(long, int)
	wait()
	finalize()	//
 * 
 * -------------------
 * 
 * public boolean equals(Object obj)
 * 
 *  指示某个其他对象是否与此对象“相等”。 
	equals 方法在非空对象引用上实现相等关系： 
	
	对于任何非空引用值 
		自反性：
		x.equals(x) 都应返回 true。 
		对称性：
		当且仅当 y.equals(x) 返回 true 时，x.equals(y) 才应返回 true。 
		传递性：
		如果 x.equals(y) 返回 true，并且 y.equals(z) 返回 true，那么 x.equals(z) 应返回 true。 
		一致性：
		多次调用 x.equals(y) 始终返回 true 或始终返回 false，前提是对象上 equals 比较中所用的信息没有被修改。 
		对于任何非空引用值 x，x.equals(null) 都应返回 false。 
		
	Object 类的 equals 方法实现对象上差别可能性最大的相等关系；即，对于任何非空引用值 x 和 y，当且仅当 x 和 y 引用同一个对象时，此方法才返回 true（x == y 具有值 true）。 
	注意：当此方法被重写时，通常有必要重写 hashCode 方法，以维护 hashCode 方法的常规协定，该协定声明相等对象必须具有相等的哈希码。 
 * 
 * */

public class Equals_effect extends Object{
	
	/*
	 *  什么时候需要重写 equals 方法呢？
	 *  如果一个类包含一个逻辑相等（ logical equality）的概念，此概念有别于对象标识（object identity），而且父类还没有重写过 equals 方法。
	 *  这通常用在值类（ value classes）的情况。值类只是一个表示值的类，例如 Integer 或 String 类。
	 *  程序员使用 equals 方法比较值对象的引用，期望发现它们在逻辑上是否相等，而不是引用相同的对象。
	 *  重写 equals 方法不仅可以满足程序员的期望，它还支持重写过 equals 的实例作为 Map的键（key），或者 Set 里的元素，以满足预期和期望的行为。
	 *  
	 *  一种不需要 equals 方法重写的值类是使用实例控制（instance control）（条目 1）的类，以确保每个值至多存在一个对象。 
	 *  枚举类型（条目 34）属于这个类别。 对于这些类，逻辑相等与对象标识是一样的，所以 Object 的 equals方法作用逻辑 equals 方法。
	 * */
	
	/*
	 *  重写 equals 方法看起来很简单，但是有很多方式会导致重写出错，其结果可能是可怕的。
	 *  避免此问题的最简单方法是不覆盖 equals 方法，在这种情况下，类的每个实例只与自身相等。
	 *  如果满足以下任一下条件，则说明是正确的做法：
	 *  
		每个类的实例都是固有唯一的。 对于像 Thread 这样代表活动实体而不是值的类来说，这是正确的。
		Object 提供的 equals 实现对这些类完全是正确的行为。
		
		类不需要提供一个“逻辑相等（logical equality）”的测试功能。例如 java.util.regex.Pattern 可以重写
		equals 方法检查两个是否代表完全相同的正则表达式 Pattern 实例，但是设计者并不认为客户需要或希望使用此功能。
		在这种情况下，从 Object 继承的 equals 实现是最合适的。
		
		父类已经重写了 equals 方法，则父类行为完全适合于该子类。例如，大多数 Set 从 AbstractSet 继承了 equals 实
		现、List 从 AbstractList 继承了 equals 实现，Map 从 AbstractMap 的 Map 继承了 equals 实现。
		
		类是私有的或包级私有的，可以确定它的 equals 方法永远不会被调用。如果你非常厌恶风险，可以重写 equals
		方法，以确保不会被意外调用：
			@Override
			public boolean equals(Object o) {
			throw new AssertionError(); // Method is never called
			}
	 * */
	
    public boolean equals(Object obj) {
        return (this == obj);
    }
    
    @Test
    public void test() {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";
        
        /*
         * cis.equals(s) 返回 true。 问题是，尽管 CaseInsensitiveString 类中的 equals 方法知道正常字符串，
         * 但 String 类中的 equals 方法却忽略了不区分大小写的字符串。 因此， s.equals(cis) 返回 false，明显违反对称性。
         * */
        System.out.println(cis.equals(s));// true--1
        System.out.println(s.equals(cis));// false--1
        
        List<CaseInsensitiveString> list = new ArrayList<CaseInsensitiveString>();
        list.add(cis);
        System.out.println(list.contains(s));
    }
    
   
}

final class CaseInsensitiveString {
	private final String s;
	
	public CaseInsensitiveString(String s) {
		this.s = Objects.requireNonNull(s);
	} 

	//Broken - violates symmetry!
	@Override
	public boolean equals(Object o) {
//1		if (o instanceof CaseInsensitiveString)
//			return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
//		if (o instanceof String)
//			return s.equalsIgnoreCase((String) o);
//		return false;
		
		return o instanceof CaseInsensitiveString && 
				((CaseInsensitiveString) o).s.equalsIgnoreCase(s); 
	} 
}
