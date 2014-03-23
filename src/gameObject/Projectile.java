package gameObject;

import game.Game;

import java.util.LinkedList;

import system.GameSystem;

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
		LinkedList<Enemy> enemies=Physics.collision(this, game.getEnemyList());
		for(int i=0;i<enemies.size();i++){
			applyDamage(damage,10,enemies.get(i));
		}
		x+=getVelX();
		y+=getVelY();
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
