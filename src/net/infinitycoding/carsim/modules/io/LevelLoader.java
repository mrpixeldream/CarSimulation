package net.infinitycoding.carsim.modules.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import net.infinitycoding.carsim.CarSim;
import net.infinitycoding.carsim.exceptions.LevelFormatException;
import net.infinitycoding.carsim.modules.Level;
import net.infinitycoding.carsim.modules.Street;

public class LevelLoader
{	
	static String lvlPic;
	static int maxCars;
	static float carRatio;
	static int streetCount;
	static HashMap<Integer, Integer> stopLineCoords = new HashMap<Integer, Integer>();
	static ArrayList<Integer> carSpawns;
	static HashMap<Integer, Integer> trafficLightCoords = new HashMap<Integer, Integer>();
	
	public static Level loadLevel(String lvlFileName) throws LevelFormatException
	{
		HashMap<String, String> contents = new HashMap<String, String>();
		Scanner scr = new Scanner(CarSim.class.getResourceAsStream(lvlFileName));
			
		while (scr.hasNext())
		{
			String line = scr.nextLine();
			//System.out.println("Line: " + line);
			String key = line.split("=")[0];
			String val = line.split("=")[1];
			contents.put(key, val);
				
			// DEBUGPART //
			//System.out.println("Key: " + key);
			//System.out.println("Value: " + val);
			//System.out.println("------------------");
		}
		
		Set<String> keys = contents.keySet();
		
		int lineCount = 0;
		for (String elem : keys)
		{
			lineCount++;
			if (elem.equalsIgnoreCase("lvlname"))
			{
				lvlPic = contents.get(elem);
			}
			else if (elem.equalsIgnoreCase("maxcars"))
			{
				try
				{
					maxCars = Integer.parseInt(contents.get(elem));
				}
				catch (NumberFormatException ex)
				{
					throw new LevelFormatException(lineCount);
				}
			}
			else if (elem.equalsIgnoreCase("spawnrate"))
			{
				try
				{
					carRatio = Float.parseFloat(contents.get(elem));
				}
				catch (NumberFormatException ex)
				{
					throw new LevelFormatException(lineCount);
				}
			}
			else if (elem.equalsIgnoreCase("streetcount"))
			{
				try
				{
					streetCount = Integer.parseInt(contents.get(elem));
				}
				catch (NumberFormatException ex)
				{
					throw new LevelFormatException(lineCount);
				}
			}
			else if (elem.startsWith("stopline"))
			{
				int x = Integer.parseInt(contents.get(elem).split(",")[0]);
				int y = Integer.parseInt(contents.get(elem).split(",")[1]);
				stopLineCoords.put(x, y);
			}
			else if (elem.equalsIgnoreCase(""))
			{
				
			}
			else if (elem.equalsIgnoreCase(""))
			{
				
			}
			else
			{
				throw new LevelFormatException(lineCount);
			}
		}
		
		Street[] streets = new Street[4];
		streets[0] = new Street(null, 0, 0, null, null);
		streets[1] = new Street(null, 0, 0, null, null);
		streets[2] = new Street(null, 0, 0, null, null);
		streets[3] = new Street(null, 0, 0, null, null);
		return new Level(streets, 0, 0.0F);
	}
}
