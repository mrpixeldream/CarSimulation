package net.infinitycoding.carsim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Canvas extends JPanel
{
	long delta = 0;
    long last = 0;
    long fps = 10;
	
	public Canvas(int width, int height, ImageIcon background, final CarSim carSim)
	{
		init();
		this.setDoubleBuffered(true);
		this.setBounds(0, 0, width, height);
		this.setBackground(Color.WHITE);
		
//		JLabel bgImg = new JLabel(background);
//		bgImg.setBounds(0, 0, width, height);
//		bgImg.setBorder(null);
		
//		this.add(bgImg);
		this.setVisible(true);
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				carSim.onClick(e.getX(), e.getY());
			}
		});
	}
	
	public void init()
	{
		last = System.nanoTime();
        System.out.println(last);
	}
	
	@Override
	public void repaint()
	{
		super.repaint();
		
		if (last != 0)
		{
			delta = System.nanoTime() - last;
	        last = System.nanoTime();
	        fps = ((long) 1E9) / delta;
	        
	        if (fps == 0)
	        {
	        	fps = 1;
	        }
	        
	        System.out.println(fps + " FPS");
		}
	}
}
