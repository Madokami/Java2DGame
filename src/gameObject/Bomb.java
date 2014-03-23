package gameObject;

import game.Game;

import java.util.LinkedList;

public class Bomb extends MovableObject{
	public int counter;
	public int explodeTime;
	public int strength;
	public int length;
	public int xStarting,yStarting;
	public String direction;
	public LinkedList<MovableObject> initiallyOnBomb=new LinkedList<MovableObject>();
	
	
	public Bomb(int x,int y, Game game,int bombStrength,int bombLength,int duration){
		super(x,y,game);
		
		stand=new ImageSequence("/image/projectiles/bomb/stand",1);
		sequence.startSequence(stand);
		
		explodeTime=duration;
		length=bombLength;
		strength=bombStrength;
		ss = SpriteData.bomb;
		image = ss.grabImage(1, 1, 32, 32);
		counter = 0;
		hp=1;
		
		xStarting=xGridNearest;
		yStarting=yGridNearest;
		//fireBomb();
		if(Physics.collide(this, game.getPlayer())){
			initiallyOnBomb.add(game.getPlayer());
		}
		LinkedList<Enemy> enemies=Physics.collision(this, game.getEnemyList());
		for(int i=0;i<enemies.size();i++){
			initiallyOnBomb.add(enemies.get(i));
		}
	}
	public void tick(){
		super.tick();
		for(int i=0;i<initiallyOnBomb.size();i++){
			if(!Physics.collide(this, initiallyOnBomb.get(i))){
				initiallyOnBomb.remove(i);
			}
		}
		
		counter++;
		if(counter>explodeTime||hp<=0){
			if(!game.isTimeStop()){
				explode();
			}
		}
	}
	public void explode(){
		game.getController().createExplosion((int)super.xGridNearest, (int)super.yGridNearest, length, strength);
		game.getController().removeEntity(this);
	}
	public void removeFromMap(){
		boolean[][] map = game.getBombArray();
		map[xStarting][yStarting]=false;
	}

}
