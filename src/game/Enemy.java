package game;

import game.GameObject.ORIENTATION;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy extends MovableObject{
	
	public int score, exp;
	
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
	}
	public void tick(){
		super.tick();
		this.curX=super.curX;
		this.curY=super.curY;
		damage=Physics.hitByAttack(this, game.fireList);
		if(damage!=-1){
			hp=hp-damage;
		}
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
		Animate.animate(this);
	
	}
	public void providePoints(Player p){
		p.expCurrent+=exp;
		p.score+=score;
	}
	
}
