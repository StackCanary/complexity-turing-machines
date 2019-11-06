package turing.machine;

public class Sym
{
	
	String sym;
	
	public Sym(String sym)
	{
		this.sym = sym;
	}
	
	@Override
	public int hashCode() {
		return this.sym.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Sym)
			return ((Sym) obj).sym.equals(this.sym);
			
		return false;
	}
	
	@Override
	public String toString() {
		
		if (sym.equals("_"))
			return "\u2423";
		
		return sym;
	}
	
}
