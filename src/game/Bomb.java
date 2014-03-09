package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bomb extends MovableObject{
	public int counter;
	public int explodeTime=60;
	public int strength;
	public int length;
	public String direction;
	
	
	
	public Bomb(int x,int y, Game game){
		super(x,y,game);
		length=game.p.bombLength;
		strength=game.p.bombStrength;
		ss = SpriteData.bomb;
		image = ss.grabImage(1, 1, 32, 32);
		counter = 0;
		direction=game.p.direction;
		hp=1;
		//fireBomb();
		
	}
	public void tick(){
		super.tick();
		counter++;
		if(counter>explodeTime){
			game.c.createExplosion((int)super.xGridNearest, (int)super.yGridNearest, length, strength);
			game.c.removeEntity(this);
		}
		else if(hp<=0){
			game.c.createExplosion((int)super.xGridNearest, (int)super.yGridNearest, length, strength);
			game.c.removeEntity(this);
		}
		
	}
	public void fireBomb(){
		if(direction.equals("up")){
			moveUp();
		}
		else if(direction.equals("down")){
			moveDown();
		}
		else if(direction.equals("right")){
			moveRight();
		}
		else if(direction.equals("left")){
			moveLeft();
		}
	}
}
