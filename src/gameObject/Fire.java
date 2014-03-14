package gameObject;

import game.Game;

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
			controller.removeFire(this);
		}
		if(Physics.collide(this, game.getPlayer())){
			applyDamage(strength,15,game.getPlayer());
		}
		int enemyHit=Physics.collision(this, game.getEnemyList());
		if(enemyHit!=-1){
			applyDamage(strength,10,game.getEnemyList().get(enemyHit));
		}
		int wallHit=Physics.hitWall(this, game.getBrickList());
		if(wallHit!=-1){
			applyDamage(strength,10,game.getBrickList().get(wallHit));
		}
		int bombHit=Physics.hitBomb(this, game.getBombList());
		if(bombHit!=-1){
			applyDamage(strength,10,game.getBombList().get(bombHit));
		}
	}

	
	
	public int getStrength(){
		return strength;
	}
}
