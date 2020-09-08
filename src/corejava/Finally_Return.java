package corejava;
import java.util.HashMap;
import java.util.Map;

public class Finally_Return {
	public static void main(String[] args) { 
		//System.out.println("return value of FinallyDraft(): " + FinallyDraft()); 
		//FinTry(); 
		System.out.println(getMap2().get("KEY")); 
		
	}
	
	/*
	 *  the previous statement of try block 
		Exception in thread "main" java.lang.ArithmeticException: / by zero 
		at com.bj.charlie.Test.test(Test.java:15) 
		at com.bj.charlie.Test.main(Test.java:6)
		
		return value of test(): 0
		
		1.  在以上两种情况下，finally 语句块都没有执行，说明什么问题呢？
			只有与 finally 相对应的 try 语句块得到执行的情况下，finally 语句块才会执行。
			以上两种情况，都是在 try 语句块之前返回（return）或者抛出异常，所以 try 对应的 finally 语句块没有执行。
			
		2. 即使与 finally 相对应的 try 语句块得到执行的情况下，finally 语句块一定会执行吗？
						try { 
							System.out.println("try block"); 
							System.exit(0); //try block --------
							return i; 
						}finally { 
							System.out.println("finally block"); 
						 } 
				finally 语句块还是没有执行，为什么呢？
					- try 语句块中执行了 System.exit (0) 语句，终止了 Java 虚拟机的运行。
					- 当一个线程在执行 try 语句块或者 catch 语句块时被打断（interrupted）或者被终止（killed），与其相对应的 finally 语句块可能不会执行。
					- 线程运行 try 语句块或者 catch 语句块时，突然死机或者断电，finally 语句块肯定不会执行了。
					
		 3. finally 语句块是怎样执行的? try、catch、finally 的执行顺序是什么样的？
		 	不管 try 语句块正常结束还是异常结束，finally 语句块是保证要执行的。
		 	如果 try 语句块正常结束，那么在 try 语句块中的语句都执行完之后，再执行 finally 语句块。
		 	
		 	- 如果 try 中有控制转移语句（return、break、continue）呢？
		 		* 如果 try 语句块异常结束，应该先去相应的 catch 块做异常处理，然后执行 finally 语句块。
		 		
		 		如果 catch 语句块中包含控制转移语句呢？
		 		
		 	
	 * */
	public static int FinallyDraft(){
		int i = 1; 
//      if(i == 1) 
//          return 0; 
		System.out.println("the previous statement of try block"); 
		i = i / 0; 
		try { 
			System.out.println("try block"); 
			return i; 
		}finally { 
			System.out.println("finally block"); 
		} 
	}
	
	/*
	 *  try block 
		finally block
		
		 finally 语句块在 try 语句块中的 return 语句之前执行
	 * */
	public static void FinTry(){
		try {  
			System.out.println("try block");  
			return ;  
			} finally {  
			System.out.println("finally block");  
			   }  
	} 
	
	/*
	 *  try block
		exception block
		finally block
		reture value of FinTryCatch(): 2
		
		finally 语句块是在 try 或者 catch 中的 return 语句之前执行的。
		更加一般的说法是，finally 语句块应该是在控制转移语句之前执行，
		控制转移语句除了 return 外，还有 break 和 continue。另外，throw 语句也属于控制转移语句。
		虽然return、throw、break 和 continue 都是控制转移语句，但是它们之间是有区别的。
		其中 return 和 throw 把程序控制权转交给它们的调用者（invoker），而 break 和 continue 的控制权是在当前方法内转移。
		
		public static int getValue1() { 
	       try { 
	                return 0; 
	       } finally { 
	                return 1; 
	       } 
	    }//return value of getValue(): 1
	    
	    -----
	    
	    public static int getValue2() { 
	       int i = 1; 													入操作数栈(1)			 	    更新本地变量表 #0 -- 1
	       try { 													
	                return i; 											出操作数栈(1) --->0栈为空				#0 -- 1//<执行i++后>-->#0 -- 2
	       } finally { 																			   更新本地变量表	#1 -- 1
	                i++; 																		  		  [ #0 -- 2 ]//i++ 更新变量表编号#0的值 1+1=2						
	                													入操作数栈(1) #1 -- 1
	       } 
	    } //return value of getValue(): 1								return:出操作数栈-->(1)#1 -- 1 //变量表编号#1的值，值为1
	    
	    
	    
	    	当有数进入操作数栈时，就会更新本地变量表
	    	
	    		| 1 | 			|  |			| 1 |(return i)		
	    	
	    	更新本地变量表 #0 -- 1				本地变量表 #0 -- 1
	    											#1 -- 1
	    
	    
	    1.  那为什么getValue2() 的返回值不是 2，而是 1 呢？
	    	按照清单 5 的分析逻辑，finally 中的 i++；语句应该在 try 中的 return i 之前执行啊？
	  		i 的初始值为 1，那么执行 i++ 之后为 2，再执行 return i 那不就应该是 2 吗？怎么变成 1 了呢？
	  
	  	实际上，Java 虚拟机会把 finally 语句块作为子程序(subroutine) 直接插入到 try 语句块或者 catch 语句块的控制转移语句之前。
	  	但是，还有另外一个不可忽视的因素，那就是在执行 subroutine（也就是 finally 语句块）之前，try 或者 catch 语句块会保留其返回值到本地变量表（Local Variable Table）中。
	  	待 subroutine 执行完毕之后，再恢复保留的返回值到操作数栈中，
	  	然后通过 return 或者 throw 语句将其返回给该方法的调用者（invoker）。
	  	
	  	try{} catch{} finally{}..[subroutine]
	  	
	  	<--finally{}..[subroutine]---执行完毕--->恢复保留的返回值到操作数栈中 					
	  	[															
	  		try{}	>>> 返回值 >>> 本地变量表(Local Variable Table)	#0 -- 1	
	  		catch{} >>> 返回值 >>> 本地变量表(Local Variable Table)	#1 --	
	  	]			...>>入操作数栈
	  	
		<请注意>
			1. 前文中我们曾经提到过 return、throw 和 break、continue 的区别，对于这条规则（保留返回值），只适用于 return 和 throw 语句
			       不适用于 break 和 continue 语句，因为它们根本就没有返回值。
			2. finally里加上return过后，finally外面的 return 就变成不可到达语句了，也就是永远不能被执行到，所以需要注释掉否则编译器报错。
		--------------------
		
		2.  再让我们来看看异常执行的情况。是不是有人会问，你的清单 getValue2() 中都没有 catch 语句，哪来的异常处理呢？
				其实，即使没有 catch 语句，Java 编译器编译出的字节码中还是有默认的异常处理的，
				别忘了，除了需要捕获的异常，还可能有不需捕获的异常（如：RunTimeException 和 Error）。
				
				入操作数栈(1)			 	    更新本地变量表 #0 -- 1
				出操作数栈(1) --->0栈为空				#0 -- 1//<执行i++后>-->#0 -- 2
										   更新本地变量表	#1 -- 1
									...				#2 ex
									...	   
				出现异常时，将会产生一个 exception 对象，并且把它压入当前操作数栈的栈顶。
				接下来是 astore_2 这条指令，它负责把 exception 对象保存到本地变量表中#2 的位置，然后执行 finally 语句块，
				待 finally 语句块执行完毕后，再由 aload_2 这条指令把预先存储的 #2 exception 对象恢复到操作数栈中，
				最后由 athrow 指令将其返回给该方法的调用者（main）。
	 * 
	 * */
	public static int FinTryCatch(){ 
		int i = 1; 
		        
		try {  
		System.out.println("try block");  
		            i = 1 / 0; //1-->
		return 1;  
		}catch (Exception e){ 
		System.out.println("exception block"); //2-->
		return 2; //4-->
		}finally {  
		System.out.println("finally block"); //3-->
		  } 
	}
	
	@SuppressWarnings("finally")
	public static int getValue3() { 
	       int i = 1; 									//#0=1
	       try { 
	                i = 4; 								//#0=4
	       } finally { 
	                i++; 								//#0=5
	                return i; //return value of getValue(): 5 
	       } 
	  } 
	
	public static int getValue4() { 
	       int i = 1; 
	       try { 
	                i = 4; 
	       } finally { 
	                i++; 
	       } 
	        
	       return i;  //return value of getValue(): 5 
	}
	
	//请完成测试
	public static String test() {  
		try {  
		System.out.println("try block");  
		return test1();  
		} finally {  
		System.out.println("finally block");  
		        }  
		    }  
		public static String test1() {  
		System.out.println("return statement");  
		return "after return";  
	}  
	
	//请完成测试2
	public static int test2() {
	    int b = 20;
	    try {
	        System.out.println("try block");
	            return b += 80;
	     } catch (Exception e) {
	        System.out.println("catch block");
	     } finally {
	        System.out.println("finally block");
	       if (b > 25) {
	         System.out.println("b>25, b = " + b);
	       }
	       b = 150;
	      }
	    return 2000;
	}
		
		
	
		
		
	/*以下为答案
	 *--------------------------- 
	 * test():
	  		try block 
			return statement 
			finally block 
			after return
		-----
		test2():
			try block
			finally block
			b>25, b = 100
			100

	 * */
	
//-----------------------------------------------------------------------------------------------------------------------
	/**
	 * 【总结】
	 * 	1. 至少有两种情况下finally语句是不会被执行的：
			（1）try语句没有被执行到，如在try语句之前就返回了，这样finally语句就不会执行，
				这也说明了finally语句被执行的必要而非充分条件是：相应的try语句一定被执行到。
			（2）在try块中有System.exit(0);这样的语句，System.exit(0);是终止Java虚拟机JVM的，连JVM都停止了，所有都结束了，当然finally语句也不会被执行到。
			
	 * */
	
	@SuppressWarnings("finally")
	public static int test9() {
        int b = 20;
        try {
            System.out.println("try block");
            /*b =b+1;
             *return b;
             * */
            return b += 80;//b
        } catch (Exception e) {
            System.out.println("catch block");
        } finally {
            System.out.println("finally block");
            //b ++;
            if (b > 20) {
                System.out.println("b>20, b = " + b);
            }
            return 200;//return 200 --->说明了返回语句是try中的return语句而不是 finally外面的return 
        }
        // return b;
    }
	
	/*
	* public static int getValue() { 
	       int i = 1; 
	       try { 
	                return i; 
	       } finally { 
	                i++; 
	       } 
	    } 
	 * 
	 * 
	 * 		return的时候是复制了一个变量然后返回，所以之后finally操作的变量如果是基本类型的话不会影响返回值。 
			但是如果返回值是引用类型的话，因为指向同一个对象所以还是有影响的。
			
			#0 --> m /m.t ==》(heap1) m.f ==》(heap1) ==》null
			#1 --> m /m.t ==》(heap1) 
	 * */
	
    public static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("KEY", "INIT");
         
        try {
            map.put("KEY", "TRY");
            return map;
        }
        catch (Exception e) {
            map.put("KEY", "CATCH");
        }
        finally {
            map.put("KEY", "FINALLY");
            map = null;
        }
        return map;
    }
    
	public static Map<String, String> getMap2() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("KEY", "INIT");
		
		try {
			map.put("KEY", "TRY");
			return map;
		} 
		catch (Exception e) {
			map.put("KEY", "CATCH");
		}
		finally {
			map.put("KEY", "FINALLY");
		}
		
		return map;
	}

    
    /*
		try block
		catch block
		finally block
		b>25, b = 35
		204
     * */
    public static int test4() {
        int b = 20;

        try {
            System.out.println("try block");

            b = b / 0;

            return b += 80;
        } catch (Exception e) {

            b += 15;
            System.out.println("catch block");
        } finally {

            System.out.println("finally block");

            if (b > 25) {
                System.out.println("b>25, b = " + b);
            }

            b += 50;
        }

        return 204;
    }
}

/*
 * 参考：
 * 	https://www.cnblogs.com/lanxuezaipiao/p/3440471.html
 * 	https://www.ibm.com/developerworks/cn/java/j-lo-finally/index.html
 * 
 * 总结：
 * 			
			只有与 finally 相对应的 try 语句块得到执行的情况下，finally 语句块才会执行。
			 即使与 finally 相对应的 try 语句块得到执行的情况下，finally 语句块一定会执行吗？
					- try 语句块中执行了 System.exit (0) 语句，终止了 Java 虚拟机的运行。
					- 当一个线程在执行 try 语句块或者 catch 语句块时被打断（interrupted）或者被终止（killed），与其相对应的 finally 语句块可能不会执行。
					- 线程运行 try 语句块或者 catch 语句块时，突然死机或者断电，finally 语句块肯定不会执行了。
					
					
					
			finally块的语句在try或catch中的return语句执行之后返回之前执行
			且finally里的修改语句不影响try或catch中 return已经确定的返回值，
			若finally里也有return语句则覆盖try或catch中的return语句直接返回。
			

 * 
 * 
 * 
 * */

