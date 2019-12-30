package corejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
