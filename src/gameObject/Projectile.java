package gameObject;

import system.GameSystem;
import game.Game;

public abstract class Projectile extends MovableObject {
	
	public int damage;
	public GameObject owner=null;
	public Projectile(int x, int y, Game game,GameObject o) {
		super(x, y, game);
		owner=o;
		damage = 10;
		// TODO Auto-generated constructor stub
	}
	public void tick(){
		removeIfOutSideScreen();
		int tempWall=Physics.hitWall(this, game.getBrickList());
		if(tempWall!=-1){
			applyDamage(damage,0,game.getBrickList().get(tempWall));
		}
		int tempEnemy=Physics.collision(this, game.getEnemyList());
		if(tempEnemy!=-1){
			applyDamage(damage,0,game.getEnemyList().get(tempEnemy));
		}
		x+=velX;
		y+=velY;
	}
	
	public void removeIfOutSideScreen(){
		if(x<=-ssWidth){
			game.getController().removeEntity(this);
		}
		else if(y<=-ssHeight){
			game.getController().removeEntity(this);
		}
		else if(x>=GameSystem.GAME_WIDTH){
			game.getController().removeEntity(this);
		}
		else if(y>=GameSystem.GAME_HEIGHT){
			game.getController().removeEntity(this);
		}
	}
}
