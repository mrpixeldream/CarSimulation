package net.infinitycoding.carsim.exceptions;

public class LevelFormatException extends Exception
{
	public LevelFormatException(int lineNumber)
	{
		super("Wrong Level Format (Line " + lineNumber + ")\n");
	}
}
