package turing.machine;

import java.util.ArrayList;

public class Tape 
{

	public ArrayList<Sym> rtape = new ArrayList<Sym>();
	public ArrayList<Sym> ltape = new ArrayList<Sym>();
	
	public int p = 0;
	
	public Tape(String input)
	{
		rtape.add(new Sym("_"));
		
		if (input.length() > 0)
		{
			rtape.remove(0);
			
			String[] characters = input.split("");
			
			for (String character : characters)
				rtape.add(new Sym(character));
		}
		
	}
	
	public Tape(Tape tape)
	{
		this.p = tape.p;
		this.ltape = new ArrayList<Sym>(tape.ltape);
		this.rtape = new ArrayList<Sym>(tape.rtape);
	}
	
	public void move(Out out)
	{
		
		if (p < 0)
			ltape.set(-p - 1, out.sym);
		else
			rtape.set(p, out.sym);
		
		switch(out.act)
		{
		case L:
			p--;
			if (-p - 1 == ltape.size())
				ltape.add(new Sym("_"));
			break;
		case R:
			p++;
			if (p == rtape.size())
				rtape.add(new Sym("_"));
			break;
		case S:
			break;
		}
		
	}
	
	public Sym scan()
	{
		
		if (p < 0)
			return this.ltape.get(-p - 1);
		
		return this.rtape.get(p);
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (obj instanceof Tape)
		{
			Tape t = (Tape) obj;
			
			return this.ltape.equals(t.ltape) && this.rtape.equals(t.rtape) && this.p == t.p;
			
		}
		
		
		return false;
	}
	
	@Override
	public String toString() {
		return rtape.toString();
	}
}
