package net.infinitycoding.carsim;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Canvas extends JPanel
{
	public Canvas(int width, int height, ImageIcon background)
	{
		this.setBounds(0, 0, width, height);
		this.setBackground(Color.WHITE);
		
		JLabel bgImg = new JLabel(background);
		bgImg.setBounds(0, 0, width, height);
		bgImg.setBorder(null);
		
		this.add(bgImg);
		this.setVisible(true);
	}
}
