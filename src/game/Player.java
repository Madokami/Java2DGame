package game;


import game.Object.ORIENTATION;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Player extends Object implements FriendlyInterface{
	protected int ultCount;
	public int bombStrength;
	public int bombLength;

	public Player(int x, int y, Game game) {
		super(x, y, game);
		bombStrength = 1;
		super.speed=3;
		// TODO Auto-generated constructor stub
	}


	public void tick(){
		super.tick();
		this.curX=super.curX;
		this.curY=super.curY;
		damage=Physics.hitByAttack(this, game.fireList);
		if(damage!=-1){
			hp=hp-damage;
			System.out.println("you've taken "+damage+" damage");
			playDamagedSound();
		}
		if(hp<=0){
			playDeathSound();
			game.c.removeEntity(this);
			game.playerIsAlive=false;
		}
	
		//changes the player's "playerImage" depending on movement orientation
		Animate.animate(this);
	}
	

	public void playDamagedSound() {
		
	}
	public void playDeathSound(){
		
	}

	public void useUltimate(){
		
	}
	public boolean hasUltimate(){
		if(ultCount>0){
			return true;
		}
		return false;
	}



	
	
	//basic getter and setter methods

	//this method crashes if you presses movement buttons in rapid random order....
	
}
