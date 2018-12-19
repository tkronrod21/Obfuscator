package com.kronrod.obfuscator;

public class TestCase {
	private int var = 12;
	
	public void Method() {
		int var1 = 13;
	}
	
	public void Method(String s) {
		Method1();
	}
	
	/**
	 * comment
	 */
	public void Method1() {
		String s = "aaa"; //comment
		s.toString();
	}
}