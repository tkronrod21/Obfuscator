
package com.kronrod.obfuscator;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.visitor.ModifierVisitor;

/**
 * 
 * @author tkronrod21
 * @version 0.1.0
 */

public class Printer {
	CompilationUnit cu;
	int SPACE_SCAN_AMOUNT = 10;
	int MAX_SPACE_AMOUNT = 4;
	
	public Printer(CompilationUnit cu) {
		this.cu = cu;
	}
	
	public String removeNewLineChars() {
		String originalText = this.cu.toString();
		originalText = originalText.replace("\n", " ");
		originalText = originalText.replace("\t", " "); // \t = tabs
		
		for(int i = 0; i < this.SPACE_SCAN_AMOUNT; i++)
			originalText = originalText.replace("  ", " ");
		
		String text = addRandomSpacing(originalText);
		return text;
	}
	
	private String addRandomSpacing(String originalText) {
		// TAKEN FROM MANUAL AS A WAY TO GET STRING VARS
		// checks if there is a var and if so if its a string var
        ArrayList<Expression> list = new ArrayList<Expression>();

		class MyVisitor extends ModifierVisitor<Void> {
		    @Override
		    public Node visit(VariableDeclarator declarator, Void args) {
	            Expression expression = declarator.getInitializer().get();
	            
	            
	            // We're looking for string.
	            if (expression instanceof StringLiteralExpr) {
	                list.add(expression);
	            }
		        return null;
		    }
		}
		
		
	}
	
	public CompilationUnit returnCu() {
		return this.cu;
	}
}
