package turing.machine;

public class MiniMachine 
{
	public Tape tape; public Stt current; 
	
	public boolean halted = false;
	
	public MiniMachine(Tape tape, Stt current)
	{
		this.tape = new Tape(tape); this.current = current;
	}
	
	public MiniMachine(MiniMachine machine)
	{
		this.tape = new Tape(machine.tape); this.current = machine.current;
	}
	
	public boolean isHalted()
	{
		return halted;
	}
	
//	public boolean step()
//	{
//		
//		Out out = current.delta(tape.scan());
//		
//		if (out == null)
//			return false;
//		
//		current = out.stt; tape.move(out);
//		
//		return true;
//	}
	
	
	public void tick(Out out)
	{
		current = out.stt; tape.move(out);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof MiniMachine)
		{
			MiniMachine m = (MiniMachine) obj;
			
			return this.tape.equals(m.tape) && this.current.equals(m.current);
		}
		
		return false;
	}
	
	
	@Override
	public String toString() {
		return "(" + tape.toString() + ", " + this.current + ")";
	}
}
