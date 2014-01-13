package net.infinitycoding.carsim;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
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
		
		Image background = ImageIO.read(CarSim.class.getResource("res/strasse.png"));
		userInterface = new UserInterface(background,this);
		
		while(this.run)
		{
			difTime = afterTime - beforeTime;
			beforeTime = System.currentTimeMillis();
			System.out.println("test");
			Car temp = this.generator.genNewCars(this.cars,this.level);
			if(temp != null)
			{
				this.cars.add(temp);
			}
			this.checkCarsOut();
			System.out.println("pre move");
			this.moveCars(difTime);
			System.out.println("after move");
			this.userInterface.canvas.startDraw();
			System.out.println("after draw");
			this.userInterface.drawCars(this.cars);
			this.userInterface.drawLights(this.level.streets);
			System.out.println("elem draw");
			this.userInterface.canvas.flip();
			System.out.println("page flip");
			this.userInterface.checkCollision();
			System.out.println("after collision check");
			
			afterTime = System.currentTimeMillis();
		}
	}
	
	private synchronized void checkCarsOut() {
		for(Iterator<Car> it = this.cars.iterator(); it.hasNext();)
		{
			Car car = it.next();
			if(-1 < car.x && car.x < 1281){}
			else
			{
				System.out.println("LOESCHEN");
				this.level.streets.get(car.streetNum).hasSpawnedCar = false;
				it.remove();
			}
			if(-1 < car.y && car.y < 1025){}
			else
			{
				System.out.println("LOESCHEN");
				this.level.streets.get(car.streetNum).hasSpawnedCar = false;
				it.remove();
			}
		}
		
	}

	private void moveCars(long difTime)
	{
		System.out.println("move");
		int zahl = 0;
		boolean collision;
		for(Car car : cars)
		{
			collision = false;
			for(int streetNum : level.streets.keySet())
			{
				if(!collision)
				{
					if(car.collisionBox.intersects(level.streets.get(streetNum).stopLine) && !level.streets.get(streetNum).trafficLight.getOn())
					{
						car.isDriving = false;
						collision = true;
					}
					else
					{
						car.isDriving = true;
						collision = false;
					}
				}
			}
			if(car.isDriving)
			{
				for(Car other_car : cars)
				{
					if(car.collisionBox.intersects(other_car.collisionBox) && car != other_car)
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
					zahl++;
					switch(car.direction)
					{
						case 1:
							car.setX(car.x -1);
							break;
						case 2:
							car.setY(car.y - 1);
							break;
						case 3:
							car.setX(car.x + 1);
							break;
						case 4:
							car.setY(car.y + 1);
							break;
						}
				}
			}
		}
		//System.out.println(zahl);
	}
	public void onClick(int x, int y)
	{
		for(Integer nummer : this.level.streets.keySet())
		{
			if(this.level.streets.get(nummer).trafficLight.box.contains(x, y))
			{
				this.level.streets.get(nummer).trafficLight.changeLight();
				//System.out.println(this.level.streets.get(nummer).trafficLight.x);
				//
				System.out.println(this.level.streets.get(nummer).stopLine.x);
			}
		}
		System.out.println("Klick an: "+x+","+y);
	}

	private void gameOver()
	{
		JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);

		System.exit(0);
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
