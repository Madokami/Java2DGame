package game;

import game.GameObject.ORIENTATION;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends MovableObject{
	
	public int score, exp;
	public Random rand;
	public int counter;
	public int collisionDamage;
	
	public Enemy(int x,int y, Game game){
		super(x,y,game);
		//image = ss.grabImage(10, 1, 32, 32);
		ssX=10;
		ssY=1;
		ss=SpriteData.char2;
		image = ss.grabImage(ssX, ssY, size, size);
		moveDown();
		score = 50;
		exp = 50;
		collisionDamage=20;
		
		counter = 0;
		rand = new Random();
	}
	public void tick(){
		super.tick();
		
		if(Physics.collide(game.p, this)) applyDamage(collisionDamage,10,game.p);
		counter++;
		if(counter>40){
			counter=0;
			moveRandomly();
		}
		
		this.curX=super.curX;
		this.curY=super.curY;
		
		if(hp<=0){
			game.enemyCount--;
			game.c.removeEntity(this);
			providePoints(game.p);
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
