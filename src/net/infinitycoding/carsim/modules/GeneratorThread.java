package net.infinitycoding.carsim.modules;

import java.io.IOException;

import net.infinitycoding.carsim.CarSim;

public class GeneratorThread extends Thread
{
	CarSim carSim;
	
	public GeneratorThread(CarSim carSim)
	{
		this.carSim = carSim;
	}
	
	@Override
	public void run()
	{
		Car temp;
		try
		{
			temp = this.carSim.generator.genNewCars(this.carSim.cars, this.carSim.level);
			if(temp != null)
			{
				this.carSim.cars.add(temp);
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
