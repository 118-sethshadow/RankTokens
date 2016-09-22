
//a rank class that takes on the value of a token, and how many times it was used
	public class Rank implements Comparable<Rank>
	{
		String token;
		Integer count;
		
		public Rank(String t, int c)
		{
			token = t;
			count = c;
		}
		public String word()
		{
			return token;
		}
		public Integer appearance()
		{
			return count;
		}
		
		public int compareTo(Rank whatever) {
			String value = whatever.word();
			if(token.compareTo(value) < 0)
				return -1;
			else if(token.compareTo(value) > 0)
				return 1;
			return 0;
		}
		
	}//end class Rank
