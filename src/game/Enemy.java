package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy extends Object implements FriendlyInterface{

	
	public Enemy(int x,int y, Game game){
		super(x,y,game);
		image = ss.grabImage(10, 1, 32, 32);
		ssRow=10;
		ssCol=1;
		this.game=super.game;
		moveDown();
	}
	public void tick(){
		super.tick();
		this.curX=super.curX;
		this.curY=super.curY;
		if(Physics.hitByAttack(this, game.fireList)){
			game.c.removeEntity(this);
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
	
}
