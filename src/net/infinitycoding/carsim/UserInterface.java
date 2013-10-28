package net.infinitycoding.carsim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.infinitycoding.carsim.modules.Car;
import net.infinitycoding.carsim.modules.Street;

public class UserInterface extends JFrame
{	
	MyCanvas canvas;
	private ImageIcon background;
	private Graphics graphic;
	public Dimension d;
	private Image offscreen;
	private Graphics offgc;
	private JPanel JPanel;
	
	public UserInterface(ImageIcon background, CarSim carSim)
	{
		super();
		
		this.background = background;
		
		this.setBounds(0, 0, 1280, 1024);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("CarSimulator by InfinityCoding");
		this.setLayout(new GridLayout(1, 1));
		this.setBackground(Color.WHITE);
		
		this.JPanel = new JPanel();
		
		this.canvas = new MyCanvas(1280, 1024, background,carSim);
		
		this.JPanel.add(this.canvas);
		
		this.setContentPane(this.JPanel);
		this.setVisible(true);
		
		this.canvas.start();
		
		
		
	}

	public void checkCollision()
	{
		// TODO Auto-generated method stub
	}

	public void drawCars(ArrayList<Car> cars)
	{
		for(Car elem : cars)
		{
			this.canvas.bkG.drawImage(elem.picture, elem.x, elem.y, null);
		}
	}

	public void drawLights(HashMap<Integer, Street> streets) {
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
	
}
