package net.infinitycoding.carsim.modules;

public class TrafficLight
{
	private boolean isOn;
	public int x;
	public int y;
	
	public void setOn(boolean on)
	{
		this.isOn= on;
	}
	public boolean getOn()
	{
		return this.isOn;
	}
}
