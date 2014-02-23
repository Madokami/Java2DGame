package game;


import game.Object.ORIENTATION;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Player extends Object implements FriendlyInterface{
	protected int ultCount;
	public int bombStrength;
	public int bombLength;
	public BufferedImage soulGem;
	public BufferedImageLoader loader;
	public double maxSoul;
	public double soul;
	public double mp;

	public Player(int x, int y, Game game) {
		super(x, y, game);
		loader = new BufferedImageLoader();
		bombStrength = 1;
		mp = 100;
		maxSoul=500;
		soul=maxSoul;
		super.speed=6;
		soulGem=loader.loadImage("/image/soulGemRed.png");
		// TODO Auto-generated constructor stub
	}


	public void tick(){
		super.tick();
		this.curX=super.curX;
		this.curY=super.curY;
		damage=Physics.hitByAttack(this, game.fireList);
		if(damage!=-1){
			takeDamage();
			setInvulnerable();			
		}
		if(hp<=0){
			playDeathSound();
			game.c.removeEntity(this);
			game.playerIsAlive=false;
		}
		if(soul>0){
			if(hp<100){
				soul--;
				hp=hp+0.2;
			}
		}
		if(soul>0){
			if(mp<100){
				soul--;
				mp=mp+0.2;
			}
		}
		
	
		//changes the player's "playerImage" depending on movement orientation
		Animate.animate(this);
	}
	

	public void playDamagedSound() {
		
	}
	public void playDeathSound(){
		
	}
	public void takeDamage(){
		if(!invulnerable){
			hp=hp-damage;
			System.out.println("you've taken "+damage+" damage");
			playDamagedSound();
		}
	}
	public void setInvulnerable(){
		invulnerable=true;
	}

	public void useUltimate(){
		
	}
	public boolean hasUltimate(){
		if(ultCount>0){
			return true;
		}
		return false;
	}


	public void renderPlayerHp(Graphics g) {
		// TODO Auto-generated method stub
		
	}



	
	
	//basic getter and setter methods

	//this method crashes if you presses movement buttons in rapid random order....
	
}
