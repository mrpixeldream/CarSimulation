package net.infinitycoding.carsim;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;

import net.infinitycoding.carsim.exceptions.LevelFormatException;
import net.infinitycoding.carsim.modules.Car;
import net.infinitycoding.carsim.modules.GameLoop;
import net.infinitycoding.carsim.modules.ImageStruct;
import net.infinitycoding.carsim.modules.Level;
import net.infinitycoding.carsim.modules.io.LevelLoader;
import net.infinitycoding.carsim.util.CarGenerator;

public class CarSim
{
	public boolean run = true;
	public ArrayList<Car> cars = new ArrayList<Car>();
	public CarGenerator generator;
	public UserInterface userInterface;
	public Level level;
	private Clip crashSound;
	public int tickJumper = 0;
	public int points = 0;
	private int multi = 1;
	public ImageStruct images = new ImageStruct();

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
		System.out.println("Reading sound...");
		
		try
		{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("res/crash.wav")));
	        AudioFormat af = audioInputStream.getFormat();
	        int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
	        byte[] audio = new byte[size];
	        DataLine.Info info = new DataLine.Info(Clip.class, af, size);
	        audioInputStream.read(audio, 0, size);
	        crashSound = (Clip) AudioSystem.getLine(info);
	        crashSound.open(af, audio, 0, size);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		generator = new CarGenerator(this);
		
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
		
		GameLoop gameLoop = new GameLoop(this);
		gameLoop.start();
	}
	
	public void checkCarsOut() {
		for(Iterator<Car> it = this.cars.iterator(); it.hasNext();)
		{
			Car car = it.next();
			if(-1 < car.x && car.x < 1281){}
			else
			{
				System.out.println("LOESCHEN");
				this.level.streets.get(car.streetNum).hasSpawnedCar = false;
				it.remove();
				this.points++;
				
				if(this.points > 5){
					this.multi = 2;
				}
				if(this.points > 10){
					this.multi = 3;
				}
				if(this.points > 20){
					this.multi = 4;
				}
				if(this.points > 30){
					this.multi = 5;
				}
				if(this.points > 40){
					this.multi = 10;
				}
			}
			if(-1 < car.y && car.y < 1025){}
			else
			{
				System.out.println("LOESCHEN");
				this.level.streets.get(car.streetNum).hasSpawnedCar = false;
				it.remove();
				this.points++;
				
				if(this.points > 5){
					this.multi = 2;
				}
				if(this.points > 10){
					this.multi = 3;
				}
				if(this.points > 20){
					this.multi = 4;
				}
				if(this.points > 30){
					this.multi = 5;
				}
			}
		}
		
	}
	
	public int getJumpedTicks()
	{
		return tickJumper;
	}

	public void moveCars(long difTime) throws IOException
	{
		boolean collision;
		for(Car car : cars)
		{
			boolean weiter = true;
			
			if(car.warten <= 20)
			{
				
				car.warten++;
				weiter = false;
			}
			if(weiter)
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
				
				// Prepare the casr to check collisions
				Car checkCar = new Car(car.streetNum, this);
				checkCar.setX(car.x);
				checkCar.setY(car.y);
				checkCar.direction = car.direction;
				// Moves the checkCar a little bit to look in the fututre :D
				switch(checkCar.direction)
				{
					case 1:
						checkCar.setX(checkCar.x -10);
						break;
					case 2:
						checkCar.setY(checkCar.y - 10);
						break;
					case 3:
						checkCar.setX(checkCar.x + 10);
						break;
					case 4:
						checkCar.setY(checkCar.y + 10);
						break;
				}
				
				
				if(car.isDriving)
				{
					for(Car other_car : cars)
					{
						if(checkCar.collisionBox.intersects(other_car.collisionBox) && car != other_car)
						{
							if(other_car.isDriving  && !(other_car.warten <= 20))
							{
								if(car.collisionBox.intersects(other_car.collisionBox) && car != other_car)
								{
									this.gameOver();
								}
							}
							else
							{
								collision = true;
								car.warten = 0;
							}
						}
					}
					if(!collision)
					{
						//zahl++;
						switch(car.direction)
						{
							case 1:
								car.setX(car.x -1*this.multi);
								break;
							case 2:
								car.setY(car.y - 1*this.multi);
								break;
							case 3:
								car.setX(car.x + 1*this.multi);
								break;
							case 4:
								car.setY(car.y + 1*this.multi);
								break;
							}
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
		for(Car car : this.cars){
			if(car.collisionBox.contains(x,y)){
				System.out.println("--------------------------------");
				System.out.println("Warten: "+car.warten);
				System.out.println("Driving: "+car.isDriving);
				System.out.println("Direction: "+car.direction);
				//this.markedCar = car;
			}
		}
		
	}

	private void gameOver()
	{
		try
		{
            crashSound.start();
        }
		catch(Exception e)
		{ 
			e.printStackTrace(); 
		
		}
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
