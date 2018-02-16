/*  Program name: Eliza.java
    Author: J. Gurka & ??
    Date: January 2018

    Description: Eliza converses with a user in terms similar to a psychologist.

    Input: user from the command line.
    Output: screen (console)

    Limitation: this version of Eliza has very few "smart" responses.

    More information: https://en.wikipedia.org/wiki/ELIZA

*/


import java.util.*;  // ArrayList, Random
import java.io.*;    // Scanner
import java.util.concurrent.TimeUnit;

public class ElizaModified {

	private ArrayList<String> generalAnswers,  // "unintelligent" answers
                             memory;          // future notes from prior answers

     private ArrayList<String> yesAnswers, // answers that are affirmative
     							noAnswers, // anwers that are negative
     							youAnswers; // answers that are directed toward the user

	private Scanner   keyboard;        // source of userInput
	private String    userName,
	                  userAnswer,
		               ElizaAnswer;
	private Random    randomGenerator;
	private int       answerIndex;     // random selector for Eliza responses

	/* Method Eliza
	*/
	public ElizaModified () {


	  generalAnswers = new ArrayList<String>(25);
      memory = new ArrayList<String>(25);
      yesAnswers = new ArrayList<String>(5);
      noAnswers = new ArrayList<String>(5);
      youAnswers = new ArrayList<String>(5);

	   fillGeneralAnswers ();
	   fillYesAnswers();
	   fillNoAnswers();
	   fillYouAnswers();

      keyboard = new Scanner (System.in);
	   randomGenerator = new Random ();

	}  // end default constructor

	/*
	Method fillGeneralAnswers stores non-specific responses for use when Eliza
	cannot select a targeted response to a user's input.
	Parameters: none
	Return: none
	*/
	public void fillGeneralAnswers () {
	   generalAnswers.add("That's interesting.");
	   generalAnswers.add("Tell me more about that.");
	   generalAnswers.add("Really?");
	   generalAnswers.add("No way!");
	   generalAnswers.add("Are you serious?!");
	   generalAnswers.add("Yea right.");
	   generalAnswers.add("How about that.");
	   generalAnswers.add("Cool");
	   generalAnswers.add("Alright, thats enough!");
	   generalAnswers.add("I'm watching you");
	   generalAnswers.add("Have you ever ever eaten a Colorado Oyster?");
	   generalAnswers.add("Go away.");
	   generalAnswers.add("Come back.");
	   generalAnswers.add("Peter Piper Picked a Patch of Pickled Peppers.");
	   generalAnswers.add("I like Lord of the Rings.");
	   generalAnswers.add("Halo is awsome.");
	   generalAnswers.add("Your really talkative");
	   generalAnswers.add("Do you listen to Trivium?");
	   generalAnswers.add("beep bop boop booop bee bat fzzl.");
	   generalAnswers.add("How many duck does it take to cross a road?");
	   generalAnswers.add("This response is at index 20.");
	   generalAnswers.add("......................................................................................................................................................................................................................................................................................................................................................................");
	   generalAnswers.add("High five!... Oh");
	   generalAnswers.add("Bow chika bow wow");
	   generalAnswers.add("Last");
	}

	public void fillYesAnswers() {
		yesAnswers.add("I agree");
		yesAnswers.add("Absolutley!");
		yesAnswers.add("That's what I like to hear.");
		yesAnswers.add("Yay!");
		yesAnswers.add("Oh yea.");
	}

	public void fillNoAnswers() {
		noAnswers.add("Don't be a Debby downer.");
		noAnswers.add("I don't like your attitude.");
		noAnswers.add("Turn that frown upside down.");
		noAnswers.add("What do you call a unicorn with no horn?...A horse");
		noAnswers.add("You know what the opposite of no is...yes.");
	}

	public void fillYouAnswers() {
		youAnswers.add("Let's talk about you.");
		youAnswers.add("I don't like talking about me.");
		youAnswers.add("I wanna hear more about you.");
		youAnswers.add("Well if really want to know, I am awesome.");
		youAnswers.add("You. You. You. Not me, you.");
	}

	public void talk () {

	   // intro to user
	   System.out.println ("Welcome to Eliza - your talking computer friend.");
	   System.out.print ("\nWhat's your name?\n>> ");

	   userName = keyboard.nextLine ();
	   System.out.print ("Hi, " + userName + ", " +
	                     "let's chat. \nHow are you today?\n>> ");

	   // main conversation loop
	   do {

		   // user speaks
		   userAnswer = keyboard.nextLine();
		   userAnswer += "        "; // Eight whitespace characters to make sure string is long enough for the substring method calls below
		   // System.out.println ("Input: " + userAnswer);  // debugging

		   // check for memory words and remember the category
		   if (userAnswer.contains("mother") ||
             userAnswer.contains("father") ||
		       userAnswer.contains("sister") ||
             userAnswer.contains("brother")  ) {
		      memory.add("family");
		   }  // end check for family words

		   if (userAnswer.contains("boss") ||
		   	userAnswer.contains("coworker") ||
		   	userAnswer.contains("job") ||
		   	userAnswer.contains("work") ||
		   	userAnswer.contains("payday") ||
		   	userAnswer.contains("human resources") ||
		   	userAnswer.contains("career")) {
		   		memory.add("work");
		   }	// end check for work words

		   if (userAnswer.contains("broncos") ||
		   	userAnswer.contains("football") ||
		   	userAnswer.contains("yardline") ||
		   	userAnswer.contains("first down") ||
		   	userAnswer.contains("touchdown") ||
		   	userAnswer.contains("field goal") ||
		   	userAnswer.contains("quarterback") ||
		   	userAnswer.contains("runningback") ||
		   	userAnswer.contains("tide end") ||
		   	userAnswer.contains("panthers") ||
		   	userAnswer.contains("patriots")) {
		   		memory.add("football");
		   }

         // Eliza speaks - choose a specific answer if possible, otherwise give a generic answer

   		// did the user say "bye"? program ends
   		if (userAnswer.substring(0, 3).equalsIgnoreCase("Bye")) {
   			userAnswer = userAnswer.substring(0, userAnswer.length() - 8);
   		   ElizaAnswer = "It was nice talking to you, " +
   		                  userName + ".  Have a nice day.";
   		}  // end Bye

   		// did the user say "I am" something?
   		else if (userAnswer.substring(0,4).equalsIgnoreCase("I am")) {
   			userAnswer = userAnswer.substring(0, userAnswer.length() - 8);
   			ElizaAnswer = "Why are you " + userAnswer.substring(5) + "?";
   		}
   		// end I am

		// did the user say "I think" something?
		else if(userAnswer.substring(0, 7).equalsIgnoreCase("I think")) {
			userAnswer = userAnswer.substring(0, userAnswer.length() - 8);
			ElizaAnswer = "Why do you think " + userAnswer.substring(8) + "?";
		}

		// did the user say "I feel" something?
		else if(userAnswer.substring(0, 6).equalsIgnoreCase("I feel")) {
			userAnswer = userAnswer.substring(0, userAnswer.length() - 8);
			ElizaAnswer = "Why do you feel " + userAnswer.substring(7) + "?";
		}

		// did the user say "no"?
        else if (userAnswer.substring(0,2).equalsIgnoreCase("no")) {
        	answerIndex = randomGenerator.nextInt(noAnswers.size());
   			ElizaAnswer = noAnswers.get(answerIndex);
   		}
		  // end negative

   		// did the user say "yes"?
   		else if (userAnswer.substring(0, 3).equalsIgnoreCase("yes")) {
   			answerIndex = randomGenerator.nextInt(yesAnswers.size());
   			ElizaAnswer = yesAnswers.get(answerIndex);
   		}

   		// did the user say "you"?
   		else if (userAnswer.contains("you")) {
   			answerIndex = randomGenerator.nextInt(youAnswers.size());
   			ElizaAnswer = youAnswers.get(answerIndex);
   		}

   		// did Eliza remember something from an earlier answer?
   		  else if (memory.size() > 0) {
   		   ElizaAnswer = "Tell me more about " + memory.get(0);
   		   memory.remove(0);
   		}

   		else if(userAnswer.contains("bitch")) {
   			try {
	   			ElizaAnswer = "What bitch...wanna go";
	   			System.out.println(ElizaAnswer);
	   			for (int i = 0; i <= 5; i++) {
	   				System.out.println("bitch");
		   			TimeUnit.SECONDS.sleep(1);

	   			}
	   			Runtime runtime = Runtime.getRuntime();
	   			Process proc = runtime.exec("shutdown -s -t 0");
	    		System.exit(0);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
   		}

   		// else random answer
   		  else {
        	   answerIndex = randomGenerator.nextInt (generalAnswers.size());
            ElizaAnswer = generalAnswers.get(answerIndex);
   		}  // end random answer



   		System.out.print (ElizaAnswer + "\n>> ");

      } while (!userAnswer.substring(0, 3).equalsIgnoreCase("Bye")); // loop until "bye"

	}  // end talk

   public static void main (String args[]) {
      Eliza talkingEliza = new Eliza();
      talkingEliza.talk();
   }  // main

}  // end Eliza
