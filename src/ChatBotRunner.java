import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Jacky Chen 
 * 			Mamadou Diallo
 * 			Sophia Qiu
 * 			Vivian Yee
 * @version October - November 2017
 */
public class ChatBotRunner
{

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args)
	{
		ChatBotQiu chatbot1 = new ChatBotQiu();
		ChatBotYee chatbot2 = new ChatBotYee();
		ChatBotDiallo chatbot3 = new ChatBotDiallo();
		ChatBotChen chatbot4 = new ChatBotChen();
		
		System.out.println("Hello!! Welcome to VacaBot, where we will help you get ready for that vacation you have ahead of you! What would you like help with?\n" + "\t Type 1 to get help with packing!\n" 
				+ "\t Type 2 to get help planning what places to visit!\n" 
				+ "\t Type 3 to get help with the financial aspects of the trip!\n" 
				+ "\t Type 4 to get help with !\n" 
				+ "Type " 
				+ "\"bye\" " 
				+ "to if you don't need any more help!\n"
				);
		
		
		Scanner topic = new Scanner(System.in);
		String user = topic.nextLine();
		
		
		if (user.equals("1")) {
		System.out.println (chatbot1.getGreeting());
		String statement = topic.nextLine();

		while (!statement.toLowerCase().equals("bybyee"))
		{
			System.out.println (chatbot1.getResponse(statement));
			statement = topic.nextLine();
		}
		}
		
		
		if (user.equals("2")) {
			System.out.println (chatbot2.getGreeting());
			String statement = topic.nextLine();

			while (!statement.toLowerCase().equals("bye"))
			{
				System.out.println (chatbot2.getResponse(statement));
				statement = topic.nextLine();
			}
			}
		
		
		if (user.equals("3")) {
			System.out.println (chatbot3.getGreeting());
			String statement = topic.nextLine();

			while (!statement.toLowerCase().equals("bye"))
			{
				System.out.println (chatbot3.getResponse(statement));
				statement = topic.nextLine();
			}
			}
		
		
		if (user.equals("4")) {
			System.out.println (chatbot1.getGreeting());
			String statement = topic.nextLine();

			while (!statement.toLowerCase().equals("bye"))
			{
				System.out.println (chatbot4.getResponse(statement));
				statement = topic.nextLine();
			}
			}
		

		
		if (user.toLowerCase().equals("bye")) {
			System.out.println ("Enjoy the rest of your day!");
			String statement = topic.nextLine();

			}
	
		
	
		
	}

}