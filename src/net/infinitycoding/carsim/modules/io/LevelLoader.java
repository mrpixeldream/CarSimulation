package net.infinitycoding.carsim.modules.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import net.infinitycoding.carsim.modules.Level;

public class LevelLoader
{
	public static Level loadLevel(String lvlFileName)
	{
		File lvlFile = new File(lvlFileName);
		try
		{
			Scanner scr = new Scanner(lvlFile);
			StringTokenizer tokenizer;
			
			while (scr.hasNext())
			{
				String line = scr.nextLine();
				tokenizer = new StringTokenizer(line, "=");
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datei nicht gefunden!", "Dateifehler", JOptionPane.ERROR_MESSAGE);
		}
		
		return null;
	}
}
