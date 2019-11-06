package turing.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stt 
{
	public String state; public HashMap<Sym, List<Out>> table = new HashMap<Sym, List<Out>>();
	
	public Vec dis = new Vec(0, 0);
	public Vec pos = new Vec(0, 0);
	
	public Stt(String state2)
	{
		this.state = state2;
	}
	
	public void add(Sym sym, Out out)
	{
		if (this.table.get(sym) == null)
			this.table.put(sym, new ArrayList<Out>());
		
		this.table.get(sym).add(out);
	}
	
	public void add(Stt stt)
	{
		
		if (this.table.get(null) == null)
			this.table.put(null, new ArrayList<Out>());
		
		this.table.get(null).add(new Out(stt));
	}
	
	public List<Out> delta(Sym sym)
	{
		return this.table.get(sym);
	}
	
	@Override
	public int hashCode() 
	{
		return state.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (obj instanceof Stt)
			return ((Stt) obj).state.equals(this.state);
			
		return false;
	}
	
	
	@Override
	public String toString() {
		return state;
	}
	

}
