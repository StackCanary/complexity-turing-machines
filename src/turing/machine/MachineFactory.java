package turing.machine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import turing.machine.Out.Act;

public class MachineFactory {
	
	public static Machine make(File file) throws IOException
	{
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		Machine machine = new Machine();

		int states = Integer.parseInt(bReader.readLine().split("\\s+")[1]);
		
		HashMap<String, Stt> map = new HashMap<String, Stt>();
		
		for (int i = 0; i < states; i++)
		{
			String[] state = bReader.readLine().split("\\s+");
			
			Stt stt = new Stt(state[0]);
			
			if (i == 0)
				machine.setQ0(stt);
			
			machine.states.add(stt);
			
			if (state.length > 1)
				machine.accept.add(stt);
			
			map.put(state[0], stt);
		}
		
		
		// skip
		bReader.readLine();
		
		String next = null;
		while((next = bReader.readLine()) != null)
		{
			String[] tuple = next.split("\\s+");
			
			if (tuple.length == 3)
				map.get(tuple[0]).add(map.get(tuple[2]));
			else
				map.get(tuple[0]).add(new Sym(tuple[1]), new Out(map.get(tuple[2]), new Sym(tuple[3]), getAct(tuple[4])));
		}
		
//		machine.tape = new Tape(input);
		
		bReader.close();
		
		return machine;
	}
	
	public static Act getAct(String act)
	{
		if (act.equals("L"))
			return Act.L;
		
		if (act.equals("R"))
			return Act.R;

		return Act.S;
	}


}
