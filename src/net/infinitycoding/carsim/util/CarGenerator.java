package net.infinitycoding.carsim.util;

import java.io.IOException;
import java.util.ArrayList;

import net.infinitycoding.carsim.CarSim;
import net.infinitycoding.carsim.modules.Car;
import net.infinitycoding.carsim.modules.Level;

public class CarGenerator extends Thread
{
	private Car carToGenerate;
	private CarSim carSim;
	
	public CarGenerator(CarSim carSim)
	{
		this.carSim = carSim;
	}
	
	private boolean spawnFree(Car car, ArrayList<Car> otherCars)
	{
		for (Car elem : otherCars)
		{
			if (elem.collisionBox.intersects(car.collisionBox))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public Car genNewCars(ArrayList<Car> cars, Level level) throws IOException
	{
		if(cars.size() < level.MAX_CARS)
		{
			double zahl = Math.random();
			if(zahl <= level.CAR_RATIO)
			{
				int streetNum;
				boolean freeSpace = false;
				
				streetNum = (int) (Math.random() * level.streetcount);
					
				carToGenerate = new Car(streetNum, carSim);
				carToGenerate.setX(level.streets.get(streetNum).startX);
				carToGenerate.setY(level.streets.get(streetNum).startY);
				carToGenerate.streetNum = streetNum;
					
				freeSpace = spawnFree(carToGenerate, cars);

				if (freeSpace)
				{
					switch(level.streets.get(streetNum).rotatioAngel)
					{
						case 180:
							carToGenerate.direction = 1;
							break;
						case 270:
							carToGenerate.direction = 4;
							break;
						case 0:
							carToGenerate.direction = 3;
							break;
						case 90:
							carToGenerate.direction = 2;
							break;
					}
					return carToGenerate;
				}
				
				//level.streets.get(streetNum).hasSpawnedCar = true;
			}
		}
		return null;
	}
	
	public void run()
	{
		
	}
}
