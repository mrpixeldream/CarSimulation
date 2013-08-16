package net.infinitycoding.carsim.testing;

import javax.swing.JFrame;

public class Test extends JFrame {

	private static final long serialVersionUID = 1263804592269955535L;
	
	public Test() {
		
		super();
		this.add(new CustomPanel());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void start() {
		
		this.pack();
		this.setVisible(true);
		
	}

}
