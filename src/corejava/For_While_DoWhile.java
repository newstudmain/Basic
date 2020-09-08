package corejava;

public class For_While_DoWhile {
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 10; i++) {
			System.out.print(" "+i);
		}
		System.out.println();
		
		int i = 0;
		while(i < 10) {
			System.out.print(" "+i);
			i++;
		}
		System.out.println();
		
		int j = 0;
		do {
			System.out.print(" "+j);
			j++;
		} while (j < 10);
		
		
	}
}
