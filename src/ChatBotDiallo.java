import java.util.Random;
import java.util.Scanner;
public class ChatBotDiallo
{
	Scanner SC = new Scanner(System.in);
	private int emotion;
	public ChatBotDiallo()
	{
		int emotion= 0;
	}
	public String getGreeting()
	{
return "Hello there, my name is Ocirun, I will be your budget planner for this..." + "........" + "hahahahaha I can't believe you actually let me ramble on for that long. What’s your name?";
	}	
	public String getResponse(String statement)
	{
			if (statement.length() == 0);
			{
				emotion--;
			}
			if (statement.length() == 0 && emotion < 0)
			{
				return("I know you ain’t just ignore me. Answer!");
			}
			if (statement.length() == 0 && emotion > 0)
			{ 
				return("You're like the g in lasagna right now. Respond buddy o' pal of mine.");
			}
			if (statement.length() == 0 && emotion == 0)
			{ 
				return("Hello...Is you living?");
			}
			else 
			{
				return "Nice to meet you. What is your name?";
				String name = SC.nextLine();
				name = name.substring(11, name.length());
				emotion++;
			}
		return("Hello " + name + ", if you’ve already spoken to the other parts of the program, you should know where you’re going. How much does your trip cost? Please input a double.");
		
		return("I didn't quite get that. Come again?");
		}
	


// Methods
public int findKeyword(String statement, String goal, int startPos)
{
	String phrase = statement.trim().toLowerCase();
	goal = goal.toLowerCase();
	int psn = phrase.indexOf(goal, startPos);
	while (psn >= 0)
	{
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
		if (((before.compareTo("a") < 0) || (before
				.compareTo("z") > 0)) // before is not a
										// letter
				&& ((after.compareTo("a") < 0) || (after
						.compareTo("z") > 0)))
		{
			return psn;
		}
		psn = phrase.indexOf(goal, psn + 1);
	}
	return -1;
}
}