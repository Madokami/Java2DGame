package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Object implements FriendlyInterface{
	
	public int bombStrength;
	public Player(int x, int y, Game game) {
		super(x, y, game);
		bombStrength = 1;
		// TODO Auto-generated constructor stub
	}


	public void tick(){
		super.tick();
		this.curX=super.curX;
		this.curY=super.curY;
		
		if(Physics.hitByAttack(this, game.fireList)){
			game.c.removeEntity(this);
			game.playerIsAlive=false;
		}
	
		//changes the player's "playerImage" depending on movement orientation
		Animate.animate(this);
		
	
	}


	
	
	//basic getter and setter methods

	//this method crashes if you presses movement buttons in rapid random order....
	
}
