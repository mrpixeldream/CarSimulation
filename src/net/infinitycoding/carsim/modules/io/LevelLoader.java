package net.infinitycoding.carsim.modules.io;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import net.infinitycoding.carsim.CarSim;
import net.infinitycoding.carsim.exceptions.LevelFormatException;
import net.infinitycoding.carsim.modules.Level;
import net.infinitycoding.carsim.modules.Street;
import net.infinitycoding.carsim.modules.TrafficLight;

public class LevelLoader
{	
	static String lvlPic;
	static int maxCars;
	static float carRatio;
	static int streetCount;
	static LinkedHashMap<Integer, Integer> stopLineCoords = new LinkedHashMap<Integer, Integer>();
	static LinkedHashMap<Integer, Integer> carSpawns = new LinkedHashMap<Integer, Integer>();
	static LinkedHashMap<Integer, Integer> trafficLightCoords = new LinkedHashMap<Integer, Integer>();
	static LinkedList<Integer> rotations = new LinkedList<Integer>();
	
	public static Level loadLevel(String lvlFileName) throws LevelFormatException, IOException
	{

		LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
		Scanner scr = new Scanner(CarSim.class.getResourceAsStream(lvlFileName));
			
		while (scr.hasNext())
		{
			String line = scr.nextLine();
			System.out.println("Line: " + line);
			String key = line.split("=")[0];
			String val = line.split("=")[1];
			contents.put(key, val);
				
			// DEBUGPART //
			//System.out.println("Key: " + key);
			//System.out.println("Value: " + val);
			//System.out.println("------------------");
		}
		for(String i : contents.keySet())
		{
			System.out.println(i);
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
			else if (elem.startsWith("spawn"))
			{
				int x = Integer.parseInt(contents.get(elem).split(",")[0]);
				int y = Integer.parseInt(contents.get(elem).split(",")[1]);
				carSpawns.put(x, y);
			}
			else if (elem.startsWith("traffic"))
			{
				int x = Integer.parseInt(contents.get(elem).split(",")[0]);
				int y = Integer.parseInt(contents.get(elem).split(",")[1]);
				trafficLightCoords.put(x, y);
			}
			else if (elem.startsWith("rotate"))
			{
				rotations.add(Integer.parseInt(contents.get(elem)));
			}
			else
			{
				throw new LevelFormatException(lineCount);
			}
		}
		
		Street[] streets = new Street[stopLineCoords.size()];
		Iterator<Integer> stopIterator = stopLineCoords.keySet().iterator();
		Iterator<Integer> trafficIterator = trafficLightCoords.keySet().iterator();
		Iterator<Integer> spawnIterator = carSpawns.keySet().iterator();
		
		for (int i = 0; i < stopLineCoords.size(); i++)
		{
			int x = stopIterator.next();
			int y = stopLineCoords.get(x);
			
			int tfX = trafficIterator.next();
			int tfY = trafficLightCoords.get(tfX);
			
			int sX = spawnIterator.next();
			int sY = carSpawns.get(sX);
			
			streets[i] = new Street(new Rectangle(x, y, 5, 5), sX, sY, new TrafficLight(tfX, tfY), rotations.get(i));
			System.out.println(streets[i].stopLine.x);
			System.out.println(streets[i].trafficLight.x);
			System.out.println("---");
		}
		
		return new Level(streets, maxCars, carRatio, lvlPic);
	}
}
