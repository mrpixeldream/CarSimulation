package net.infinitycoding.carsim;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Canvas extends JPanel
{
	public Canvas(int width, int height, ImageIcon background, final CarSim carSim)
	{
		this.setBounds(0, 0, width, height);
		this.setBackground(Color.WHITE);
		
		JLabel bgImg = new JLabel(background);
		bgImg.setBounds(0, 0, width, height);
		bgImg.setBorder(null);
		
		this.add(bgImg);
		this.setVisible(true);
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				carSim.onClick(e.getX(), e.getY());
			}
		});
	}
}
