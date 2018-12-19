package com.kronrod.obfuscator;

import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * 
 * @author tkronrod21
 * @version 0.1.0
 */
public class NameGiver {
	
	public static String encryptedName(String plaintext)
    {
    	// transposition part
    	String overflow = "";
    	
    	int amountOver = 4 - (plaintext.length() % 4);
    	for (int i = 0; i < amountOver; i++)
    	{
    		overflow += '\n';
    	}
    	
    	plaintext += overflow;
    	
    	//divide string into 4 groups and then shuffle them that way
    	String[] groups = new String[4];
    	for (int i = 0; i < 4; i++) 
    	{
    		groups[i] = new String();
    	}
    	
    	for (int i = 0; i < plaintext.length(); i++)
    	{
    		groups[i % 4] += plaintext.charAt(i);
    	}
    	
    	String halfway = "";
    	
    	for (int i = 0; i < 4; i++) 
    	{
    		halfway += groups[i];
    	} 
    	
    	String result = "";
    	
    	//XOR Cipher
    	byte[] toConvert = halfway.getBytes(); //byte val array of every char in string
    	byte[] finished = new byte[toConvert.length];
    	for (int i = 0; i < toConvert.length; i++)
    	{
    		byte letter = toConvert[i];
    		byte mask = (byte) 255; //can be any int between 0 and 255 to vary encyption
    		byte newLetter = (byte) (letter^mask);
    		finished[i] = newLetter;
    	}
    	Encoder encoder = Base64.getEncoder();
    	result = encoder.encodeToString(finished);
    	
    	result = removeIllegalChars(result);
    	
    	return result;
    }
	
	private static String removeIllegalChars(String name) {
		String[] toRemove = {"=",
							 ">",
							 "<",
							 "?",
							 " ",
							 "!",
							 "@",
							 "'",
							 "\"",
							 "{",
							 "}",
							 "[",
							 "]",
							 "%",
							 "$",
							 "*",
							 "^",
							 "#",
							 "&",
							 "|",
							 "\\",
							 "/"};
		for(int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			for(String n : toRemove) {
				if(n.equals("" + c)) {
					name = name.substring(0, i) + name.substring(i + 1, name.length());
				}
			}
		}
		
		return name;
	}
}
