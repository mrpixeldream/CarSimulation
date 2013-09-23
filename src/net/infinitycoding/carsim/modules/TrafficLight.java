package net.infinitycoding.carsim.modules;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.infinitycoding.carsim.CarSim;

public class TrafficLight
{
	private boolean isOn = false;
	public int x;
	public int y;
	public Image greenLight,redLight;
	
	public TrafficLight(int x,int y) throws IOException
	{
		this.x = x;
		this.y = y;
		this.redLight = ImageIO.read(CarSim.class.getResource("res/roteAmpel.png"));
		this.greenLight = ImageIO.read(CarSim.class.getResource("res/grueneAmpel.png"));
	}
	
	public void setOn(boolean on)
	{
		this.isOn= on;
	}
	public boolean getOn()
	{
		return this.isOn;
	}
}
