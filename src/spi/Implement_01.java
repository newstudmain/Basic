package spi;

public class Implement_01 implements Service {

	@Override
	public void read() {
		System.out.println("01...read()");

	}

	@Override
	public void load() {
		System.out.println("01...road()");

	}

	@Override
	public void exit() {
		System.out.println("01...exit()");

	}

}
