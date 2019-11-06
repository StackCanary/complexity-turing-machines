package turing.graph;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import turing.machine.Out;
import turing.machine.Stt;
import turing.machine.Vec;

public class Simulation {
	
	double a;
	double k;
	
	Collection<Stt> V;
	
	double w;
	double h;
	
	double t;
	
	final double threshold = 1;
	
	public Simulation(double w, double h, Collection<Stt> V)
	{
		a = w * h; k = Math.sqrt(a * V.size()); this.V = V; t = 0.1 * w;
		
		int x = (int) w/2;
		int y = (int) h/2;
		
		
		this.w = w;
		this.h = h;
		
		Random random = new Random();
		
		for (Stt v : V)
		{
			v.pos.x = x + random.nextInt(60) - 30;
			v.pos.y = y + random.nextInt(60) - 30;
			
			x += 10;
		}
	}
	
	public double f_a(double x)
	{
		return 0.29 * (x * x) / k;
	}
	
	public double f_r(double x)
	{
		return 0.0001 * (k * k) / x;
	}
	
	public boolean step()
	{
		
		boolean finished = true;
		
		// Calculate repulsive forces
		for (Stt v : V)
		{
			v.dis = new Vec(0, 0);
			for (Stt u: V)
			{
				if (!v.equals(u))
				{
					Vec delta = v.pos.diff(u.pos);
					
					if (delta.size() < 0.05)
					{
						delta.x = 1;
						delta.y = 1;
					}
					
					double ds = delta.size();
					
					double nx = v.dis.x + (delta.x / ds) * f_r(ds);
					double ny = v.dis.y + (delta.y / ds) * f_r(ds);
					
					v.dis = new Vec(nx, ny);
					
				}
			}
		}
		
		// Calculate attractive forces
		for (Stt v : V)
		{
			
			List<Out> list = v.table.values().stream().flatMap(x -> x.stream()).collect(Collectors.toList());
			
			for (Out o : list)
			{
				Stt u = o.stt;
				
				if (!v.equals(u)) 
				{
					Vec delta = v.pos.diff(u.pos);
					
					if (delta.size() < 0.05)
					{
						delta.x = 1;
						delta.y = 1;
					}
					
					double ds = delta.size();
					
					double vx = v.dis.x - (delta.x / ds) * f_a(ds);
					double vy = v.dis.y - (delta.y / ds) * f_a(ds);
					v.dis = new Vec(vx, vy);

					double ux = u.dis.x + (delta.x / ds) * f_a(ds);
					double uy = u.dis.y + (delta.y / ds) * f_a(ds);
					u.dis = new Vec(ux, uy);
					
				}
			}
			
		}
		
		// Limit maximum displacement to temperature and prevent displacement outside of frame
		
		for (Stt v : V)
		{
			
			if (v.dis.size() > threshold)
				finished = false;
			
			v.pos.x = v.pos.x + v.dis.x;
			v.pos.y = v.pos.y + v.dis.y;

			
			// Keep in frame
			v.pos.x = Math.min(w, Math.max(0.0, v.pos.x));
			v.pos.y = Math.min(h, Math.max(0.0, v.pos.y));
		}
		
		
		// Unused as of now
		t = cool(t);
		
		return finished;
	}
	
	public double cool(double t)
	{
		return 0;
	}

}
