package corejava;

public class Synchronized extends Thread{
	
	private int count = 0;
	
	public void run() {
		System.out.println(Thread.currentThread().getName()+" count= "+ count++);
	}
	
	public static void main(String[] args) {

		for(int i=0;i<5;i++) {
			new Synchronized().start();
		}
	}
}

class ThreadTest {
}

/*
 * 	
 * */
class TestSynchronized1 {
	public static void main(String[] args){
		
		Runner2 r2 = new Runner2();
		Thread thread = new Thread(r2);
		thread.start();
		r2.method2();
	}
}

class Runner2 implements Runnable{
	
	int b = 0;
	
	public void run() {
		method1();
	}
	
	public synchronized void method1(){
		b = 100;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("b = "+b);
	}
	
	public void method2(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		b = 500;
	}
}