package net.infinitycoding.carsim;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyCanvas extends Canvas
{
	long delta = 0;
    long last = 0;
    public long fps = 10;
	private BufferStrategy strategy;
	public Graphics2D bkG;
	
	public MyCanvas(int width, int height, Image background2, final CarSim carSim)
	{
		init();
		this.setBounds(0, 0, width, height);
		this.setBackground(Color.WHITE);
		
//		JLabel bgImg = new JLabel(background);
//		bgImg.setBounds(0, 0, width, height);
//		bgImg.setBorder(null);
		
//		this.add(bgImg);f
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
		
		
	}

	public synchronized void flip() {
		this.bkG.dispose();
		strategy.show();
		Toolkit.getDefaultToolkit().sync();
		
		if (last != 0)
		{
			delta = System.nanoTime() - last;
	        last = System.nanoTime();
	        fps = ((long) 1E9) / delta;
	        
	        if (fps == 0)
	        {
	        	fps = 1;
	        }
	        
	        //System.out.println(fps + " FPS");
		}
		
	}


	public synchronized void startDraw() {
		
		this.bkG = (Graphics2D) strategy.getDrawGraphics();
		this.bkG.fillRect(0, 0, getWidth(), getHeight());
		
	}

	public synchronized void start() {
		this.createBufferStrategy(2);
		strategy = this.getBufferStrategy(); 
	}
}
