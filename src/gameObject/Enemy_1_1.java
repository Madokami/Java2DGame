package gameObject;

import game.Game;

import java.awt.Graphics;

public class Enemy_1_1 extends Enemy{
	private int chargeCounter=0;
	public Enemy_1_1(int x, int y, Game game) {
		super(x, y, game);
		
		run=new ImageSequence("/image/spriteSheet/actors/player/homura/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/player/homura/stand",8);
		damage=new ImageSequence("/image/spriteSheet/actors/player/homura/damage",4);		
		dead=new ImageSequence("/image/spriteSheet/actors/player/homura/dead",14);
		sequence.startSequence(stand);
		
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
	}
	
	
}
