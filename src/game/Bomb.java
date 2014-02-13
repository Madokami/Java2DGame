package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bomb extends Object implements FriendlyInterface{
	private SpriteSheet ss;
	public long start;
	public int strength;
	
	public Bomb(int x,int y, Game game){
		super(x,y,game);
		strength=super.game.p.bombStrength;
		super.ss = super.game.ss2;
		super.image = super.ss.grabImage(1, 1, 32, 32);
		start = System.currentTimeMillis();
		
	}
	public void tick(){
		super.tick();
		if(System.currentTimeMillis()-start>2000){
			super.game.e.createExplosion((int)super.xGrid, (int)super.yGrid, 1, this.strength);
			super.game.c.removeEntity(this);
		}
		
	}
	
}
