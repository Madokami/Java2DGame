package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import system.GameSystem;

public class Input extends KeyAdapter{
	GameSystem sys;
	
	public Input(GameSystem sys){
		this.sys = sys;
	}
	
	public void keyPressed(KeyEvent e){
		sys.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		sys.keyReleased(e);
	}
	
}
