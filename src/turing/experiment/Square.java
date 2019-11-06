package turing.experiment;

import java.io.IOException;

public class Square extends Data {

	public Square() throws IOException
	{
		super();
	}

	@Override
	public String getMachine() 
	{
		return "machines/square0s.txt";
	}

	@Override
	public String generate(int n) 
	{
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n ;i++)
			sb.append("0");

		return sb.toString();
	}

	@Override
	public int getN() {
		return 800;
	}

	@Override
	public int getR() {
		return 1;
	}

	@Override
	public String toString() {
		return "square0s.csv";
	}
}
