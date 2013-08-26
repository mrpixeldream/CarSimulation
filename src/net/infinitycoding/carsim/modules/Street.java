package net.infinitycoding.carsim.modules;

import java.awt.geom.Line2D;

import javax.swing.ImageIcon;

import net.infinitycoding.carsim.CarSim;

public class Street
{
	Line2D stopLine;
	int startX, startY;
	TrafficLight trafficLight;
	ImageIcon streetPic;
	
	public Street(Line2D stopLine, int startX, int startY, TrafficLight trafficLight, String imgName)
	{
		this.stopLine = stopLine;
		this.startX = startX;
		this.startY = startY;
		this.trafficLight = trafficLight;
		this.streetPic = new ImageIcon(CarSim.class.getResource("res/" + imgName));
	}
}
