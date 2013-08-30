package net.infinitycoding.carsim.modules.io;

import java.util.HashMap;
import java.util.Scanner;

import net.infinitycoding.carsim.CarSim;
import net.infinitycoding.carsim.modules.Level;

public class LevelLoader
{
	static HashMap<String, String> lvlContent = new HashMap<String, String>();
	
	public static Level loadLevel(String lvlFileName)
	{

			Scanner scr = new Scanner(CarSim.class.getResourceAsStream(lvlFileName));
			
			while (scr.hasNext())
			{
				String line = scr.nextLine();
				System.out.println("Line: " + line);
				String key = line.split("=")[0];
				String val = line.split("=")[1];
				lvlContent.put(key, val);
				
				// DEBUGPART //
				System.out.println("Key: " + key);
				System.out.println("Value: " + val);
				System.out.println("------------------");
			}
		
		
		return null;
	}
}
