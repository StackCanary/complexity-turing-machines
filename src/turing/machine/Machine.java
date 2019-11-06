package turing.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Machine 
{	

	// Set of States
	// Set of Input alphabet
	// Tape alphabet (super set of input alphabet)
	// Start State
	// Set of Accepting States
	// Transition Function QxR -> QxRxA

	public HashSet<Stt> states = new HashSet<Stt>(); 
	public HashSet<Stt> accept = new HashSet<Stt>();

	public List<MiniMachine> machines = new ArrayList<MiniMachine>();

	Stt q0;

	public void setQ0(Stt q0)
	{
		this.q0 = q0;
	}

	public void reset(String input)
	{
		machines = new ArrayList<MiniMachine>();

		MiniMachine first = new MiniMachine(new Tape(input), q0);
		
		machines.add(first);
		
		prepare();
	}
	
	public boolean isCurrent(Stt stt)
	{
		for (MiniMachine machine : machines)
		{
			if (machine.current.equals(stt))
				return true;
		}
		
		return false;
	}

	public boolean isAccepted()
	{

		for (MiniMachine machine : machines)
		{
			if (machine.isHalted() && accept.contains(machine.current))
				return true;
		}

		return false;
	}
	
	public void epislon()
	{
		
		// follow epsilon transitions
		
		List<MiniMachine> pool = new ArrayList<MiniMachine>();
		
		pool.addAll(machines);
		
		while(!pool.isEmpty())
		{
			List<MiniMachine> add = new ArrayList<MiniMachine>();
			
			for (MiniMachine machine : pool)
			{
				List<Out> outs = machine.current.delta(null);
				
				if (outs == null)
					continue;
				
				for (Out out : outs)
				{
					
					if (!machine.current.equals(out.stt))
					{
						MiniMachine spawn = new MiniMachine(machine.tape, out.stt); add.add(spawn);
					}
					
				}
				
			}
			
			machines.addAll(add);
			pool.clear();
			pool.addAll(add);
		}
	}
	
	// prune halted but not accepted, follow epsilon transitions
	public void prepare()
	{
		
		epislon();
		
		List<MiniMachine> rem = new ArrayList<MiniMachine>();
		List<MiniMachine> add = new ArrayList<MiniMachine>();	
		
		for (MiniMachine machine : machines)
		{
			List<Out> outs = machine.current.delta(machine.tape.scan());

			// Machine can't transition to any states
			if (outs == null || outs.size() == 0)
			{
				machine.halted = true; 
				
				if ( !accept.contains(machine.current) )
					rem.add(machine);
				
				continue;
			}
		}
		

		machines.removeAll(rem); machines.addAll(add);
	}
	
	public void tick()
	{
		List<MiniMachine> rem = new ArrayList<MiniMachine>();
		List<MiniMachine> add = new ArrayList<MiniMachine>();
		
		for (MiniMachine machine : machines)
		{
			List<Out> outs = machine.current.delta(machine.tape.scan());
			
			// For each output, create new machine with (Replaced Symbol, Next State, Movement)
			for (Out out : outs)
			{
				MiniMachine spawn = new MiniMachine(machine); spawn.tick(out); add.add(spawn);
			}

			// delete original machine
			rem.add(machine);
		}

		machines.removeAll(rem); machines.addAll(add);
	}

	public boolean step()
	{

		if (machines.isEmpty() || isAccepted())
			return false;
		
		tick(); prepare();
		
		return true;
	}

}
