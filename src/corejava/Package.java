/*
 *  为了提供一种特殊的机制，以便管理“命名空间”（ Name Space）。我们所有类成员的名字相互间都会隔离起来。
 *  位于类 A 内的一个方法 f() 不会与位于类 B 内的、拥有相同“签名”（自变量列表）的 f()发生冲突。
 	 *  
	 *  //所有类成员的名字相互间都会隔离起来
		//class PackageA
		class PackageA{
			public void pack() {
				
			}
		}
		//class PackageB
		class PackageB{
			public void pack() {
				
			}
		}
	 *  
	 *  
 *  但类名会不会冲突呢？
 *  假设创建一个stack 类，将它安装到已有一个 stack类（由其他人编写）的机器上，这时会出现什么情况呢？
 *  对于因特网中的 Java 应用，这种情况会在用户毫不知晓的时候发生，因为类会在运行一个 Java 程序的时候自动下载。
 *  
	正是由于存在名字潜在的冲突，所以特别有必要对 Java 中的命名空间进行完整的控制。
	且需要创建一个完全独一无二的名字，无论因特网存在什么样的限制。
	若计划创建一个“对因特网友好”或者说“适合在因特网使用”的程序，必须考虑如何防止类名的重复。
 * 
 * */


// 该文件的目录结构为：...corejava\Package.java
// G:\workspace_2020\Basic\src\corejava\Package.java
package corejava; 

/*
 * 指出这个编译单元属于名为 corejava 的一个库的一部分。
 * 或者换句话说，它表明这个编译单元内的 public 类名位于 corejava 这个名字的下面。
 * 如果其他人想使用这个名字，要么指出完整的名字，要么与 corejava 联合使用 import 关键字（使用前面给出的选项）。
 * 注意根据 Java 包（封装）的约定，名字内的所有字母都应小写，甚至那些中间单词亦要如此。
 * */
				

/*
 * 使用import就可以在一个package中导入另一个package中的类，不过import和C语言和C++中的#include是不同的，
 * import并不会在当前java文件中嵌入另一个package中的类的代码，只是告诉java文件，不属于该包的类能够到哪里去寻找而已
 * 
 * java文件在编译时如何知道这个package在哪？
 * 这里要提到一个重要的变量：classpath。
 * classpath是java在编译程序时查找类文件的路径，java编译器会在classpath中包含有的路径中查找java的类文件.
 * 
 * 举个例子，比如说classpath的值（Windows下）是	.（当前目录）;C:/
 * 那么编译器在查找类的时候，就只会在当前目录和C:/中查找。
 * 所以如果 package2 这个目录与 package1 在同一目录层次下，那么 package2 就能直接被查找到了，查找也就结束了；
 * 如果不在的话，比如说 package1 的目录结构是 C:/document/java/project/p1，package2 的目录结构是C:/document/java/test/p2，
 * 那么如果C:/document/java/test不在classpath中，java解释器是找不到 package2这个package的位置的，那么就会提示错误，
 * 因此需要将C:/document/java/test加入到classpath中。
 * 
 * 	C:\Desktop>java -cp .\test2 test
	test2
	
	C:\Desktop>java -cp .\test2;.\test1 test
	test2
	
	C:Desktop>java -cp .\test1;.\test2 test
	test1
	
	C:Desktop>java testp.test
	test
 * */
import org.junit.jupiter.api.Test;

//-The type corejava.temp.test.PackageC is not visible
//-public class PackageC  ==> change to public
//import	corejava.temp.test.PackageC;

//-The import corejava.temp.test.Package conflicts with a type defined in the same file
//import corejava.temp.test.Package;

/*
 * 若导入了两个库，而且它们包括相同的名字，这会造成潜在的冲突。
 * 然而，只要冲突并不真的发生，那么就不会产生任何问题,这当然是最理想的情况，
 * 因为否则的话，就需要进行大量编程工作，防范那些可能可能永远也不会发生的冲突。
 * 
 * 如现在试着生成一个 Package，就肯定会发生冲突。如下所示：
 * Package p = new Package();
 * 它引用的到底是哪个 Package 类呢？
 * 
 * 编译器对这个问题没有答案，读者也不可能知道。所以编译器会报告一个错误，强迫我们进行明确的说明。
 * 例如，假设我想使用指定的 Package，那么必须象下面这样编程：
 * corejava.temp.test.Package p = new corejava.temp.test.Package();
 * 
 * */

import corejava.temp.test.PackageD; //java解释器会将package中的.解释为目录分隔符/，也就是说该文件的目录结构为：...corejava/temp/test/PackageD


public class Package {

	public void pack() {
		System.out.println("this is pack() of Package of the corejava");
		
	}
	
	@Test
	public void test() {
		new Package().pack();
		new corejava.temp.test.Package().pack();
		new PackageD().pack();
	}
	
}

/*
 * 包的停用
		大家应注意这样一个问题：每次创建一个包后，都在为包取名时间接地指定了一个目录结构。这个包必须存
		在（驻留）于由它的名字规定的目录内。而且这个目录必须能从CLASSPATH 开始搜索并发现。最开始的时
		候， package 关键字的运用可能会令人迷惑，因为除非坚持遵守根据目录路径指定包名的规则，否则就会在
		运行期获得大量莫名其妙的消息，指出找不到一个特定的类—— 即使那个类明明就在相同的目录中。若得到
		象这样的一条消息，请试着将 package 语句作为注释标记出去。如果这样做行得通，就可知道问题到底出在
		哪儿。
 * */

//所有类成员的名字相互间都会隔离起来
//class PackageA
class PackageA{
	public void pack() {
		
	}
}
//class PackageB
class PackageB{
	public void pack() {
		
	}
}