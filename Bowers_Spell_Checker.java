//Author Name: Joachim Bowers
//Date: 05/29/2021
//Program Name: Bowers_spell_checker
//Purpose: Compare a text file and determine if the words contained within are found in a dictionary file.

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Bowers_Spell_Checker {

	//C:\Users\Joachim Bowers\Desktop\School\Summer 2021\software engineering\Module 2\dictionary.txt

	public static void main(String[] args)throws Exception { 
		//create scanner object
		Scanner input = new Scanner(System.in);
		
		//variables
		ArrayList<String> dictionary = new ArrayList<>();
		ArrayList<String> tested = new ArrayList<>();
		
		//loop to write the dictionary file string to an array
		while(dictionary.isEmpty()) {
			System.out.println("Enter the filepath for the dictionary");
			String tempFilePath = new String(input.nextLine());
			String filePath = addBackslash(tempFilePath);
			
			try{dictionary.addAll(Files.readAllLines(Paths.get(filePath)));
			}
			catch(IOException ex) {
				System.out.println(ex.toString());
				System.out.println("Could not find file. Try again");
			}
		}
		//newline
		System.out.println("\n");
		//loop for user input of file to compare to dictionary.
		while(tested.isEmpty()) {
			System.out.println("Enter the filepath for the text you would like to match to the dictionary.");
			String tempFilePath = new String(input.nextLine());
			String filePath = addBackslash(tempFilePath);
			
			try{tested.addAll(Files.readAllLines(Paths.get(filePath)));
			}
			catch(IOException ex) {
				System.out.println(ex.toString());
				System.out.println("Could not find file. Try again");
			}
		}
		//newline
		System.out.println("");
		//compare the two strings
		compareArraylists(dictionary, tested);
		System.out.println("\nThanks for using the spell checker!");
		
	} 
	//method for adding extra "\"
	public static String addBackslash(String str) {
		str = str.replaceAll("\\\\", "\\\\\\\\");
		return str;
	}
	//method for comparing arrayLists
	public static void compareArraylists(ArrayList<String> dictionary, ArrayList<String> tested) {
		//boolean to tell whether no spelling errors were found
		boolean errorsFound = false;
		//loop
		for(int i = 0; i < tested.size(); i++) {
			if(dictionary.contains(tested.get(i)) == false) {
				System.out.println(tested.get(i) + " is an unknown word");
				errorsFound = true;
			}
		}
		if(errorsFound == false){
			System.out.println("No spelling errors found!");
		}
	}
}