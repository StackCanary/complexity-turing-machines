package turing.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JPanel;

import turing.graph.Simulation;
import turing.machine.Machine;
import turing.machine.Out;
import turing.machine.Stt;
import turing.machine.Sym;

public class StatePanel extends JPanel {
	private static final long serialVersionUID = 1L;


	Machine machine;

	private Point p;
	private Point c = new Point(0, 0);

	final int radius = 20;

	public StatePanel(Machine machine, Simulation simulation, int w, int h)
	{
		this.machine = machine;

		setBackground(Color.LIGHT_GRAY);

		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				p = e.getPoint();

				repaint();
				revalidate();
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				int dx = e.getX() - p.x;
				int dy = e.getY() - p.y;

				p = e.getPoint();

				c.setLocation(c.x + dx, c.y + dy);

				repaint();	
				revalidate();
			}
		});
	}

	public void drawNodes(Graphics g)
	{
		int inRadius = radius;

		for (Stt v : machine.states)
		{

			if (machine.accept.contains(v))
			{
				inRadius += 5;
				g.setColor(Color.DARK_GRAY);
				g.fillOval((int) v.pos.x - inRadius, (int) v.pos.y - inRadius, inRadius * 2, inRadius * 2);
				g.setColor(Color.BLUE);
				inRadius -= 5;

				inRadius += 3;
				g.setColor(Color.PINK);
				g.fillOval((int) v.pos.x - inRadius, (int) v.pos.y - inRadius, inRadius * 2, inRadius * 2);
				g.setColor(Color.BLUE);
				inRadius -= 3;
			}

			if (machine.isCurrent(v))
				g.setColor(Color.CYAN);
			else
				g.setColor(Color.BLUE);

			g.fillOval((int) v.pos.x - inRadius, (int) v.pos.y - inRadius, inRadius * 2, inRadius * 2);

			Font font = new Font("default", Font.ITALIC, 18);

			g.setFont(font);

			g.setColor(Color.PINK);
			Util.drawCenteredString(g, v.state, (int) v.pos.x, (int) v.pos.y);


		}	
	}


	public void drawEdges(Graphics g)
	{
		for (Stt v : machine.states)
		{
			for (Entry<Sym, List<Out>> entry : v.table.entrySet())
			{

				Sym sym = entry.getKey();

				if (sym == null)
					sym = new Sym("\u03B5");
				
				for (Out out : entry.getValue())
				{
					
					Stt u = out.stt;
					
					if (!v.equals(u)) {
						double dx = (u.pos.x - v.pos.x);
						double dy = (u.pos.y - v.pos.y);
						double sz = Math.sqrt(dx * dx + dy * dy);
						
						double nx = dx / sz;
						double ny = dy / sz;
						
						int x1 = (int) (v.pos.x + radius * nx);
						int y1 = (int) (v.pos.y + radius * ny);
						int x2 = (int) (u.pos.x - radius * nx);
						int y2 = (int) (u.pos.y - radius * ny);
						
						double ctrlx = v.pos.x + dx / 4; 
						double ctrly = v.pos.y + dy / 4;
						
						g.drawLine(x1, y1, x2, y2);
						
						g.setColor(Color.GREEN);
						final int rad1 = 10;
						g.fillOval(x1 - rad1, y1 - rad1, rad1 * 2, rad1 * 2);
						
						g.setColor(Color.RED);
						final int rad2 = 8;
						g.fillOval(x2 - rad2, y2 - rad2, rad2 * 2, rad2 * 2);
						
						g.setColor(Color.BLACK);
						Font font = new Font("default", Font.HANGING_BASELINE, 20);
						g.setFont(font);
						Util.drawCenteredString(g, sym.toString() + ", " + out.act, (int) ctrlx, (int) ctrly);
						
					} else {
						g.setColor(Color.BLACK);
						g.drawOval((int) v.pos.x, (int) v.pos.y, 50, 20);
						g.drawString(sym.toString(), (int) (v.pos.x + 50), (int) (v.pos.y + 20));
					}
					
				}

			}	

		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		((Graphics2D) g).translate(c.x, c.y);


		drawEdges(g);
		drawNodes(g);

	}

}
