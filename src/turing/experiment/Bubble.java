package turing.experiment;

import java.io.IOException;
import java.util.Random;

public class Bubble extends Data {
	
	Random random = new Random();

	public Bubble() throws IOException
	{
		super();
	}

	@Override
	public String getMachine() 
	{
		return "machines/bubblesort.txt";
	}

	@Override
	public String generate(int n) 
	{
		StringBuilder sb = new StringBuilder();
		
		char[] chars = {'0', '1', '2'};
		
		for (int i = 0; i < n; i++)
			sb.append(chars[random.nextInt(3)]);
		
		return sb.toString();
	}

	@Override
	public int getN() {
		return 500;
	}

	@Override
	public int getR() {
		return 10;
	}
	
	@Override
	public String toString() {
		return "bubble.csv";
	}
		
}
