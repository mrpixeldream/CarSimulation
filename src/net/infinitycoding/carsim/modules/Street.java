package net.infinitycoding.carsim.modules;

import java.awt.geom.Line2D;

public class Street
{
	Line2D stopLine;
	int startX, startY;
	TrafficLight trafficLight;
	
	public Street(Line2D stopLine, int startX, int startY, TrafficLight trafficLight)
	{
		this.stopLine = stopLine;
		this.startX = startX;
		this.startY = startY;
		this.trafficLight = trafficLight;
	}
}
