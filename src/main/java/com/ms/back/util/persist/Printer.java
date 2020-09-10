package com.ms.back.util.persist;

public interface Printer extends Cloneable {

	public void print(String s);
	
	public Printer clone();

}
