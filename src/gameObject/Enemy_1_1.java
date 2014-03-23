package gameObject;

import game.Game;

import java.awt.Graphics;

public class Enemy_1_1 extends Enemy{
	private int chargeCounter=0;
	public Enemy_1_1(int x, int y, Game game) {
		super(x, y, game);
		
		run=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_1_1/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_1_1/stand",8);
		damage=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_1_1/damage",4);		
		sequence.startSequence(stand);

		
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
