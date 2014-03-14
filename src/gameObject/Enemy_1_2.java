package gameObject;

import game.Game;

import java.awt.Graphics;

public class Enemy_1_2 extends Enemy{
	public int counter=0;
	public int time=1;
	public Enemy_1_2(int x, int y, Game game) {
		super(x, y, game);
		ss=SpriteData.adelbertMini;
		ssWidth=48;
		ssHeight=48;
		collisionWidth=32;
		collisionHeight=32;
		this.ssX=1;
		ssY=1;
		frames=9;
		renderXShift=-8;
		renderYShift=-8;
		
		// TODO Auto-generated constructor stub
	}
	
	public void tick(){
		super.tick();
		
		Animate.animate(this);
		
	}
	
	public void setTime(){
		time=rand.nextInt(100);
	}
}
