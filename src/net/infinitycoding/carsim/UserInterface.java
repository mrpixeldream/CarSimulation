package net.infinitycoding.carsim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.infinitycoding.carsim.modules.Car;
import net.infinitycoding.carsim.modules.Street;

public class UserInterface extends JFrame
{	
	public MyCanvas canvas;
	private Image background;
	public Dimension d;
	private JPanel JPanel;
	private CarSim parent;
	
	public UserInterface(Image background2, CarSim carSim)
	{
		super();
		
		this.parent = carSim;
		
		this.background = background2;
		
		this.setBounds(0, 0, 1280, 1024);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("CarSimulator by InfinityCoding");
		this.setLayout(new GridLayout(1, 1));
		this.setBackground(Color.WHITE);
		
		this.JPanel = new JPanel();
		
		this.canvas = new MyCanvas(1280, 1024, background2,carSim);
		
		this.JPanel.add(this.canvas);
		
		this.setContentPane(this.JPanel);
		this.setVisible(true);
		
		this.canvas.start();
		
		
		
	}

	public void checkCollision()
	{
		// TODO Auto-generated method stub
	}
		
	public void drawPoints(int point)
	{
		this.canvas.bkG.drawString(String.valueOf(point),1024,59);
	}
	
	public synchronized void drawCars(ArrayList<Car> cars) throws IOException
	{
		this.canvas.bkG.drawImage(this.background,0,0,null);
		for(Car elem : cars)
		{
			elem.updatePicture();
			this.canvas.bkG.drawImage(elem.picture, elem.x, elem.y, null);
		}
	}

	public synchronized void drawLights(HashMap<Integer, Street> streets) {
		for(Integer street : streets.keySet())
		{
			if(streets.get(street).trafficLight.getOn())
			{
				this.canvas.bkG.drawImage(streets.get(street).trafficLight.greenLight,streets.get(street).trafficLight.x,streets.get(street).trafficLight.y,null);
			}
			else
			{
				this.canvas.bkG.drawImage(streets.get(street).trafficLight.redLight,streets.get(street).trafficLight.x,streets.get(street).trafficLight.y,null);
			}
			
		}
		
	}
	
	public void drawFPS(long fps)
	{
		if (parent.getJumpedTicks() < 50)
		{
			this.canvas.bkG.drawString("" + fps, 50, 50);
		}
	}
	
}
