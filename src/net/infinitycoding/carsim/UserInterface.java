package net.infinitycoding.carsim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import net.infinitycoding.carsim.modules.Car;
import net.infinitycoding.carsim.modules.Level;
import net.infinitycoding.carsim.modules.Street;

public class UserInterface extends JFrame
{	
	Canvas canvas;
	private ImageIcon background;
	private Graphics graphic;
	public Dimension d;
	private Image offscreen;
	private Graphics offgc;
	
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
		
		this.canvas = new Canvas(1280, 1024, background,carSim);
		this.setContentPane(canvas);
		this.setVisible(true);
		
		
		
	}

	public void checkCollision()
	{
		// TODO Auto-generated method stub
	}

	public void drawCars(ArrayList<Car> cars)
	{
		for(Car elem : cars)
		{
			this.offgc.drawImage(elem.picture, elem.x, elem.y, null);
		}
	}

	public void drawLights(HashMap<Integer, Street> streets) {
		for(Integer street : streets.keySet())
		{
			if(streets.get(street).trafficLight.getOn())
			{
				this.offgc.drawImage(streets.get(street).trafficLight.greenLight,streets.get(street).trafficLight.x,streets.get(street).trafficLight.y,null);
			}
			else
			{
				this.offgc.drawImage(streets.get(street).trafficLight.redLight,streets.get(street).trafficLight.x,streets.get(street).trafficLight.y,null);
			}
			
		}
		
	}

	public void startdraw() {
		this.offscreen = createImage(1280,1024);
		this.offgc = offscreen.getGraphics();
		
	}

	public void paintUpdate() {
		this.canvas.repaint();
		this.canvas.getGraphics().drawImage(this.offscreen, 0, 0, null);
		
	}
	
}
