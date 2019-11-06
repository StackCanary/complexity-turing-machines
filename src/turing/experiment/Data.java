package turing.experiment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import turing.machine.Machine;
import turing.machine.MachineFactory;

public abstract class Data {

	Machine machine;
	
	public Data() throws IOException
	{
		this.machine = MachineFactory.make(new File(getMachine())); machine.reset("");
	}
	
	public abstract int getN();
	
	public abstract int getR();
	
	public abstract String getMachine();
	
	public abstract String generate(int n);
	
	public double repeat(int n, int repeats)
	{
		
		List<Long> list = new ArrayList<Long>();
	
		for (int j = 0; j < repeats; j++)
		{
			String input = generate(n);
			
			this.machine.reset(input);

			long count = 0;

			while(this.machine.step())
			{				
				count++;
			}
			
			list.add(count);
		}

		return list.stream().mapToLong(x -> x).average().getAsDouble();
	}
	
	public List<Double> run(int n, int repeats)
	{
		List<Double> list = new ArrayList<Double>();
		
		for (int i = 1; i <= n; i++)
		{
			System.out.println(i);
			list.add(repeat(i, repeats));
		}
		
		return list;
	}
	
	public static void main(String ... strings)
	{
		
		ArrayList<Data> data = new ArrayList<Data>();
		
		try {
			
			// data.add(new Palindrome());
			data.add(new Subtractomatic());
			// data.add(new Bubble());
			// data.add(new Square());
			
			for (Data d : data)
			{
				
				List<Double> results = d.run(d.getN(), d.getR());
				
				System.out.println(results);
				
				PrintWriter pWriter = new PrintWriter(new File(d.toString()));
				
				pWriter.println("n" + "," + "steps");
				
				int i = 1;
				for (Double result : results)
				{
					pWriter.println(i + "," + result);
					i++;
				}
				
				pWriter.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
