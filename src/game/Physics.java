package game;

import java.util.LinkedList;

public class Physics {
	public static boolean collision(FriendlyInterface f,LinkedList<EnemyInterface> ei){
		for(int i=0;i<ei.size();i++){
			if(f.getBounds(32,32).intersects(ei.get(i).getBounds(32,32))){
				return true;
			}
		}
		return false;
	}
	public static boolean collision(FriendlyInterface f,EnemyInterface e){
		if(f.getBounds(32,32).intersects(e.getBounds(32,32))){
			return true;
		}
		return false;
	}
	public static int collision(WallInterface w,LinkedList<EnemyInterface> ei){
		for(int i=0;i<ei.size();i++){
			if(w.getBounds(32,32).intersects(ei.get(i).getBounds(32,32))){
				return i;
			}
		}
		return -1;
	}
	public static boolean hitWall(GameObject f,LinkedList<WallInterface> wi){
		for(int i=0;i<wi.size();i++){
			if(f.getBounds(31, 31).intersects(wi.get(i).getBounds(31, 31)))
				return true;
		}
		return false;
	}
	public static int hitByAttack(GameObject f, LinkedList<Fire> fi){
		for(int i=0;i<fi.size();i++){
			if(f.getBounds(GameSystem.SIZE-5, GameSystem.SIZE-5).intersects(fi.get(i).getBounds(GameSystem.SIZE-5, GameSystem.SIZE-5)))
				//return fi.get(i).game.p.bombStrength;
				return fi.get(i).getStrength();
		}
		return -1;
	}
	public static boolean collide(GameObject x,GameObject y){
		if(x.getBounds(GameSystem.SIZE-2, GameSystem.SIZE-2).intersects(y.getBounds(GameSystem.SIZE-2,GameSystem.SIZE-2))){
			return true;
		}
		return false;
	}
}
