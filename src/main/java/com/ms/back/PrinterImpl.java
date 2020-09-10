package com.ms.back;

import com.ms.back.util.persist.Printer;

public class PrinterImpl implements Printer {

	public void print(String s) {
		System.out.println(s);
	}

	public Printer clone() {
		return new PrinterImpl();
	}

}
