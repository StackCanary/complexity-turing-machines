package turing.experiment;

import java.io.IOException;

public class Palindrome extends Data {

	public Palindrome() throws IOException {
		super();
	}

	@Override
	public String getMachine()
	{
		return "machines/palindrome.txt";
	}

	@Override
	public String generate(int n) 
	{
		
		StringBuilder sb = new StringBuilder();
		
		if (n % 2 == 0) {
			
			StringBuilder l = new StringBuilder();
			
			for (int i = 0; i < n / 2; i++)
			{
				l.append(1);
			}
			
			sb.append(l);
			sb.append(l.reverse());
			
		} else {
			
			n = n - 1;
			
			StringBuilder l = new StringBuilder();
			
			for (int i = 0; i < n / 2; i++)
			{
				l.append(1);
			}
			
			sb.append(l);
			sb.append(0);
			sb.append(l.reverse().toString());
		}
		
		return sb.toString();
	}

	@Override
	public int getN() {
		return 1000;
	}

	@Override
	public int getR() {
		return 1;
	}

	@Override
	public String toString() {
		return "palindrome.csv";
	}
	
}
