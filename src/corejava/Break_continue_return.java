package corejava;

public class Break_continue_return {
	
	public static void main(String[] args) {
		
		// break 强行退出循环,不执行循环中剩余语句
		for (int i = 0; i < 10; i++) {
			  if (i == 4) {
			    break;
			  }
			  System.out.print(" "+i);
		}//0 1 2 3
		
		System.out.println();
		
		// continue 停止当前迭代，退回上次循环起始处，开始下一次迭代
		for (int i = 0; i < 10; i++) {
			  if (i == 4) {
			    continue;
			  }
			  System.out.print(" "+i);
		}//0 1 2 3 5 6 7 8 9
		
		System.out.println();
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				  if (j == 4) {
					    break;
					  }
				  System.out.print(" "+j);
			}
			  System.out.println(" "+i);
		}/*
		 0 1 2 3 0
		 0 1 2 3 1
		 0 1 2 3 2
		 0 1 2 3 3
		 0 1 2 3 4
		 0 1 2 3 5
		 0 1 2 3 6
		 0 1 2 3 7
		 0 1 2 3 8
		 0 1 2 3 9
		*/
		
		System.out.println();
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				  if (j == 4) {
					  continue;
					  }
				  System.out.print(" "+j);
			}
			  System.out.println(" "+i);
		}/*
		 0 1 2 3 5 6 7 8 9 0
		 0 1 2 3 5 6 7 8 9 1
		 0 1 2 3 5 6 7 8 9 2
		 0 1 2 3 5 6 7 8 9 3
		 0 1 2 3 5 6 7 8 9 4
		 0 1 2 3 5 6 7 8 9 5
		 0 1 2 3 5 6 7 8 9 6
		 0 1 2 3 5 6 7 8 9 7
		 0 1 2 3 5 6 7 8 9 8
		 0 1 2 3 5 6 7 8 9 9
		*/
		
		System.out.println();
		
		// return 执行后,方法不在继续执行
		for (int i = 0; i < 10; i++) {
			  if (i == 4) {
			    return;
			  }
			  System.out.print(" "+i);
		}//0 1 2 3
		
		// 在有返回值的方法中，必须确保每一条路径都有一个返回值
		returnT(0);// 不被执行
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				  if (j == 4) {
					    break;
					  }
				  System.out.print(" "+j);
			}
			  System.out.println(" "+i);
		}//不被执行
	}
	
	public static int returnT(int i) {
		if (i>0)
			return +1;
		else if(i<0)
			return -1;
		else
			return 0;
	}
}
