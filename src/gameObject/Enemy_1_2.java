package gameObject;

import game.Game;

import java.awt.Graphics;

public class Enemy_1_2 extends Enemy{
	public int counter=0;
	public int time=1;
	public Enemy_1_2(int x, int y, Game game) {
		super(x, y, game);
		
		run=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_1_2/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_1_2/stand",8);	
		sequence.startSequence(stand);
		
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void setTime(){
		time=rand.nextInt(100);
	}
}
