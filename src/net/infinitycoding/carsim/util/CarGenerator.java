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
				int strasse = (int) (Math.random() * level.streetcount);
				Car neu = new Car(strasse);
				int street = (int) (Math.random() * (level.streetcount));
				neu.setX(level.streets.get(street).startX);
				neu.setY(level.streets.get(street).startY);
				neu.streetNum = street;
				switch(level.streets.get(street).rotatioAngel)
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
