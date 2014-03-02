package game;


import game.GameObject.ORIENTATION;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Player extends MovableObject implements FriendlyInterface{
	public int bombStrength;
	public int bombLength;
	public BufferedImage soulGem;
	public BufferedImageLoader loader;
	public double maxSoul;
	public double soul;
	public double mp,maxMp,maxHp;
	
	public SpriteSheet status;
	public BufferedImage okStatus,midDamageStatus,highDamageStatus,despairStatus,statusBg;
	public final int W_STATUS = 128;
	public final int H_STATUS = 128;
	
	public PlayerData pData;

	public Player(int x, int y, Game game) {
		super(x, y, game);
		loader = new BufferedImageLoader();
		bombStrength = 50;
		bombLength=3;
		hp=100;
		mp=100;
		maxHp=hp;
		maxMp=mp;
		maxSoul=500;
		soul=maxSoul;
		spd=6;
		soulGem=loader.loadImage("/image/soulGemRed.png");
		statusBg=loader.loadImage("/image/statusBg.png");
		
		pData = game.pData;
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
	
	public void render(Graphics g){
		super.render(g);
		renderPlayerStatus(g);
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
	public void setStatusImages() {
		okStatus = status.grabImage(1, 1, W_STATUS, H_STATUS);
		midDamageStatus = status.grabImage(2, 1, W_STATUS, H_STATUS);
		highDamageStatus = status.grabImage(3, 1, W_STATUS, H_STATUS);
		despairStatus = status.grabImage(4, 1, W_STATUS, H_STATUS);
		
	}

	public void renderPlayerStatus(Graphics g){
		//g.drawImage(statusBg,0, GameSystem.ABSHEIGHT-H_STATUS+28,null);
	if(soul==0){
			g.drawImage(despairStatus,0, GameSystem.ABSHEIGHT-H_STATUS+14,null);
			return;
	}
	if(hp/maxHp>0.6){
		g.drawImage(okStatus,0, GameSystem.ABSHEIGHT-H_STATUS+14,null);
	}
	else if(hp/maxHp<=0.6&&hp/maxHp>0.3){
		g.drawImage(midDamageStatus,0, GameSystem.ABSHEIGHT-H_STATUS+14,null);
	}
	else if(hp/maxHp<=0.3){
		g.drawImage(highDamageStatus,0, GameSystem.ABSHEIGHT-H_STATUS+14,null);
	}
	}
	public void renderPlayerHp(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
