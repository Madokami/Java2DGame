package gameObject;

import game.Game;

import java.awt.Graphics;

public class Enemy_1_1 extends Enemy{

	public Enemy_1_1(int x, int y, Game game) {
		super(x, y, game);
		ss=SpriteData.mrPringles;
		ssWidth=48;
		ssHeight=48;
		collisionWidth=32;
		collisionHeight=32;
		ssX=1;
		ssY=1;
		frames=8;
		renderXShift=-8;
		renderYShift=-8;
		
		// TODO Auto-generated constructor stub
	}
	public void tick(){
		super.tick();
		Animate.animate(this);
	}
	
	
}
