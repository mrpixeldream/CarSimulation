package net.infinitycoding.carsim.util;

import java.io.IOException;
import java.util.ArrayList;

import net.infinitycoding.carsim.modules.Car;
import net.infinitycoding.carsim.modules.Level;

public class CarGenerator
{
	public Car genNewCars(ArrayList<Car> cars, Level level) throws IOException
	{
		if(cars.size() < level.MAX_CARS)
		{
			double zahl = Math.random();
			if(zahl <= level.CAR_RATIO)
			{
				int streetNum;
				do
				{
					streetNum = (int) (Math.random() * level.streetcount);
				} while (level.streets.get(streetNum).hasSpawnedCar);
				
				level.streets.get(streetNum).hasSpawnedCar = true;
				Car neu = new Car(streetNum);
				neu.setX(level.streets.get(streetNum).startX);
				neu.setY(level.streets.get(streetNum).startY);
				neu.streetNum = streetNum;
				switch(level.streets.get(streetNum).rotatioAngel)
				{
					case 180:
						neu.direction = 1;
						break;
					case 270:
						neu.direction = 4;
						break;
					case 0:
						neu.direction = 3;
						break;
					case 90:
						neu.direction = 2;
						break;
				}
				return neu;
			}
		}
		return null;
	}
}
