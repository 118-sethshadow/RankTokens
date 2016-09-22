
/*
 * Programmer: Kartikeya Kaushal
 * Class: CS 202 UIC
 * Instructor: Berger-Wolf
 * Project 1: Tokenizing
 */



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class Tokening {
	


	//the scanner to read files
	public static Scanner splicer (String fName) throws FileNotFoundException {
		  return new Scanner (new FileInputStream(fName));
		}
	
	// the printstream to write files
	public static PrintStream writer(String fName) throws FileNotFoundException {
	  return new PrintStream(new FileOutputStream(fName));
	}
	
	//lists that hold only unique tokens
	OrderedCollection<Rank> store = new OrderedCollection<Rank>();
	ArrayList<String> placeHolder = new ArrayList<String>();

	//lists that hold all the tokens
	OrderedCollection<Rank> total = new OrderedCollection<Rank>();
	ArrayList<String> sum = new ArrayList<String>();

	
	/************************************the main method****************************************************/
	static public void main(String args [])
	{
		//ensure the file was inputed on the command line
		if(args.length != 3)
		{
			System.out.println ("Specify the files!");
			System.exit(0);
		}
		//setup the scanner
		Scanner checker = null;
		PrintStream tokenCheck = null;
		
		Tokening reading = new Tokening();
		try
		{
			//start reading the input file
			checker = splicer (args[0]);
			reading.scanForTokens (checker, args[0]);
			//write the list of the unique tokens
			tokenCheck = writer(args[1]);
			reading.printTokens(tokenCheck);
			checker.close();
			  //setup for top 5
			tokenCheck.close();
			tokenCheck = writer(args[2]);

			//make the top 5 list
			reading.makeThe5(tokenCheck);			
			tokenCheck.close();
			
		}
		catch(FileNotFoundException e) {
	        System.out.println("Can't find the input file");
	        System.exit(0);
	    }
		
	}//end main
	
	
	/**********************************read the file, organize the tokens, and write the list**********************************************************/

	//reads the file and separates tokens
	public void scanForTokens(Scanner tokenizer, String wordFile)
	{
		try {
			//set up the scanner and the delimiters for the scanner
			tokenizer = new Scanner (new FileInputStream(wordFile));
			tokenizer.useDelimiter(("[\\W]+"));
			

			//read the file and separate tokens
			while(tokenizer.hasNext())
			{
				String token = tokenizer.next().toLowerCase();
				
					//add tokens to the arraylists and orderedCollections
					placeHolder.add(token);
					sum.add(token);
					//remove doubles of tokens from the arraylist
					dropDoubles(placeHolder);
					//order the arraylist with all tokens (with doubles)
					setInOrder(sum);
				
				
			}//end while
				//System.out.println(placeHolder);
			//close the scanner
			tokenizer.close();
			
			//put the unique tokens in the OrderedCollection
			for(int i = 0; i < placeHolder.size() - 1; i++)
			{				
				char[] spread = placeHolder.get(i).toCharArray();

				Rank putIn = new Rank(placeHolder.get(i), 0);
				store.insert(putIn);
			}//end for i
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//end scanForTokens
	
	//remove any doubles of a token in the ArrayList
			public void dropDoubles(ArrayList<String> storage)
			{
				for (int i = storage.size() - 1; i > 0; i--)
				{
						//the token to compare to
					String compare = storage.get(i - 1);
						//the most recently added token
					String token = storage.get (storage.size() - 1);
						if(token.compareTo(compare)==0)
						{
							storage.remove(storage.size() - 1);
						}
						//System.out.println(compare);
						//System.out.println(token);
					}
				}//end DropDoubles
			
			
			//put an ArrayList of strings in order
			public void setInOrder (ArrayList<String> values)
			{
				for (int i = values.size() - 1; i > 0; i--)
				{
					
					//the token to compare to
				String compare = values.get(i - 1);
				char[] com = compare.toCharArray();
					//the most recently added token
				String token = values.get (i);
				char[] tokener = token.toCharArray();
				
					   //order the tokens	
					for(int j = 0; j < compare.length() && j < token.length(); j++)
					{

						//compare the ASCII values to order the words
						int compASCII = (int)com[j];
						int tokASCII= (int)tokener[j];
					
						//swap if needed
						if(tokASCII < compASCII)
						{
							values.set(i - 1, token);
							values.set(i, compare);
							break;
						}
						//swap if the word is within the compared word
						else if (tokASCII == compASCII && compare.startsWith(token))
						{
							values.set(i - 1, token);
							values.set(i, compare);
							break;
						}
						//check the next word
						else if(tokASCII > compASCII)
							break;					

				}//end for j
					
				}//end for i
			}//end setInOrder
			
			//print out the unique tokens in alphanumerical order
			public void printTokens(PrintStream writer)
			{
				//findMin will remove an object each time so once it is empty, stop printing
				while(!store.isEmpty())
				{
					writer.println(store.findMin().word());
					store.remove(store.findMin());
				}
			}//end printTokens
	
			
			/*****************************************************find the top 5 tokens with a random starting letter***********************************************************/
	//get a random letter, then find all tokens that start with the letter
		public ArrayList<String> findRightTokens(ArrayList<String> total)
		{
			//get the random letter
			Random number = new Random ();
			int ASCII = number.nextInt(26) + 97;
			ArrayList<String> selected = new ArrayList<String>();	
			//convert the int to a String with ASCII conversion
			String start = new Character((char)ASCII).toString(); 	
			  //show the random letter
				System.out.println("Random letter: " + start);

				//find all tokens that start with "start"
			for (int i = 0; i <= total.size() - 1; i++)
			{
				String token = total.get(i);
				if(token.startsWith(start))
				selected.add(token);
			}
			
			
			return selected;
		}//end findRightTokens	

		 //check how many times a token is used and put the unique tokens and their rank in a new list using the Rank class
		public ArrayList<Rank> getTheResults (ArrayList<String> selected)
		{
			ArrayList<Rank> given = new ArrayList<Rank>();
			
			while (selected.isEmpty() == false)
			{
				String token = selected.get(0);
				int counter = 1;
				
				while (selected.size() > 1)
				{
					if(token.compareTo(selected.get(1)) == 0)
					{
						counter++;
						selected.remove(selected.get(1));
					}
					else
						break;
				}

				given.add(new Rank(token, counter));
				
				selected.remove(0);
				//arrange the list as it is being made
				top5(given);			
			}
		//	System.out.println(given.size());

			return given;
		}//end getTheResults
		
		//arrange the tokens in order by rank as they are being selected
		public ArrayList<Rank> top5 (ArrayList<Rank> given)
		{	
			for(int i = given.size() - 1; i > 0 ; i--)
			{
				//most recently added Rank
				Rank first = given.get(i);
				int one = first.appearance();
				
				//loop is for break purpose inside it
				for(int j = given.size() - 1; j > 0; j--)
				{
					// the rank to compare to
					Rank second = given.get(i - 1);
					int two = second.appearance();
					
					if(one > two)
					{
						given.set(i - 1, first);
						given.set(i, second);
						break;
					}
					else
						break;

				}//end for j
				
			}//end for i
				
				return given;
		}//end top5
		
		//make the output file to show the top 5 tokens
		public void makeThe5(PrintStream writer)
		{
			ArrayList <Rank> lastOne = getTheResults(findRightTokens(sum));
			if(lastOne.isEmpty())
			{
				System.out.println("No tokens to rank");
				System.exit(0);
			}
			for(int i = 0; i <= lastOne.size() - 1; i ++)
			{
				Rank judging = lastOne.get(i);
				String highToken = judging.word();
				writer.println(highToken);
				if(i == 4)
					break;
			}

		}//end makeThe5
}//end Tokening class
