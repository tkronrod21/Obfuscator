package com.kronrod.obfuscator;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Brings up a window for a user to select a file in a directory from
 * @author tkronrod21
 *
 * @version 0.1.0
 */

public class FileGetter {
	
	public static File getFile() {
		//getting directory from pop-up window
		FileDialog dialog  = new FileDialog(new Frame()); //if shows error still works
		dialog.setMode(FileDialog.LOAD);
		dialog.setVisible(true);
		String filePath = dialog.getFile(); //if returns null then no file was selected
		
		File file = parseFile(filePath); //returns null if person cancels menu
		
		return file;
	}
	
	public static File parseFile(String filePath) {
		if(filePath != null) {
			File file = new File(filePath);
			return file;
		}
		else 
			return null;
	}
}
