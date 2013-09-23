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
				System.out.println("new Car");
				int strasse = (int) (Math.random() * level.streetcount);
				Car neu = new Car(strasse);
				int street = (int) (Math.random() * (level.streetcount - 1));
				neu.x = level.streets.get(street).startX;
				System.out.println(level.streets.get(street).startX);
				neu.y = level.streets.get(street).startY;
				neu.streetNum = street;
				return neu;
			}
		}
		return null;
	}

}
