package gameObject;

import java.util.LinkedList;

import system.GameSystem;

public class Physics {
	public static int collision(Player p,LinkedList<PowerUps> powerUpList){
		for(int i=0;i<powerUpList.size();i++){
			if(p.getBounds(p.collisionWidth,p.collisionHeight).intersects(powerUpList.get(i).getBounds(powerUpList.get(i).collisionWidth,powerUpList.get(i).collisionHeight))){
				return i;
			}
		}
		return -1;
	}
	
	public static int onTopOfBomb(GameObject gameObject,LinkedList<Bomb> bList){
		for(int i=0;i<bList.size();i++){
			if(gameObject.xGridNearest==bList.get(i).xGridNearest&&gameObject.yGridNearest==bList.get(i).yGridNearest){
				return i;
			}
		}
		return -1;
	}
	public static LinkedList<Enemy> collision(GameObject w,LinkedList<Enemy> ei){
		LinkedList<Enemy> ret= new LinkedList<Enemy>();
		for(int i=0;i<ei.size();i++){
			if(w.getBounds(w.collisionWidth-1,w.collisionHeight-1).intersects(ei.get(i).getBounds(ei.get(i).collisionWidth-1,ei.get(i).collisionHeight-1))){
				ret.add(ei.get(i));
			}
		}
		return ret;
	}
	public static boolean overlapWithOtherEnemies(Enemy w,LinkedList<Enemy> ei){
		for(int i=0;i<ei.size();i++){
			Enemy tempEnemy=ei.get(i);
			if(w!=tempEnemy){
				if(w.getBounds(w.collisionWidth,w.collisionHeight).intersects(tempEnemy.getBounds(ei.get(i).collisionWidth,ei.get(i).collisionHeight))){
					return true;
				}
			}
		}
		return false;
	}
	public static boolean blockedByEnemy(Enemy source, LinkedList<Enemy> list,int targetX,int targetY){
		for(int i=0;i<list.size();i++){
			Enemy tempEnemy=list.get(i);
			if(source!=tempEnemy){
				if(list.get(i).xGridNearest==targetX&&list.get(i).yGridNearest==targetY){
					return true;
				}
			}
		}
		return false;
	}
	
	public static int hitBomb(GameObject w,LinkedList<Bomb> list){
		for(int i=0;i<list.size();i++){
			if(w.getBounds(w.collisionWidth-1,w.collisionHeight-1).intersects(list.get(i).getBounds(GameSystem.GRID_SIZE-1,GameSystem.GRID_SIZE-1))){
				return i;
			}
		}
		return -1;
	}
	
	public static int hitWall(GameObject f,LinkedList<HitableBrick> linkedList){
		for(int i=0;i<linkedList.size();i++){
			if(f.getBounds(f.collisionWidth-1, f.collisionHeight-1).intersects(linkedList.get(i).getBounds(GameSystem.GRID_SIZE-1, GameSystem.GRID_SIZE-1)))
				return i;
		}
		return -1;
	}
	public static int hitPlaceHolder(GameObject f,LinkedList<PlaceHolder> linkedList){
		for(int i=0;i<linkedList.size();i++){
			if(f.getBounds(f.collisionWidth-1, f.collisionHeight-1).intersects(linkedList.get(i).getBounds(GameSystem.GRID_SIZE-1, GameSystem.GRID_SIZE-1)))
				return i;
		}
		return -1;
	}
	
	public static boolean collide(GameObject x,GameObject y){
		if(x.getBounds(x.collisionWidth-1, x.collisionHeight-1).intersects(y.getBounds(y.collisionWidth-1,y.collisionHeight-1))){
			return true;
		}
		return false;
	}
}
