package net.infinitycoding.carsim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import net.infinitycoding.carsim.modules.Car;

public class UserInterface extends JFrame
{	
	Canvas canvas;
	
	public UserInterface(ImageIcon background)
	{
		super();
		
		this.setBounds(0, 0, 1280, 1024);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("CarSimulator by InfinityCoding");
		this.setLayout(new GridLayout(1, 1));
		this.setBackground(Color.WHITE);
		
		this.canvas = new Canvas(1280, 1024, background);
		this.setContentPane(canvas);
		
		this.setVisible(true);
	}

	public void checkCollision()
	{
		// TODO Auto-generated method stub
	}

	public void drawCars(ArrayList<Car> cars)
	{
		for(Car car : cars)
		{
			System.out.println("Drawing a car");
			this.canvas.getGraphics().drawImage(car.picture, car.x, car.y, null);
		}
	}
	
}
