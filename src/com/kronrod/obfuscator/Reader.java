package com.kronrod.obfuscator;
import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import javax.swing.JOptionPane;
/**
 * Reads in code from file and returns in multiple formats
 * @author tkronrod21
 * @version 0.1.0
 */
public class Reader {
	File file;
	CompilationUnit cu;
	
	public Reader(File file) {
		this.file = file;
		try {
			this.cu = JavaParser.parse(this.file);
			this.start();
		} 
		catch (FileNotFoundException e) {
			String title = "ERROR";
			String message = "The file you tried to parse does not exist";
			JOptionPane.showMessageDialog(null, 
					title, 
					message,
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	public void start() {
		
	}
	
	public void removeComments() {
		
	}
	
	public void changeMethodNames() {
		MethodChanger mc = new MethodChanger(cu);
		mc.changeMethods();
	}
	
	public void collectVariableNames() {
		
	}
}
