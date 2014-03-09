package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Fire extends GameObject{
int strength;
private int counter;

	public Fire(int x, int y, Game game,int Strength) {
		super(x,y,game);
		ss=SpriteData.bricks;
		image=ss.grabImage(5, 15, 32, 32);
		this.strength = Strength;
		counter=0;
	}
	public void tick(){
		counter++;
		if(counter>5){
			game.c.removeFire(this);
		}
		if(Physics.collide(this, game.p)){
			applyDamage(strength,15,game.p);
		}
		int enemyHit=Physics.collision(this, game.eList);
		if(enemyHit!=-1){
			applyDamage(strength,10,game.eList.get(enemyHit));
		}
		int wallHit=Physics.hitWall(this, game.brickList);
		if(wallHit!=-1){
			applyDamage(strength,10,game.brickList.get(wallHit));
		}
		int bombHit=Physics.hitBomb(this, game.bList);
		if(bombHit!=-1){
			applyDamage(strength,10,game.bList.get(bombHit));
		}
	}

	
	
	public int getStrength(){
		return strength;
	}
}
