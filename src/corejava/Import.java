package corejava;

/*
 * package 名称就像是我们的姓，而 class 名称就像是我们的名字 。
 * package 和 package 的附属关系用 "." 来连接，这就像是复姓。
 * 比如说 java.lang.String 就是复姓  java.lang，名字為  String 的类别；
 * java.io.InputStream 则是复姓 java.io，名字為 InputStream的类别。
 * Java 会使用 package 这种机制的原因也非常明显，如果类别不使用 package 名称，
 * 那在用到相同名称的不同类时， 就会产生极大的困扰。所以package这种方式让极大降低了类之间的命名冲突。
 * Java 的 package 名称我们可以自己取，如果依照 Sun 的规范来取套件名称，
 * 那理论上不同人所取的套件名称不会相同 ( 需要的话请参阅"命名惯例" 的相关文章 )，也就不会发生名称冲突的情况。
 * */

//单类型导入(single-type-import)
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

//按需类型导入(type-import-on-demand)
import java.util.*;

/*
 * import就是在java文件开头的地方，先说明会用到那些类别。
 * 接着我们就能在代码中只用类名指定某个类，也就是只称呼名字，不称呼他的姓。
 * 
 * java.lang 包里面的类实在是太常用到了，几乎没有类不用它的， 
 * 所以不管你有没有写  import java.lang，编译器都会自动帮你补上，
 * 也就是说编译器只要看到没有姓的类别，它就会自动去lang包里面查找。
 * 所以我们就不用特别去 import java.lang 了。
 * */

/*
 * java以这样两种方式导入包中的任何一个public的类和接口(只有public类和接口才能被导入)
 * 上面说到导入声明仅导入声明目录下面的类而不导入子包，这也是为什么称它们为类型导入声明的原因。
 * 
 * 导入的类或接口的简名(simple name)具有编译单元作用域。
 * 这表示该类型简名可以在导入语句所在的编译单元的任何地方使用.
 * 这并不意味着你可以使用该类型所有成员的简名,而只能使用类型自身的简名。
 * 例如: java.lang包中的public类都是自动导入的,包括Math和System类.
 * 但是,你不能使用它们的成员的简名PI()和gc(),而必须使用Math.PI()和System.gc().
 * 你不需要键入的是java.lang.Math.PI()和java.lang.System.gc()。
 * 
 * 程序员有时会导入当前包或java.lang包，这是不需要的，
 * 因为当前包的成员本身就在作用域内,而java.lang包是自动导入的。
 * 
 * java编译器会忽略这些冗余导入声明(redundant import declarations)。即使像这样
 * import java.util.ArrayList;
 * import java.util.*;
 * 多次导入,也可编译通过。编译器会将冗余导入声明忽略
 * */

import static corejava.staticFieldsClass.staticField;
import static corejava.staticFieldsClass.staticFunction;

/*
 * static import静态导入
 * 
 * 在Java程序中，是不允许定义独立的函数和常量的。即什么属性或者方法的使用必须依附于什么东西，
 * 例如使用类或接口作为挂靠单位才行（在类里可以挂靠各种成员，而接口里则只能挂靠常量）。
 * 
 * 如果想要直接在程序里面不写出其他类或接口的成员的挂靠单元，有一种变通的做法 :
 * 将所有的常量都定义到一个接口里面，然后让需要这些常量的类实现这个接口
 * （这样的接口有一个专门的名称，叫(“Constant Interface”)。
 * 这个方法可以工作。但是，因为这样一来，就可以从“一个类实现了哪个接口”推断出“这个类需要使用哪些常量”，有“会暴露实现细节”的问题。
 * 
 * 于是J2SE 1.5里引入了“Static Import”机制，借助这一机制，可以用略掉所在的类或接口名的方式，来使用静态成员。
 * static import和import其中一个不一致的地方就是static import导入的是静态成员，而import导入的是类或接口类型。
 * */



public class Import {

	public static void main(String[] args) {
//		java.io.InputStream is = java.lang.System.in;
//		java.io.InputStreamReader isr= new java.io.InputStreamReader(is);
//		java.io.BufferedReader br = new java.io.BufferedReader(isr);
		
		InputStream is = System.in;
		InputStreamReader isr= new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		ArrayList<Object> arrayList = new ArrayList<>();
		
        //这里直接写静态成员而不需要通过类名调用
        System.out.println(staticField);
        staticFunction();
		
		
	}
}


class staticFieldsClass {
    static int staticNoPublicField = 0; 
    public static int staticField = 1;
    public static void staticFunction(){}
}

/*
 * 这里有几个问题需要弄清楚：
 * Static Import无权改变无法使用本来就不能使用的静态成员的约束，
 * 上面例子的StaticTest和staticFieldsClass不是在同一个包下，
 * 所以StaticTest只能访问到staticFieldsClass中public的变量。使用了Static Import也同样如此。
 * 
 * 导入的静态成员和本地的静态成员名字相同起了冲突，这种情况下的处理规则，是本地优先。
 * 
 * 不同的类（接口）可以包括名称相同的静态成员。
 * 例如在进行Static Import的时候，出现了“两个导入语句导入同名的静态成员”的情况。在这种时候，J2SE 1.5会这样来加以处理：
 * 如果两个语句都是精确导入的形式，或者都是按需导入的形式，那么会造成编译错误。
 * 如果一个语句采用精确导入的形式，一个采用按需导入的形式，那么采用精确导入的形式的一个有效。
 * */


