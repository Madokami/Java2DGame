package gameObject;

import game.Game;

import java.awt.Graphics;

public class Enemy_1_1 extends Enemy{
	private int chargeCounter=0;
	public Enemy_1_1(int x, int y, Game game) {
		super(x, y, game);
		moveRightGif=loader.loadGif("/image/spriteSheet/actors/enemy/enemy_1_1/Enemy_1_1_walkRight.gif");
		moveLeftGif=loader.loadGif("/image/spriteSheet/actors/enemy/enemy_1_1/Enemy_1_1_walkLeft.gif");
		moveUpGif=moveRightGif;
		moveDownGif=moveLeftGif;
		jumpRightGif=loader.loadGif("/image/spriteSheet/actors/enemy/enemy_1_1/Enemy_1_1_jumpRight.gif");
		jumpLeftGif=jumpRightGif;
		jumpDownGif=jumpRightGif;
		jumpUpGif=jumpRightGif;
		ss=SpriteData.mrPringles;
		imageWidth=56;
		imageHeight=56;
		ssX=1;
		ssY=1;
		frames=8;
		renderXShift=-6;
		renderYShift=-12;
		
		// TODO Auto-generated constructor stub
	}
	public void tick(){
		super.tick();
		if(chargeCounter>=20){
			chargeAtPlayer(20,20);
			chargeCounter=0;
		}
		chargeCounter++;
		Animate.animateWithGif(this);
	}
	
	
}
