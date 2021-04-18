package corejava;

/*
 *  1、每个编译单元(文件)只能有一个public 类。
	    这么做的意思是，每个编译单元只能有一个公开的接口，而这个接口就由其 public 类来表示。
		你可以根据需要，往这个文件里面添加任意多个提供辅助功能的 package 权限的类。
		但是如果这个编译单元里面有两个或两个以上的 public 类的话，编译器就会报错。
	2. public 类的名字必须和这个编译单元的文件名完全相同，包括大小写。
		所以对Widget 类，文件名必须是Widget.java，
		不能是widget.java 或WIDGET.java。如果你不遵守，编译器又要报错了。
	3. 编译单元里面可以没有public 类
		虽然这种情况不常见，但却是可以的。这时，你就能随意为文件起名字了。
 * 	
 * */

/*
 * 这样的目的只有一个就是方便编译器加快编译速度。
 * 
 * 假设写了两个java源文件，
	 * a.java
		 * class A
		 * class B
		 * classC ....
	 * 
	 * m.java
		 * class M 
		 * class N
		 * class Y ....
 * 
 * 现在假设 class A 中的某个方法中用到了 class M
 * 那么在 class M 还没有先编译的时候，怎么要对 class A 继续编译下去
 * 最好就是先去把 class M 编译了，再回去继续编译 class A，这样不就完美了吗？
 * 但是现在问题是，如果我写了很多java源文件，有a.java，m.java，h.java等等，
 * 那么我在编译 a.java 的时候发现需要先编 class M 这个类，我去哪个java源文件中找那个“class M”呢？
 * 最蠢的方法是查找所有的java源文件知道找到为止，这样当然是一种解决方案，但导致编译速度太慢了。
 * 所以java就使用了 ''java中每个java源文件都是一个编译单元，每一个编译单元只能有一个public类，而且源文件名必须要与类名相同''
 * 这样一个规则，有了这个规则，再出现上述问题时，就能很快查找到 class M 文件，
 * 因为当 class A 需要用到 class M 的时候，说明 class M 一定是个可以导出的或者称为对外的类（或接口），
 * 此时只需要去找 m.java 文件即可，因为根据上述原则，class M 一定在 m.java 文件中，且是 public 的。
 * 在源程序中只有一个 public 类，也是为了让程序在执行时只从一个接口导入。如果能有多个public类，程序就无法识别该从哪里导入程序了
 *  
 * */
public class Onlyone_Public_Class {

	
}

class Onlyone_a{
	
}

class Onlyone_b{
	
}

class Onlyone_c{
	
}
