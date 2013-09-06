package net.infinitycoding.carsim;

import java.util.ArrayList;

import net.infinitycoding.carsim.modules.Car;
import net.infinitycoding.carsim.modules.Level;
import net.infinitycoding.carsim.util.CarGenerator;

public class CarSim
{
	private boolean run = false;
	private ArrayList<Car> cars = new ArrayList<Car>();
	private CarGenerator generator;
	private UserInterface userInterface;
	private Level level;

	public static void main(String[] args)
	{
		new CarSim().start();
	}
	
	public void start()
	{
		long beforeTime = 0;
		long afterTime = 0;
		long difTime = 0;
		
		userInterface = new UserInterface();
		
		generator = new CarGenerator();
		
		while(this.run)
		{
			//Hauptschleife
			difTime = afterTime - beforeTime;
			beforeTime = System.currentTimeMillis();
			
			this.cars.add(this.generator.genNewCars(this.cars,this.level));
			this.moveCars(difTime);
			this.userInterface.drawCars();
			this.userInterface.checkCollision();
			
			afterTime = System.currentTimeMillis();
		}
	}
	

	private void moveCars(long difTime)
	{
		boolean collision;
		for(Car car : (Car[]) cars.toArray())
		{
			collision = false;
			if(car.isDriving)
			{				
				for(Car other_car : (Car[]) cars.toArray())
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
							car.y += 1;
							break;
						case 2:
							car.x += 1;
							break;
						case 3:
							car.y -= 1;
							break;
						case 4:
							car.x -= 1;
							break;
						}
				}
			}
		}
	}

	private void gameOver() {
		System.out.println("Gameover!!!");
		
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