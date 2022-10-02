/*
PROGRAM NOTES: 
 
Written by Austin Daigle
Program Version: 1.0

This program was started on: March 3rd, 2018.
This program was finished on: March 4th, 2018.
Programming coarse being taken while under development: AP Computer Science

this was designed independent development project. 
This script was originally made as a personal project for the sake a advancement 
in algorithm design. All code here has been written by Austin Daigle.

The word bank in this program can easily be adapted to fit any word list. To change
the word bank simply find the arrayList entries and delete them, then replace 
the arrayList entries with an alternative.

The code for the entries looks like this:
	wordBank.add("insert_word_here");
	
	
 
*/

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.Random;
import java.net.URL;

public class Main {
	//create the public variables and object at are needed
	public static ArrayList<String> Alphabet = new ArrayList<String>();
	public static ArrayList<String> guiAlphabet = Alphabet;
	public static ArrayList<String> correctSelections = new ArrayList<String>();
 	public static ArrayList<String> displayExemptions = new ArrayList<String>();
 	public static ArrayList<String> wordBank = new ArrayList<String>();

	public static URL mistakezeroURL = Main.class.getResource("/resources/mistakeZero.png");
	public static URL mistakeoneURL = Main.class.getResource("/resources/mistakeOne.png");
	public static URL mistaketwoURL = Main.class.getResource("/resources/mistakeTwo.png");
	public static URL mistakethreeURL = Main.class.getResource("/resources/mistakeThree.png");
	public static URL mistakefourURL = Main.class.getResource("/resources/mistakeFour.png");
	public static URL mistakefiveURL = Main.class.getResource("/resources/mistakeFive.png");
	public static URL mistakesixURL = Main.class.getResource("/resources/mistakeSix.png");

	public static ImageIcon mistakezero = new ImageIcon(mistakezeroURL);
	public static ImageIcon mistakeOne = new ImageIcon(mistakeoneURL);
	public static ImageIcon mistakeTwo = new ImageIcon(mistaketwoURL);
	public static ImageIcon mistakeThree = new ImageIcon(mistakethreeURL);
	public static ImageIcon mistakeFour = new ImageIcon(mistakefourURL);
	public static ImageIcon mistakeFive = new ImageIcon(mistakefiveURL);
	public static ImageIcon mistakeSix = new ImageIcon(mistakesixURL);
	public static String word = "";
	public static int incorrect = 0;
	
	public static void main(String args[])
	{
		//create base alphabet
		Alphabet.add("A"); Alphabet.add("B"); Alphabet.add("C"); Alphabet.add("D"); Alphabet.add("E");
		Alphabet.add("F"); Alphabet.add("G"); Alphabet.add("H"); Alphabet.add("I"); Alphabet.add("J");
		Alphabet.add("K"); Alphabet.add("L"); Alphabet.add("M"); Alphabet.add("N"); Alphabet.add("O");
		Alphabet.add("P"); Alphabet.add("Q"); Alphabet.add("R"); Alphabet.add("S"); Alphabet.add("T");
		Alphabet.add("U"); Alphabet.add("V"); Alphabet.add("W"); Alphabet.add("X"); Alphabet.add("Y");
		Alphabet.add("Z");
		//Introduce word/symbols display exemptions 
		displayExemptions.add("!"); displayExemptions.add("@"); displayExemptions.add("#");
		displayExemptions.add("$"); displayExemptions.add("%"); displayExemptions.add("^");
		displayExemptions.add("&"); displayExemptions.add("*"); displayExemptions.add("(");
		displayExemptions.add(")"); displayExemptions.add("{"); displayExemptions.add("}");
		displayExemptions.add("+"); displayExemptions.add("["); displayExemptions.add("]");
		displayExemptions.add("~"); displayExemptions.add("`"); displayExemptions.add(",");
		displayExemptions.add("."); displayExemptions.add("?"); displayExemptions.add("'");
		displayExemptions.add(":"); displayExemptions.add("\""); displayExemptions.add("|");
		displayExemptions.add("<"); displayExemptions.add(">"); 
		//Introduce number display exemptions 
		displayExemptions.add("1"); displayExemptions.add("2"); displayExemptions.add("3");
		displayExemptions.add("4"); displayExemptions.add("5"); displayExemptions.add("6");
		displayExemptions.add("7"); displayExemptions.add("8"); displayExemptions.add("9");
		displayExemptions.add("0");
		
		//Program Splash Screen
		JOptionPane.showMessageDialog(null, "Welcome to Java Hangman!\n"
				+"Written by Austin Daigle\n"
				+"A version of hangman written in Java with Java themed vocabulary.\n",
				"Java Hangman",
				JOptionPane.PLAIN_MESSAGE);
		runGame();
	}
	
	//Randomly select a word from the java vocabulary bank
	public static void selectRandomWord()
	{
		Random randomWord = new Random();
		//Word Bank [delete and replace to change the word list]
		wordBank.add("boolean"); wordBank.add("Class"); 
		wordBank.add("Comments"); wordBank.add("Compiler");
		wordBank.add("Concatenation"); wordBank.add("Constructor"); 
		wordBank.add("double"); wordBank.add("field"); 
		wordBank.add("if"); wordBank.add("import"); 
		wordBank.add("instance"); wordBank.add("instance variable");
		wordBank.add("int"); wordBank.add("Keyword"); 
		wordBank.add("method"); wordBank.add("Object"); 
		wordBank.add("primitive type"); wordBank.add("private");
		wordBank.add("public"); wordBank.add("return type"); 
		wordBank.add("void"); wordBank.add("parameter");
		wordBank.add("setter"); wordBank.add("inheritance"); 
		wordBank.add("getter"); wordBank.add("object");
		wordBank.add("nested loops"); wordBank.add("overloading"); 
		wordBank.add("polymorphism"); wordBank.add("ArrayList");
		wordBank.add("String"); wordBank.add("for each loop");
		wordBank.add("do while loop"); wordBank.add("while loop");
		wordBank.add("do loop"); wordBank.add("for loops");
		//select a random word from the index of zero to max word bank entry
		word = wordBank.get(randomWord.nextInt(wordBank.size()));
		
	}
	
	//primary operating loop
	public static void runGame()
	{
		clearVaribles();
		selectRandomWord();
		while(incorrect < 6)
		{
			//if the play has won display this notification
			if(printToScreen().equals(word)) 
			{
				JOptionPane.showMessageDialog(null,
				"You have won!",
				"Notification",
				JOptionPane.PLAIN_MESSAGE);
				replay();
				break;
			}
			generateUI();
		}
		
		if(!(printToScreen().equals(word)))
		{
			JOptionPane.showMessageDialog(null,
			"You have lost!",
			"Hangman",
			JOptionPane.ERROR_MESSAGE);
			replay();
		}	
	}
	
	public static void replay()
	{
		Object[] replayMenu = {"Yes","No"};
		
		int replayInput = JOptionPane.showOptionDialog(null,
			"Would you like to play Again?",
			"Replay?",
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			replayMenu,
			replayMenu[1]);
		
		if(replayInput==0)
		{
			runGame();
		}
		else
		{
			System.exit(0);
		}
		
	}
	
	//clear values 
	@SuppressWarnings("unchecked") //I am not fully sure what the error suppression is but it is needed
	public static void clearVaribles()
	{
		incorrect = 0;
		guiAlphabet = (ArrayList<String>)Alphabet.clone();
		correctSelections.clear();
		
	}
	
	//primary interface
	public static void generateUI()
	{
		Object[] alphabetOptions = guiAlphabet.toArray();
		int data = 0;
		switch(incorrect)
		{
		case 0: 
			data = JOptionPane.showOptionDialog(null, 
					printToScreen(), 
			        "Hangman", 
			        JOptionPane.YES_NO_CANCEL_OPTION, 
			        JOptionPane.INFORMATION_MESSAGE,
			        new ImageIcon(mistakezeroURL),
			        alphabetOptions, 
			        alphabetOptions[0]);
		break;
		case 1: 
			data = JOptionPane.showOptionDialog(null, 
					printToScreen(), 
			        "Hangman", 
			        JOptionPane.YES_NO_CANCEL_OPTION, 
			        JOptionPane.INFORMATION_MESSAGE,
			        new ImageIcon(mistakeoneURL),
			        alphabetOptions, 
			        alphabetOptions[0]);
		break;
		case 2: 
			data = JOptionPane.showOptionDialog(null, 
					printToScreen(), 
			        "Hangman", 
			        JOptionPane.YES_NO_CANCEL_OPTION, 
			        JOptionPane.INFORMATION_MESSAGE,
			        new ImageIcon(mistaketwoURL),
			        alphabetOptions, 
			        alphabetOptions[0]);
		break;
		case 3: 
			data = JOptionPane.showOptionDialog(null, 
					printToScreen(), 
			        "Hangman", 
			        JOptionPane.YES_NO_CANCEL_OPTION, 
			        JOptionPane.INFORMATION_MESSAGE,
			        new ImageIcon(mistakethreeURL),
			        alphabetOptions, 
			        alphabetOptions[0]);
		break;
		case 4:
			data = JOptionPane.showOptionDialog(null, 
					printToScreen(), 
			        "Hangman", 
			        JOptionPane.YES_NO_CANCEL_OPTION, 
			        JOptionPane.INFORMATION_MESSAGE,
			        new ImageIcon(mistakefourURL),
			        alphabetOptions, 
			        alphabetOptions[0]);
		break;
		case 5: 
			data = JOptionPane.showOptionDialog(null, 
					printToScreen(), 
			        "Hangman", 
			        JOptionPane.YES_NO_CANCEL_OPTION, 
			        JOptionPane.INFORMATION_MESSAGE,
			        new ImageIcon(mistakefiveURL),
			        alphabetOptions, 
			        alphabetOptions[0]);
		break;
		case 6: 
			data = JOptionPane.showOptionDialog(null, 
					printToScreen(), 
			        "Hangman", 
			        JOptionPane.YES_NO_CANCEL_OPTION, 
			        JOptionPane.INFORMATION_MESSAGE,
			        new ImageIcon(mistakesixURL),
			        alphabetOptions, 
			        alphabetOptions[0]);
		break;
		}
	
		System.out.println("Choosen selection: "+guiAlphabet.get(data));
		
		if(checkAnswer(guiAlphabet.get(data))==false)
		{
			JOptionPane.showMessageDialog(null,"Incorrect Guess.","Notification",JOptionPane.ERROR_MESSAGE);
			incorrect++;
		}
		else
		{
			correctSelections.add(guiAlphabet.get(data));
			JOptionPane.showMessageDialog(null,"Correct Guess","Notification",JOptionPane.PLAIN_MESSAGE);
			System.out.println("Character Found!");
		}
		
		guiAlphabet.remove(data);
	
	}
	
	//create the 'blank' word output
	public static String printToScreen()
	{
		String printText = "";
		
		for(int i = 0; i < word.length(); i++)
		{

			if(showChar(String.valueOf(word.charAt(i)))==0)
			{
			printText = printText + "-";
			}
			else if(showChar(String.valueOf(word.charAt(i)))==2)
			{
			printText = printText + " ";
			}
			else
			{
			printText = printText + word.charAt(i);
			}

		}
		return printText;
	}
	
	//filter characters based on type
	public static int showChar(String givenChar)
	{
		int verifyResult = 0;
		for(int i = 0; i < correctSelections.size(); i++)
		{
			if(givenChar.equalsIgnoreCase(correctSelections.get(i)))
			{
				verifyResult = 1;	
			}
		
		}
		for(int i = 0; i < displayExemptions.size(); i++)
		{
			if(givenChar.equalsIgnoreCase(displayExemptions.get(i)))
			{
				verifyResult = 1;	
			}
		}
		for(int i = 0; i < displayExemptions.size(); i++)
		{
			if(givenChar.equalsIgnoreCase(" "))
			{
				verifyResult = 2;	
			}
		}
		return verifyResult;
	}
	
	//check user answer
	public static boolean checkAnswer(String userInput)
	{
		boolean verifyResult = false;
		for(int i = 0; i < word.length(); i++)
		{
			if(userInput.equalsIgnoreCase(String.valueOf(word.charAt(i))))
			{
				verifyResult = true;
			}
		}
		return verifyResult;
	}
	
}
