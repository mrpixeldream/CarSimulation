package net.infinitycoding.carsim.modules;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.infinitycoding.carsim.CarSim;

public class ImageStruct
{
	public BufferedImage picture4;
	public BufferedImage picture1;
	public BufferedImage picture2;
	public BufferedImage picture3;
	
	public ImageStruct()
	{
		try
		{
			this.picture4 = ImageIO.read(CarSim.class.getResource("res/auto4.png"));
			this.picture1 = ImageIO.read(CarSim.class.getResource("res/auto1.png"));
			this.picture2 = ImageIO.read(CarSim.class.getResource("res/auto2.png"));
			this.picture3 = ImageIO.read(CarSim.class.getResource("res/auto3.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
