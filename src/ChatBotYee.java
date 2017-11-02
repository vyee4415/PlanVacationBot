import java.util.Random;

/**
 * Vacation bot
 * Vivian Yee
 * Period 2
 */
public class ChatBotYee
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int frustration = 0;
	int places = 0;
	int and = 0;
	String x="";
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hi, what's good dood? I'm a pick a nice vacation bot. Give me some countries you want to go to and I'll say if it's a nice place to go to or not!";
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
			response = "Say something!!";
		}

// bad places that chatbot does not agree with
		else if (findKeyword(statement, "China") >= 0)
		{
			frustration++;
			return randomChinaResponses [r.nextInt(randomChinaResponses.length)];
		}
		else if (findKeyword(statement, "Mexico") >= 0)
		{
			frustration++;
			return randomMexicoResponses [r.nextInt(randomMexicoResponses.length)];
		}
		else if (findKeyword(statement, "Spain") >= 0)
		{
			frustration++;
			return randomSpainResponses [r.nextInt(randomSpainResponses.length)];
		}
		
// good places that chatbot agrees with
		else if (findKeyword(statement, "Russia") >=0)
		{
			frustration--;
			if(and>=1) {
				x+=" and Russia";
			}else {
				x+=" Russia";
			}
			and++;
			return randomRussiaResponses [r.nextInt(randomRussiaResponses.length)];
		}
		else if (findKeyword(statement, "Italy") >=0)
		{
			frustration--;
			if(and>=1) {
				x+=" and Italy";
			}else {
				x+=" Italy";
			}
			and++;
			return randomItalyResponses [r.nextInt(randomItalyResponses.length)];
		}
		else if (findKeyword(statement, "Japan") >=0)
		{
			frustration--;
			if(and>=1) {
				x+=" and Japan";
			}else {
				x+=" Japan";
			}
			and++;
			return randomJapanResponses [r.nextInt(randomJapanResponses.length)];
		}
		
		else if(findKeyword(statement, "hello") >=0)
		{
			response = "Hello! Do you want to tell me what places you want to visit?";
		}
		
		else if(findKeyword(statement,"because")>=0)
		{
			response = "Oh that's so cool!";
		}
		// Response transforming I want to statement
		else if (findKeyword(statement, "I want to visit", 0) >= 0)
		{
			places++;
			response = transformIWantToStatement(statement);
		}
		else
		{
			response = getRandomResponse();
		}
		
		return response;
	}
	
	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "Why do you want to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
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
		int psn = findKeyword (statement, "I want to visit", 0);
		String restOfStatement = statement.substring(psn + 15).trim();
		frustration--;
		if(and>=1) {
			x+=" and "+ restOfStatement;
		}else {
			x+=restOfStatement;
		}
		and++;
		return "Why do you want to visit " + restOfStatement + "?";
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
	private int findKeyword(String statement, String goal, int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure theoal isn't part of a
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
		if ((frustration == 0)&&(places == 0))
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if(frustration == 0) {
			return "I like your choices to visit " + x + ". Tell me more!";
		}
		if (frustration > 0)
		{	
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	
	private String [] randomNeutralResponses = {"Tell me the places you want to visit",
			"Could you say that again? Couldn't hear you.",
	};
	private String [] randomAngryResponses = {"I don't reccomend your places to visit, give me more", "Hmph, I like my cold countries like Antartica..", "Can you tell me an actual nice place to go?"};
	private String [] randomHappyResponses = {"We have a lot in common!", "You have a good taste in countries, tell me more", "I think you have a good list of where you want to go, where else?"};
	
	private String [] randomChinaResponses = {"I personally don't like China...","Great wall, more like lame wall. hehe","What's so good about China, there's so much pollution!!"};
	private String [] randomMexicoResponses = {"Uhh... Too hot for my taste","I have remember bad experiences in Mexico...","No me gusta Mexico"};
	private String [] randomSpainResponses = {"I remember Spain, it was lame.","Nope, don't reccomend."};
	
	private String [] randomRussiaResponses = {"OHH RUSSIA I LOVE RUSSIA!","ALTHOUGH I'VE NEVER BEEN TO RUSSIA I DREAM IT EVERY NIGHT!","RUSSIA!! <33"};
	private String [] randomItalyResponses = {"PIZZA PLACE!","MARIO!","LEANING TOWER OF PIZZA! <3"};
	private String [] randomJapanResponses = {"I LOVE JAPAN CULTURE!","THE CHERRY BLOSSOMS IN JAPAN ARE SO PRETTY!",""};
}



