package net.infinitycoding.carsim;

import net.infinitycoding.carsim.testing.Test;

public class CarSim {
	
	private boolean run;

	public static void main(String[] args) {
		
		new Test().start();
		
	}
	
	public void start(){
		while(this.run){
			//Hauptschleife
			
		}
	}
	
	public boolean isRun()
	{
		return run;
	}

	public void setRun(boolean run)
	{
		this.run = run;
	}
}