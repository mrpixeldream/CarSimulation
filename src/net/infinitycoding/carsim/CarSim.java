package net.infinitycoding.carsim;

import java.util.ArrayList;

import net.infinitycoding.carsim.modules.Car;
import net.infinitycoding.carsim.testing.Test;
import net.infinitycoding.carsim.util.CarGenerator;

public class CarSim {
	
	private boolean run = false;
	private ArrayList<Car> cars = new ArrayList<Car>();

	public static void main(String[] args) {
		
		new Test().start();
		
	}
	
	public void start(){
		long beforeTime = 0;
		long afterTime = 0;
		long difTime = 0;
		
		UserInterface userInterface = new UserInterface();
		
		CarGenerator generator = new CarGenerator();
		
		while(this.run){
			//Hauptschleife
			difTime = afterTime - beforeTime;
			beforeTime = System.currentTimeMillis();
			this.moveCars(difTime);
			afterTime = System.currentTimeMillis();
		}
	}
	
	private void moveCars(long difTime)
	{
		
		
	}

	public boolean isRunning()
	{
		return run;
	}

	public void setRunning(boolean run)
	{
		this.run = run;
	}
}