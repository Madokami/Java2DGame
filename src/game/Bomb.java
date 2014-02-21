package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bomb extends Object implements FriendlyInterface{
	public long start;
	public int strength;
	public int length;
	
	public Bomb(int x,int y, Game game){
		super(x,y,game);
		length=game.p.bombLength;
		strength=game.p.bombStrength;
		ss = SpriteData.bomb;
		image = ss.grabImage(1, 1, 32, 32);
		start = System.currentTimeMillis();
		
	}
	public void tick(){
		super.tick();
		if(Physics.hitByAttack(this, game.fireList)!=-1){
			super.game.e.createExplosion((int)super.xGridNearest, (int)super.yGridNearest, length, strength);
			super.game.c.removeEntity(this);
		}
		if(System.currentTimeMillis()-start>2000){
			super.game.e.createExplosion((int)super.xGridNearest, (int)super.yGridNearest, length, strength);
			super.game.c.removeEntity(this);
		}
		
	}
	
}
