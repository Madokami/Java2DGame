package gameObject;

import game.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bomb extends MovableObject{
	public int counter;
	public int explodeTime;
	public int strength;
	public int length;
	public String direction;
	
	
	
	public Bomb(int x,int y, Game game,int bombStrength,int bombLength,int duration){
		super(x,y,game);
		explodeTime=duration;
		length=bombLength;
		strength=bombStrength;
		ss = SpriteData.bomb;
		image = ss.grabImage(1, 1, 32, 32);
		counter = 0;
		hp=1;
		//fireBomb();
		
	}
	public void tick(){
		super.tick();
		counter++;
		if(counter>explodeTime||hp<=0){
			explode();
		}
	}
	public void explode(){
		game.getController().createExplosion((int)super.xGridNearest, (int)super.yGridNearest, length, strength);
		game.getController().removeEntity(this);
	}

}
