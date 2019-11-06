package turing.gui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import turing.machine.Machine;
import turing.machine.MiniMachine;

public class TTapePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	Machine machine;
	
	private Point p;
	private Point c = new Point(0, 0);
	
	public TTapePanel(Machine machine)
	{
		this.machine = machine;
		
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
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		((Graphics2D) g).translate(c.x, c.y);
		
		g.setFont(new Font("default", Font.BOLD, 24));
		
		final int d = 50;
		
		Util.drawCenteredString(g, "Is Accepted? " + machine.isAccepted(), getWidth() / 2, getHeight()/2 - d);
		
		int i = 0;

		for (MiniMachine machine : machine.machines)
		{
			Util.drawCenteredString(g, machine.tape.p + " " + machine.tape.rtape.toString(), getWidth() / 2, getHeight()/2 + d * i);
			i++;
		}
			
		
	}
	
	
}
