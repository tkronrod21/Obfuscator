package com.kronrod.obfuscator;

import java.util.List;
import java.util.Optional;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.Comment;

/**
 * 
 * @author tkronrod21
 * @version 0.1.0
 */

public class CommentRemover {
	CompilationUnit cu;
	
	public CommentRemover(CompilationUnit cu) {
		this.cu = cu;
	}
	
	public void removeAllComments() {
		List<Comment> comments = cu.getAllContainedComments();
		
		for(Comment comment : comments) {
			comment.remove();
		}
	}
	
	public void printAsString() {
		System.out.println(this.cu.toString());
	}
	
	public CompilationUnit returnCu() {
		return this.cu;
	}
}
