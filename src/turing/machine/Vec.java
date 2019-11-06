package turing.machine;

public class Vec {

	public double x;
	public double y;
	
	public Vec(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vec diff(Vec vector)
	{
		return new Vec(this.x - vector.x, this.y - vector.y);
	}
	
	public double size()
	{
		return Math.sqrt(x * x + y * y);
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
