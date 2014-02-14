package net.infinitycoding.carsim.modules;

import java.io.IOException;

import net.infinitycoding.carsim.CarSim;

public class DrawThread extends Thread
{
	CarSim carSim;
	
	public DrawThread(CarSim carSim)
	{
		this.carSim = carSim;
	}
	
	@Override
	public void run()
	{
		try
		{
			this.carSim.userInterface.drawCars(this.carSim.cars);
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.carSim.userInterface.drawLights(this.carSim.level.streets);
		this.carSim.userInterface.drawFPS(this.carSim.userInterface.canvas.fps);
		this.carSim.userInterface.drawPoints(this.carSim.points);
	}
}
