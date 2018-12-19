package com.kronrod.obfuscator;
import java.util.HashMap;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * 
 * @author tkronrod21
 * @version 0.1.0
 */

public class MethodChanger {
	
	
    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodCallVisitor extends VoidVisitorAdapter<Void> {
        HashMap<String, String> methodNames;
    	
    	public MethodCallVisitor(HashMap<String, String> methodNames) {
    		this.methodNames = methodNames;
		}
    	
    	@Override
        public void visit(MethodCallExpr n, Void arg) {
    		//checks if the method name is the name of a method already modified
    		if(methodNames.containsKey(n.getNameAsString())) {
    			n.setName(methodNames.get(n.getNameAsString())); //sets name to the new method name 
    															// of that particular method
    		}
            super.visit(n, arg);
        }
    }
	
	private int numberOfMethods = 0;
	CompilationUnit cu;
	private HashMap<String, String> methodsOldVsNew = new HashMap<String, String>();
	
	
	/**
	 * Constructor
	 * @param cu the CompliationUnit containing the file being parsed
	 */
	public MethodChanger(CompilationUnit cu) {
		this.cu = cu;
	}
	
	
	/**
	 * Scans through all Nodes, and if one is a MethodDeclaration then it will
	 * change the name of the method call and all locations where the method is referenced
	 */
	public void changeMethods() {
		//go through all types in the file
		NodeList<TypeDeclaration<?>> types = this.cu.getTypes();
		
		for(TypeDeclaration<?> type : types) {
			//go through all fields, methods, etc. in this type
			NodeList<BodyDeclaration<?>> members = type.getMembers();
			
			for(BodyDeclaration<?> member : members) {
				if(member instanceof MethodDeclaration) {
					MethodDeclaration method = (MethodDeclaration) member;
					changeMethod(method); //changes the name of the method
				}
			}
		}
		
		//changes all the method calls of methods that were already changed
		cu.accept(new MethodCallVisitor(methodsOldVsNew), null); 
	}
	
	
	/**
	 * renames the name of the method
	 * @param n the node of the method declaration
	 */
	private void changeMethod(MethodDeclaration n) {
		String newName;
		String name = n.getNameAsString();
		
		if(methodsOldVsNew.containsKey(name)) {
			newName = methodsOldVsNew.get(name);
		}
		else {
			newName = NameGiver.encryptedName(name);
			methodsOldVsNew.put(name, newName);
			this.numberOfMethods++;
		}
		
		n.setName(newName); //names the methods
		
	}
	
	/**
	 * prints the file to console
	 */
	public void printFile() {
		String s = cu.toString();
		System.out.println(s);
	}
	
	public CompilationUnit returnCu() {
		return this.cu;
	}
}
