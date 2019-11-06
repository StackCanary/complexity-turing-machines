package turing.gui;

import java.awt.FontMetrics;
import java.awt.Graphics;

public class Util 
{
	
	public static void drawCenteredString(Graphics g, String str, int x, int y)
	{
		FontMetrics fm = g.getFontMetrics();

		int x_ = x - fm.stringWidth(str) / 2;
		int y_ = y - fm.getHeight() / 2 + fm.getAscent();
		
		g.drawString(str, x_, y_);
	}
	
}
