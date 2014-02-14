package net.infinitycoding.carsim.modules;

import java.io.IOException;

import net.infinitycoding.carsim.CarSim;

public class GameLoop extends Thread
{
	CarSim carSim;
	
	public GameLoop(CarSim carSim)
	{
		this.carSim = carSim;
	}
	
	@Override
	public void run()
	{
		long beforeTime = 0;
		long afterTime = 0;
		long difTime = 0;
		
		while(this.carSim.run)
		{
			difTime = afterTime - beforeTime;
			beforeTime = System.currentTimeMillis();
			
			GeneratorThread generator = new GeneratorThread(carSim);
			generator.start();
			
			this.carSim.checkCarsOut();
			
			try
			{
				generator.join();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try
			{
				this.carSim.moveCars(difTime);
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			this.carSim.userInterface.canvas.startDraw();
			
			DrawThread drawer = new DrawThread(carSim);
			drawer.start();
			
			try
			{
				drawer.join();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.carSim.userInterface.canvas.flip();
			this.carSim.userInterface.checkCollision();
			
			afterTime = System.currentTimeMillis();
			
			if (carSim.tickJumper == 60)
			{
				carSim.tickJumper = 0;
			}
		}
	}
}