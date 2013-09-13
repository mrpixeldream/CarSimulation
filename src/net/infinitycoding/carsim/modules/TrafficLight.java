package net.infinitycoding.carsim.modules;

public class TrafficLight
{
	private boolean isOn = false;
	public int x;
	public int y;
	
	public TrafficLight(int x,int y)
	{
		this.x = x;
		this.y = y;
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
