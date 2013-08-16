package net.infinitycoding.carsim.testing;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class CustomPanel extends JPanel {

	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Rectangle r1 = new Rectangle(20, 50);
		g.drawRect(20, 20, r1.width, r1.height);
		g.drawRect(10, 40, r1.width, r1.height);
	}
	
}
