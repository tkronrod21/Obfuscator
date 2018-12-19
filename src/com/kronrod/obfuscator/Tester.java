package com.kronrod.obfuscator;
import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

/**
 * 
 * @author tkronrod21
 * @version 0.1.0
 */
public class Tester {

	public static void main(String[] args) throws FileNotFoundException {
		CompilationUnit cu = JavaParser.parse(new File("/Volumes/GoogleDrive/My Drive/School/2. Coding/KronrodTalJava/Java/Unfinished Code/Java-Obfuscator/src/com/kronrod/obfuscator/TestCase.java"));
		MethodChanger mc = new MethodChanger(cu);
		mc.changeMethods();
		
		cu = mc.returnCu();
		CommentRemover cr = new CommentRemover(cu);
		cr.removeAllComments();
		
		cu = cr.returnCu();
		Printer p = new Printer(cu);
		String text = p.removeNewLineChars();
		
		System.out.println(text);
		
		
		
	}

}
