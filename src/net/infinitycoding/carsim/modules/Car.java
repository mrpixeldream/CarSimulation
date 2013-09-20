package net.infinitycoding.carsim.modules;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.infinitycoding.carsim.CarSim;

public class Car
{
	public Rectangle collisionBox = new Rectangle();
	public boolean isDriving = true;
	public int direction;
	public int x = 0;
	public int y = 0;
	public int streetNum;
	public Image picture;
	
	public Car(int streetNum) throws IOException
	{
		this.streetNum = streetNum;
		this.picture = ImageIO.read(CarSim.class.getResource("res/auto.png"));
	}
}
