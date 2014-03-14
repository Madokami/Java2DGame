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
	protected shortestPath ai;
	
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
		ai=new shortestPath();
	}
	public void tick(){
		super.tick();
		
		if(Physics.collide(game.getPlayer(), this)) applyDamage(collisionDamage,10,game.getPlayer());
		counter++;
		if(counter>15){
			counter=0;
			//if(rand.nextInt(10)>4){
				String s;
				s=ai.makeStep(game.getWallArray(), game.getPlayer().xGridNearest, game.getPlayer().yGridNearest, lastX, lastY);
				if(s.equals("up")) moveUp();
				else if(s.equals("down")) moveDown();
				else if(s.equals("left")) moveLeft();
				else if(s.equals("right")) moveRight();
				else if(s.equals("stop")) moveStop();
			//}
			//else{
			//	moveRandomly();
			//}
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
	
	
}
