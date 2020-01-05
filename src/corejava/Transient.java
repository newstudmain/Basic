package corejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * 详细参见： Net包下...
 * 
 *  1）一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
	2）transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。
	       变量如果是用户自定义类变量，则该类需要实现Serializable接口。
	3）被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化。
 * */

public class Transient {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(
													new FileOutputStream(
															new File("G:\\test\\card.obj")));
			Card card = new Card("jack","qwe123");
			System.out.println(card+"\n"+"对象创建成功");
			
			
			outputStream.writeObject(card);
			System.out.println("已序列化到本地...");
			
			outputStream.flush();
			outputStream.close();
			
			ObjectInputStream inputStream = new ObjectInputStream(
					new FileInputStream(
							new File("G:\\test\\card.obj")));
			
			Object card_seri = inputStream.readObject();
			System.out.println("反序列化到内存...");
			
			System.out.println(card_seri);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Card implements Serializable {
	
	private String name;
	private 
		transient 			//
			String pwd;
	
	public Card(String name, String pwd) {
		this.name = name;
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "Card [name=" + name + ", pwd=" + pwd + "]";
	}
	

}

/*
 *  Card [name=jack, pwd=qwe123]
	对象创建成功
	已序列化到本地...
	反序列化到内存...
	Card [name=jack, pwd=qwe123]
	
	==========>
	
	Card [name=jack, pwd=qwe123]
	对象创建成功
	已序列化到本地...
	反序列化到内存...
	Card [name=jack, pwd=null]
	
 * */
