package corejava;


/*
 * 
 * 
 * */
public class Clone {
	public static void main(String[] args) throws CloneNotSupportedException {
		ClonSon s1 = new ClonSon("son");
		ClonFather f1 = new ClonFather(10, s1);
		System.out.println(f1+" --- "+f1.hashCode());
		ClonFather f2 = (ClonFather)f1.clon();
		ClonFather f3 = f1;
		System.out.println(f2+" --- "+f2.hashCode());
		System.out.println(f3+" --- "+f3.hashCode());
		//ClonSon s2 = (ClonSon)f1.clon_son.clon();
		System.out.println(s1+" --- "+s1.hashCode());
		//System.out.println(s2+" --- "+s2.hashCode());
	}
}

class ClonFather implements Cloneable{
	public int i;
	public ClonSon clon_son;
	
	public ClonFather(int i) {
		this.i = i;
	}
	
	public ClonFather(int i,ClonSon clon_son) {
		this.i = i;
		this.clon_son = clon_son;
	}
	
	@Override
	public String toString() {
		return "ClonFather [i=" + i + ", clon_son=" + clon_son + "]";
	}

	public Object clon() throws CloneNotSupportedException {
		return super.clone();
	}
}

class ClonSon //implements Cloneable
{
	public String s;
	
	public ClonSon(String s) {
		this.s = s;
	}
	
	@Override
	public String toString() {
		return "ClonSon [s=" + s + "]";
	}

	public Object clon() throws CloneNotSupportedException {
		return super.clone();
	}
	
}