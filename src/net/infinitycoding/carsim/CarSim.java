package net.infinitycoding.carsim;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import net.infinitycoding.carsim.exceptions.LevelFormatException;
import net.infinitycoding.carsim.modules.Car;
import net.infinitycoding.carsim.modules.Level;
import net.infinitycoding.carsim.modules.io.LevelLoader;
import net.infinitycoding.carsim.util.CarGenerator;

public class CarSim
{
	private boolean run = true;
	private ArrayList<Car> cars = new ArrayList<Car>();
	private CarGenerator generator;
	private UserInterface userInterface;
	private Level level;

	public static void main(String[] args)
	{
		try
		{
			new CarSim().start();
		}
		catch (IOException e)
		{
			System.out.println("IOFehler");
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException
	{
		long beforeTime = 0;
		long afterTime = 0;
		long difTime = 0;
		
		generator = new CarGenerator();
		
		try
		{
			this.level = LevelLoader.loadLevel("res/test.lvl");
		}
		catch (LevelFormatException ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Leveldatei-Fehler", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			System.exit(-1);
		}
		
		userInterface = new UserInterface(level.streetPic,this);
		
		while(this.run)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			difTime = afterTime - beforeTime;
			beforeTime = System.currentTimeMillis();
			Car temp = this.generator.genNewCars(this.cars,this.level);
			if(temp != null)
			{
				
				this.cars.add(temp);
			}
			this.moveCars(difTime);
			this.userInterface.drawCars(this.cars);
			this.userInterface.drawLights(this.level.streets);
			this.userInterface.checkCollision();
			this.userInterface.b.dispose();
			this.userInterface.b.show();
			
			afterTime = System.currentTimeMillis();
		}
	}
	
	private void moveCars(long difTime)
	{
		boolean collision;
		for(Car car : cars)
		{
			collision = false;
			if(car.isDriving)
			{				
				for(Car other_car : cars)
				{
					if(car.collisionBox.intersects(other_car.collisionBox))
					{
						if(other_car.isDriving)
						{
							this.gameOver();
						}
						else
						{
							collision = true;
						}
					}
				}
				if(!collision)
				{
					switch(car.direction)
					{
						case 1:
							car.x -= 1;
							break;
						case 2:
							car.y-= 1;
							break;
						case 3:
							car.x += 1;
							break;
						case 4:
							car.y += 1;
							break;
						}
				}
			}
		}
	}
	public void onClick(int x, int y)
	{
		for(Integer nummer : this.level.streets.keySet())
		{
			if(this.level.streets.get(nummer).trafficLight.box.contains(x, y))
			{
				this.level.streets.get(nummer).trafficLight.changeLight();
			}
		}
	}

	private void gameOver()
	{
		JOptionPane.showMessageDialog(null, "Game over!", "Game over!", JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean isRunning()
	{
		return run;
	}

	public void setRunning(boolean run)
	{
		this.run = run;
	}
}
