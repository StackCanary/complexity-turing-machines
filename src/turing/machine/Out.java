package turing.machine;

public class Out {

	public enum Act
	{
		L,
		R,
		S,
	};
	
	public Sym sym;
	public Act act;
	public Stt stt;
	
	boolean eps = false;
	
	public Out(Stt stt, Sym sym, Act act) 
	{
		this.sym = sym;
		this.act = act;
		this.stt = stt;
	}
	
	public Out(Stt stt) 
	{
		this.sym = null; // new Sym("\u03B5");
		this.act = Act.S;
		this.stt = stt; 
		this.eps = true; 
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Out)
		{
			if (eps)
				return  ((Out) obj).stt.equals(this.stt);
			
			return     ((Out) obj).stt.equals(this.stt)
					&& ((Out) obj).sym.equals(this.sym)
					&& ((Out) obj).act.equals(this.act);
		}
			
		return false;
	}
	
	@Override
	public String toString() 
	{
		return "(" + this.stt.toString() + ", " + this.sym.toString() + ", " + this.act + ")";
	}
	
}
