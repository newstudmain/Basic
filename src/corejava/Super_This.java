package corejava;

public class Super_This {
	public static void main(String[] args) {
		new suC().print();
	}
}

class suA{
	int a =10;
}

class suB extends suA{
	int a = super.a+10;
	
}

class suC extends suB{
	public void print() {
		System.out.println(super.a);
	}
}