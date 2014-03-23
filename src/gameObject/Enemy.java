package gameObject;

import game.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends MovableObject{
	
	public int score, exp;
	public Random rand;
	public int counter;
	public int collisionDamage;
	protected Ai ai;
	
	public Enemy(int x,int y, Game game){
		super(x,y,game);
		//image = ss.grabImage(10, 1, 32, 32);
		ssX=10;
		ssY=1;
		ss=SpriteData.char2;
		image = ss.grabImage(ssX, ssY, ssWidth, ssHeight);
		moveDown();
		score = 50;
		exp = 50;
		collisionDamage=20;
		
		counter = 0;
		rand = new Random();
		ai=new Ai();
	}
	public void tick(){
		super.tick();
		/*
		if(Physics.overlapWithOtherEnemies(this, game.getEnemyList())){
			this.moveToLastAcceptableLocation();
		}
		*/
		if(Physics.collide(game.getPlayer(), this)) applyDamage(collisionDamage,30,game.getPlayer());
		counter++;
		if(counter>10){
			counter=0;
			if(rand.nextInt(10)<8){
			chasePlayer();
			}
			else{
				moveRandomly();
			}
		}
		int bombKicked=Physics.onTopOfBomb(this, game.getBombList());
		if(bombKicked!=-1){
			this.kickBomb();
		}
		/*
		if(counter>40){
			counter=0;
			moveRandomly();
		}
		*/
		
		this.curX=super.curX;
		this.curY=super.curY;
		
		if(hp<=0){
			game.decreaseEnemyCount();
			game.getController().removeEntity(this);
			providePoints(game.getPlayer());
		}
		/*
		if(super.x<=0)
			moveRight();
		if(super.x>=Game.WIDTH*Game.SCALE-23)
			moveLeft();
		if(super.y<=0)
			super.moveDown();
		if(super.y>=Game.HEIGHT*Game.SCALE-23)
			super.moveUp();
		if(Physics.collision(game.p, this)){
			//game.c.removeEntity(this);
		}
		*/
	
	}
	public void providePoints(Player p){
		p.expCurrent+=exp;
		p.score+=score;
	}
	public void moveRandomly(){
		int temp = rand.nextInt(4);
		if(temp==0){
			moveUp();
		}
		else if(temp==1){
			moveDown();
		}
		else if(temp==2){
			moveLeft();
		}
		else if(temp==3){
			moveRight();
		}
	}
	public void chasePlayer(){
		String s;
		s=ai.makeStep(game.getWallArray(), game.getPlayer().xGridNearest, game.getPlayer().yGridNearest, lastX, lastY);
		if(s.equals("up")) {
			if(!Physics.blockedByEnemy(this, game.getEnemyList(), lastX, lastY-1)) moveUp();
		}
		else if(s.equals("down")) {
			if(!Physics.blockedByEnemy(this, game.getEnemyList(), lastX, lastY+1)) moveDown();
		}
		else if(s.equals("left")) {
			if(!Physics.blockedByEnemy(this, game.getEnemyList(), lastX-1, lastY)) moveLeft();
		}
		else if(s.equals("right")) {
			if(!Physics.blockedByEnemy(this, game.getEnemyList(), lastX+1, lastY-1)) moveRight();
		}
		else if(s.equals("stop")) moveRandomly();
	}
	public void chargeAtPlayer(int speed,int duration){
		String dir=ai.isValidStraightLine(game.getWallArray(), game.getPlayer().xGridNearest, game.getPlayer().yGridNearest, xGridNearest, yGridNearest);
		if(dir.equals("stop")){
			return;
		}
		else if(dir.equals("right")){
			moveRight();
			startCharge(speed,duration);
		}
		else if(dir.equals("left")){
			moveLeft();
			startCharge(speed,duration);
		}
		else if(dir.equals("up")){
			moveUp();
			startCharge(speed,duration);
		}
		else if(dir.equals("down")){
			moveDown();
			startCharge(speed,duration);
		}
		
		
	}
	
}
