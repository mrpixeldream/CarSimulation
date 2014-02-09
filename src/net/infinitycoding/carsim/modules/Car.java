package net.infinitycoding.carsim.modules;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.infinitycoding.carsim.CarSim;

public class Car
{
	public Rectangle collisionBox = new Rectangle();
	public int warten = 101;
	public boolean isDriving = true;
	public int direction;
	public int x = 0;
	public int y = 0;
	public int streetNum;
	public BufferedImage picture;
	private BufferedImage picture4;
	private BufferedImage picture1;
	private BufferedImage picture2;
	private BufferedImage picture3;
	
	public Car(int streetNum) throws IOException
	{
		this.collisionBox.width = 99;
		this.collisionBox.height = 99;
		this.streetNum = streetNum;
		this.picture4 = ImageIO.read(CarSim.class.getResource("res/auto4.png"));
		this.picture1 = ImageIO.read(CarSim.class.getResource("res/auto1.png"));
		this.picture2 = ImageIO.read(CarSim.class.getResource("res/auto2.png"));
		this.picture3 = ImageIO.read(CarSim.class.getResource("res/auto3.png"));
	}

	public void updatePicture() throws IOException {
		switch(direction){
		case 1:
			this.picture = this.picture4;
			break;
		case 2:
			this.picture = this.picture1;
			break;
		case 3:
			this.picture = this.picture2;
			break;
		case 4:
			this.picture = this.picture3;
			break;
		}
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		this.collisionBox.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		this.collisionBox.y = y;
	}
}
