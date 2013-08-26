package net.infinitycoding.carsim.modules;

import java.util.HashMap;

public class Level
{
	HashMap<Integer, Street> streets = new HashMap<Integer, Street>();
	
	public int carCount  = 0;
	
	public int MAX_CARS = 0;
	public long CAR_RATIO = 0L;
	
	public Level(Street[] streets, int maxCars, long carRatio)
	{
		int streetNumIndex = 0;
		
		for (Street elem : streets)
		{
			this.streets.put(streetNumIndex, elem);
			streetNumIndex++;
		}
	}
}
