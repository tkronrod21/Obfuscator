package com.kronrod.obfuscator;
import java.io.File;

/**
 * 
 * @author tkronrod21
 * @version 0.1.0
 */
public class Runner {

	public static void main(String[] args) {
		File file = FileGetter.getFile();
		Reader r = new Reader(file);
		System.exit(0);
	}

}
