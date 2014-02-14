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
	private CarSim carSim;
	
	public Car(int streetNum, CarSim carSim) throws IOException
	{
		this.collisionBox.width = 99;
		this.collisionBox.height = 99;
		this.streetNum = streetNum;
		this.carSim = carSim;
	}

	public void updatePicture() throws IOException {
		switch(direction){
		case 1:
			this.picture = this.carSim.images.picture4;
			break;
		case 2:
			this.picture = this.carSim.images.picture1;
			break;
		case 3:
			this.picture = this.carSim.images.picture2;
			break;
		case 4:
			this.picture = this.carSim.images.picture3;
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
