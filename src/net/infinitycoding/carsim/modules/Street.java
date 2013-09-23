package net.infinitycoding.carsim.modules;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Street
{
	public Rectangle stopLine;
	public int startX, startY;
	public TrafficLight trafficLight;
	public ImageIcon streetPic;
	
	public Street(Rectangle stopLine, int startX, int startY, TrafficLight trafficLight)
	{
		this.stopLine = stopLine;
		this.startX = startX;
		this.startY = startY;
		this.trafficLight = trafficLight;
		
	}
}
