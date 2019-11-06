package turing.experiment;

import java.io.IOException;
import java.math.BigInteger;

public class Subtractomatic extends Data {

	public Subtractomatic() throws IOException
	{
		super();
	}

	@Override
	public String getMachine() 
	{
		return "machines/subtractomatic.txt";
	}

	@Override
	public String generate(int n) 
	{
		StringBuilder sb = new StringBuilder();

		StringBuilder digits = new StringBuilder();
		
		for (int i = 0; i < n / 2; i++)
		{
			digits.append("1");
		}
		
		if (digits.length() == 0)
			digits.append("0");
		
		BigInteger a = new BigInteger(digits.toString(), 2);
		BigInteger b = new BigInteger(digits.toString(), 2);
		
		BigInteger c = a.add(b);
		
		sb.append(a.toString(2));
		sb.append("#");
		sb.append(b.toString(2));
		sb.append("#");
		sb.append(c.toString(2));
		
		return sb.toString();
	}

	@Override
	public int getN() {
		return 50;
	}

	@Override
	public int getR() {
		return 1;
	}
	
	@Override
	public String toString() {
		return "subtractomatic.csv";
	}
		
}
