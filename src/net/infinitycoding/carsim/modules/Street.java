package net.infinitycoding.carsim.modules;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Street
{
	public Rectangle stopLine;
	public int startX, startY;
	public TrafficLight trafficLight;
	public ImageIcon streetPic;
	public AffineTransform transform;
	public int rotatioAngel ;
	public boolean hasSpawnedCar = false;
	
	public Street(Rectangle stopLine, int startX, int startY, TrafficLight trafficLight, int rotation)
	{
		this.stopLine = stopLine;
		this.startX = startX;
		this.startY = startY;
		this.trafficLight = trafficLight;
		this.transform = AffineTransform.getRotateInstance(rotation);
		this.rotatioAngel = rotation;
	}
}
