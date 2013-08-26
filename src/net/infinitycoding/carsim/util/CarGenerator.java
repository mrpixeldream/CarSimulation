package net.infinitycoding.carsim.util;

import java.util.ArrayList;

import net.infinitycoding.carsim.modules.Car;
import net.infinitycoding.carsim.modules.Level;

public class CarGenerator
{

	public Car genNewCars(ArrayList<Car> cars, Level level)
	{
		if(cars.size() <= level.MAX_CARS)
		{
			double zahl = Math.random();
			if(zahl <= level.CAR_RATIO)
			{
				int strasse = (int) (Math.random() * level.streetcount);
				Car neu = new Car(strasse);
				return neu;
			}
		}
		return null;
	}

}
