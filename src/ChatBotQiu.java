 import java.util.Random;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Sophia Qiu 
 * @version Oct - Nov 2017 
 * @class period 2 Mr. Levin
 */
public class ChatBotQiu
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Heyo, PackBot at your service. What do you need help with?";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";
		Random r = new Random ();
		
		
		if (statement.length() == 0)
		{
			response = "speak up please";
		}
		
		//how to make bot annoyed
		
		else if (findKeyword(statement, "quit") >= 0)
		{
			
			emotion--;
			return randomQuitResponses [r.nextInt(randomQuitResponses.length)];
			
		}
		
		
		else if (findKeyword(statement,"ugh") >= 0)
		{
			emotion--;
			response = "you're breaking the mood";
		
		}
		
		
		
		//how to make bot happy
		
		else if (findKeyword(statement, "done") >= 0)
		{
			emotion++;
			response = "!!! That's good";
			
		}
		
		
		else if (findKeyword(statement,"progress") >= 0)
		{
			response = "I knew you were capable";
			emotion++;
		}
		
		//how much to bring

		else if (findKeyword(statement, "many") >= 0)
		{
			response = "Well, how many do you need?";
			
			if (findKeyword(statement, "a lot") >= 0) {
				response = "Oh boy..";
			}
			else if (findKeyword(statement, "not") >= 0) {
				response = "that'll saves more room for other things!";
			}
				
                	
		}
		
		else if (findKeyword(statement, "can") >= 0)
		{
			response = "Of course.";
			
		}
		
		
		else if (findKeyword(statement,"tired") >= 0)
		{
			response = "Cheer up!";
			
		}
		
		
		//basic items
		
		else if (findKeyword(statement, "forget") >= 0)
		{
			return randomForgetResponses [r.nextInt(randomForgetResponses.length)];
			
		}
		
		
		else if (findKeyword(statement,"money") >= 0)
		{
			response = "Check out options at your bank to see if it's available internationally so you can just transfer money over! Also beware of fees.";
			
		}
		
		
		else if (findKeyword(statement, "ok") >= 0)
		{
			response = "Glad we're agreeing on something!";
			
		}
		
		
		else if (findKeyword(statement,"yes") >= 0)
		{
			response = "I knew you would say that.";
			
		}
		
		
		
		
		
		
		
		
		
		
		
		

		// Response transforming I need help statement
		else if (findKeyword(statement, "I need help", 0) >= 0)
		{
			response = transformINeedHelpStatement(statement);
		}
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}	
		else
		{
			response = getRandomResponse();
		}
		
		return response;
	}
	
	/**
	 * Take a statement with "I need help <something>." and transform it into 
	 * "I can help you with <something>!"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformINeedHelpStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I need help", 0);
		String restOfStatement = statement.substring(psn + 11).trim();
		return "I can help you with " + restOfStatement + "!";
	}

	
	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "You can have  <something> if you work hard!"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "You can have " + restOfStatement + " if you work hard!";
	}
	
	

	
	
	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal,
			int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
											// letter
					&& ((after.compareTo("a") < 0) || (after
							.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (emotion == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{	
			return randomAnnoyedResponses [r.nextInt(randomAnnoyedResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	
	private String [] randomNeutralResponses = {"Do you think a packing song exist? We should make one!",
												"I didn't understand that bud. Try again!",
												"You're doing great!",
												"Pardon?",
												"How many socks are too many?",
												"It would be great if we could shrink and unshrink suitcases with superpowers so we can bring everything.",
												"Don't forget that you can't bring things like scissors in a carry on so put those in the suitcase to check in!!"
												};
	private String [] randomAnnoyedResponses = {"Don't be a procrastinator.", 
											"Your suitcase is crying",
											"Did you know that the negatively emotional I get, the faster my battery drains?", 
											"Yes and ...",
											"Kaboom. I am not amused.",
											"I'm not so sure about your life choices, kid...",
											"You make me stressed. My battery's going to explode.",
											">:(( blah",
											};
	
	private String [] randomHappyResponses = {"You're on the right track, fellow!", 
											"I'm proud of u bb.", 
											"I gotchuu!! You can trust me.",
											"Laa Dee Daa DUUUM,,, I'm just humming a tune to make you feel better.",
											"Oo, that's a nice top. You should definitely bring it!",
											"I'm so jealous that you're going on vacation!",
											":D hehehehehhe",
											"",
											};
private String [] randomForgetResponses =	{
											"I can't read your mind unfortunately.",
											"Weak memory is a sign of lack of sleep",
											"Eat spaghetti to forgetti your regretti.",
											"",
											"",
											"",
											};
private String [] randomQuitResponses =	{
											"You're going to forget important things like those duck underwear you have.",
											"Don't waste any time. Come on now.",
											"Don't come crying to me later if you barely have any time to pack >:(",
											"",
											"",
											"",
		};

}
